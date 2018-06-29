//Helene Akulow
package de.reserve;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import javabeans.LoginBean;
import javabeans.ReservationBean;
import java.util.Date;
import java.util.HashSet;
/**
 * Servlet implementation class MyReservation
 */
@WebServlet("/myreservationservlet")
public class MyReservationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Resource(lookup="jdbc/MyTHIPool")
	private DataSource ds;

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Servlet zur Entgegennahme von Formularinhalten, Lesen der Daten in einer DB und Generierung
		// eines Beans zur Weitergabe der Formulardaten an eine JSP
		
		request.setCharacterEncoding("UTF-8");	// In diesem Format erwartet das Servlet jetzt die Formulardaten
		String page = request.getParameter("page"); //diese Seite soll angezeigt werden!
		
		
		HttpSession session = request.getSession();
		LoginBean user = (LoginBean) session.getAttribute("lb");
		
		//Aufruf durch URL Fehler vermeiden
		if(user == null) {
			// Weiterleiten an JSP
			final RequestDispatcher dispatcher = request.getRequestDispatcher("/home/jsp/login.jsp");
			dispatcher.forward(request, response);	
		}
		else {
		
			// DB-Zugriff Einzeltermine
			List<ReservationBean> einzeltermine = sucheEinzeltermine(user.getUsername());
			
			//DB-Zugriff Wiederholungstermine
			Object wdhObject[] = sucheWdhtermine(user.getUsername());
			List<ReservationBean> wdhtermine = (List<ReservationBean>) wdhObject[0];
			Set<String> terminBezSet = (Set<String>) wdhObject[1];
			
			//Berechnung der Seitenanzahl und Anzeige der Elemente für jeweilige Seite für die Einzeltermine
			int nextPageEinzeltermin [] = getEinzelterminPage(page,einzeltermine.size());
			int start = nextPageEinzeltermin [0];
			int end = nextPageEinzeltermin [1];
			int anzSeiten = nextPageEinzeltermin [2];
			int nachfolgendeSeite = nextPageEinzeltermin [3];
			int vorherigeSeite =nextPageEinzeltermin [4];
			
			// Scope "Request"
			request.setAttribute("erstesElement", start); //foreach schleife Einzeltermine
			request.setAttribute("letztesElement", end); //foreach schleife Einzeltermine
			request.setAttribute("seitenAnzInsgesamt", anzSeiten); //foreach schleife für Seitenanzahllink
			request.setAttribute("nachfolgendeSeite", nachfolgendeSeite); //"nächste Seite" Einzeltermine
			request.setAttribute("vorherigeSeite", vorherigeSeite);//"vorherige Seite" Einzeltermine
		
			request.setAttribute("einzeltermine", einzeltermine); //Liste der Res.Bean (Einzeltermine)		
			request.setAttribute("wdhtermine", wdhtermine); //Liste der Res.Bean (Wdh Termine)
			request.setAttribute("terminBezSet", terminBezSet); //alle Terminbezeichnungen
			
			// Weiterleiten an JSP
			final RequestDispatcher dispatcher = request.getRequestDispatcher("/home/jsp/myReservation.jsp");
			dispatcher.forward(request, response);	
		}
	}

	private List<ReservationBean> sucheEinzeltermine(String username) throws ServletException {

		List<ReservationBean> einzeltermine = new ArrayList<ReservationBean>();
		
		// DB-Zugriff
		try (Connection con = ds.getConnection();
			 PreparedStatement pstmt = con.prepareStatement("SELECT * FROM thidb.platzreservierung " 
			 		+ "WHERE username = ? AND terminbezeichnung IS NULL ORDER BY Datum, zeitraum, PlatzID ASC")) {
			
			pstmt.setString(1,username);
			try (ResultSet rs = pstmt.executeQuery()) {
				
				while (rs != null && rs.next()) {
					if(timecheck(rs.getDate("datum"),rs.getString("zeitraum")) == true) {
						ReservationBean rb = new ReservationBean();
						rb.setReservierungID(rs.getInt("reservierungID"));
						rb.setDatumString(changeDateFormat(rs.getDate("datum")));
						rb.setZeitraum(changeTimeFormat(rs.getString("zeitraum")));
						rb.setPlatzID(rs.getInt("PlatzID"));
						
						einzeltermine.add(rb);
						
					}
				} // while rs.next()
			}
		} catch (Exception ex) {
			throw new ServletException(ex.getMessage());
		}
		
		return einzeltermine;
	}
	
	private Object[] sucheWdhtermine(String username) throws ServletException {

		List<ReservationBean> wdhtermine = new ArrayList<ReservationBean>();
		
		Set<String> terminbez = new HashSet<String>();
		
		// DB-Zugriff
		try (Connection con = ds.getConnection();
			 PreparedStatement pstmt = con.prepareStatement("SELECT * FROM thidb.platzreservierung"
			 		+ " WHERE username = ? AND terminbezeichnung IS NOT NULL ORDER BY Datum, zeitraum, PlatzID ASC")) {
			
			pstmt.setString(1,username);
			try (ResultSet rs = pstmt.executeQuery()) {
				
				while (rs != null && rs.next()) {
					if(timecheck(rs.getDate("datum"),rs.getString("zeitraum")) == true) {
						ReservationBean rb = new ReservationBean();
						rb.setReservierungID(rs.getInt("reservierungID"));
						rb.setDatumString(changeDateFormat(rs.getDate("datum")));
						rb.setZeitraum(changeTimeFormat(rs.getString("zeitraum")));
						rb.setPlatzID(rs.getInt("PlatzID"));	
						rb.setTerminbezeichnung(rs.getString("terminbezeichnung"));
						
						wdhtermine.add(rb);	
						//System.out.println("wdhtermine");
						terminbez.add(rs.getString("terminbezeichnung"));
						
					}
				} 
			}
		} catch (Exception ex) {
			throw new ServletException(ex.getMessage());
		}
		
		return new Object[] {wdhtermine,terminbez};
	}
	
	
	private Boolean timecheck(Date datum, String time) {
		Calendar now = Calendar.getInstance();
		Date today=now.getTime();
		
		//wenn Termin morgen oder später ist, dann passt es
		if(datum.after(today)==true) { // datum > heute -> datum liegt in der zukunft
			return true;
		}
		//Für Zeitvergleich:
		int zeitraumende = Integer.parseInt(time);
		int stdNow = now.get(Calendar.HOUR_OF_DAY);
		
		//Für Datumvergleich:
	    //Zeit von now & termindatum wird auf 0 gesetzt, weil in der DB eindefault-Wert abgespeichert wird, 
		//welcher ignoriert werden muss 
	    now.set(Calendar.HOUR_OF_DAY, 0);
	    now.set(Calendar.MINUTE, 0);
	    now.set(Calendar.SECOND, 0);
	    now.set(Calendar.MILLISECOND, 0);
	    
	    Calendar termindatum = Calendar.getInstance();
	    termindatum.setTime(datum);
	    termindatum.set(Calendar.HOUR_OF_DAY, 0);
	    termindatum.set(Calendar.MINUTE, 0);
	    termindatum.set(Calendar.SECOND, 0);
	    termindatum.set(Calendar.MILLISECOND, 0);
		
		//wenn termindatum == heute & terminzeit noch nicht vorbei, dann passt es auch
		if(termindatum.equals(now)){
			if(zeitraumende >  stdNow ) {//wenn zeitraumede später als stdNow ist
				//System.out.println("zeitraum >= stdNow: " + zeitraumende + " < " + stdNow);
				return true;
			}
		}	
		
		return false;
	}
	
	private String changeDateFormat(Date datum) {
		SimpleDateFormat sd = new SimpleDateFormat("dd.MM.yyyy");
		String dateString = sd.format(datum); 
		
		return dateString;
	}
	
	private String changeTimeFormat(String zeitraumende) {
		String zeitraum;
		if (zeitraumende.equals("10")) {
			zeitraum = "08:00 - 10:00";
			return zeitraum;
		}
		else if(zeitraumende.equals("12")) {
			zeitraum = "10:00 - 12:00";
			return zeitraum;
		}
		else if(zeitraumende.equals("14")) {
			zeitraum = "12:00 - 14:00";
			return zeitraum;
		}
		else if(zeitraumende.equals("16")) {
			zeitraum = "14:00 - 16:00";
			return zeitraum;
		}
		else if(zeitraumende.equals("18")) {
			zeitraum = "16:00 - 18:00";
			return zeitraum;
		}
		else if(zeitraumende.equals("20")) {
			zeitraum = "18:00 - 20:00";
			return zeitraum;
		}
		else if(zeitraumende.equals("22")) {
			zeitraum = "20:00 - 22:00";
			return zeitraum;
		}
		else if(zeitraumende.equals("24")) {
			zeitraum = "22:00 - 24:00";
			return zeitraum;
		}
		else {
			return "bis " + zeitraumende + ":00";
		}
	}
	
	public int[] getEinzelterminPage(String p, int anzEinzTermine) {
		int page = Integer.parseInt(p); //diese Seite soll angezeigt werden
		int anzSeiten = 1; //so viele Seiten gibt es insgesamt
		
		
	//Wenn es nur eine Seite gibt:
		int start = 0; //"Begin" für die Ausgabe in der Foreach Schleife
		int end = 0; // "End" für die Ausgabe
		
		//Wenn es weniger als 10 Termine gibt,
		//bleibt nextPage und lastPage auf 0
		//und dann wird "nächste Seite" & "vorherige Seite" nicht angezeigt
		int nachfolgendeSeite=0;
		int vorherigeSeite=0;
		
		
	// Wenn es mehr als 10 Termine gibt, dann gibt es die Blätterfunktion:
		int anzElemAufEinerSeite = 10;
		if(anzEinzTermine > anzElemAufEinerSeite) {
			start = (page - 1) * anzElemAufEinerSeite; 
			//Zb: (2-1)*10 = 10 -> 2.Seite zeigt ab dem 11ten Element an (-1 weil es bei 0 anfängt)
			
			//Berechnung Seitenanzahl
			if(anzEinzTermine % anzElemAufEinerSeite == 0 ) { // % Anzahl der Elemente auf einer Seite
				//Es gibt keine "Restelemente", die auf die nächste Seite müssen
				anzSeiten = anzEinzTermine / anzElemAufEinerSeite;											
			}
			
			else {  //"Restelemente" die auf einer neuen Seite angezeigt werden müssen
				anzSeiten = anzEinzTermine / anzElemAufEinerSeite + 1;									
			}
			
			//Berechnung "End" ->letztes Element das Angezeigt werden soll
			if(page == anzSeiten && anzEinzTermine % anzElemAufEinerSeite != 0) { // Wenn letzte Seite und "Restelemente"
				end= start + (anzEinzTermine % anzElemAufEinerSeite);			
				//Gesamtzahl der Einzeltermine = 45
				//zb: Seite 5: start = (5-1)*10 = 40
				//end = 40 + (45 % 10) - 1 = 44	
				
			}
			else {
				end = (page * anzElemAufEinerSeite)-1;
				//zb: (2 * 10) -1 = 19 -> 2.Seite zeigt bis zum 20 Element an (-1 weil es bei 0 anfängt)
			}
										
		}
		else { // wenn es =< 10 Elemente gibt, dann soll die foreachSchleife nur bis zum Letzten Termin gehen
			end= anzEinzTermine-1; //-1 weil es bei 0 anfängt
		}
		
		//Wenn es mehrere Seiten und der User NICHT auf der LETZTEN Seite ist
		//dann wird "nächste Seite" angezeigt
		if(anzSeiten > 1 && page != anzSeiten) { 
			nachfolgendeSeite = page+1; // für query
		}
		//Wenn es mehrere Seiten gibt und der User NICHT auf der ERSTEN Seite ist
		//dann wird "letzte Seite" angezeigt
		if(anzSeiten > 1 && page != 1) {
			vorherigeSeite = page-1; // für query
		}
		
		int ergebnis [] = {start, end, anzSeiten, nachfolgendeSeite, vorherigeSeite};
		return ergebnis ;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
