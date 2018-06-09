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
		
		String next = request.getParameter("next");
		int nextPage = Integer.parseInt(next);
		
		HttpSession session = request.getSession();
		LoginBean user = (LoginBean) session.getAttribute("lb");
		
		// DB-Zugriff
		List<ReservationBean> einzeltermine = sucheEinzeltermine(user.getUsername());
		int anzEinzTermine = einzeltermine.size();
		//int nextPage = 1;
		boolean checkNextPage = false;

		
		if(anzEinzTermine > 10) {
			checkNextPage = true;			
		}
		
		int start = 0;
		int end = 10;
		if(nextPage == 2) {
			start = 11;
			end = 20;
		}
		
		Object wdhObject[] = sucheWdhtermine(user.getUsername());
		List<ReservationBean> wdhtermine = (List<ReservationBean>) wdhObject[0];
		Set<String> terminBezSet = (Set<String>) wdhObject[1];
						
		// Scope "Request"
		request.setAttribute("start", start);
		request.setAttribute("end", end);
		request.setAttribute("einzeltermine", einzeltermine);
		request.setAttribute("nextPage", nextPage);
		request.setAttribute("checkNextPage",checkNextPage);
		
		request.setAttribute("wdhtermine", wdhtermine);
		request.setAttribute("terminBezSet", terminBezSet);
		
		// Weiterleiten an JSP
		final RequestDispatcher dispatcher = request.getRequestDispatcher("/home/jsp/myReservation.jsp");
		dispatcher.forward(request, response);	
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
