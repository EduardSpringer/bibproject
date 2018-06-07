package de.reserve;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;


import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;


/**
 * Servlet implementation class CurrentPlaceServlet
 */
@WebServlet("/currentplaceservlet")
public class CurrentPlaceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Resource(lookup="jdbc/MyTHIPool")
	private DataSource ds;
	
    public CurrentPlaceServlet() {
        super();
     
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Servlet zur Entgegennahme von Formularinhalten, Lesen der Daten in einer DB und Generierung
		// eines Beans zur Weitergabe der Formulardaten an eine JSP
				
		request.setCharacterEncoding("UTF-8");	// In diesem Format erwartet das Servlet jetzt die Formulardaten
		
		
		java.sql.Date currentdate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
		
		int anzPlaetze = sucheAnzAktuellerPlaetze(currentdate);
		int freiePlaetze = 53 - anzPlaetze;

		request.setAttribute("anzBesetztePlaetze", anzPlaetze);
		request.setAttribute("anzFreiePlaetze", freiePlaetze);
			
		// Weiterleiten an JSP
		RequestDispatcher dispatcher = request.getRequestDispatcher("home/jsp/JSON/currentPlace.jsp");
		dispatcher.forward(request, response);

	}
	
	
	private int sucheAnzAktuellerPlaetze(java.sql.Date currentdate) throws ServletException {
		// DB-Zugriff
		int anz = 0;
		try (Connection con = ds.getConnection();
			 PreparedStatement pstmt = con.prepareStatement("SELECT * FROM thidb.platzreservierung WHERE datum = ?")) {
			
			pstmt.setDate(1, currentdate);
			try (ResultSet rs = pstmt.executeQuery()) {
				
				while (rs != null && rs.next()) {
					if(timecheck(rs.getString("zeitraum")) == true) {//wenn zeitraum == jetzt
							anz++;
					}
				} // while rs.next()
			}
		} catch (Exception ex) {
			throw new ServletException(ex.getMessage());
		}
		return anz;
	}
	
	private Boolean timecheck(String zeitraumende) {
		/* Beispiel:
		 * heute Uhrzeit-> 14:15 --> std = 14 || 15:45 --> std = 15
		 * terminende muss deshalb 16 sein (14 bis 16 uhr Termin)
		 * terminende 16-1 = 15 & terminende-2 = 14
		 * */
		
		
		Calendar heute = Calendar.getInstance();
		int std = heute.get(Calendar.HOUR_OF_DAY);
		
		int terminende = Integer.parseInt(zeitraumende);
		
		//if 14 ==(16-2) || 15 == (16-1)	
		if (std == (terminende-2) || std == (terminende-1)) {
			return true;
		}
		
		return false;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}