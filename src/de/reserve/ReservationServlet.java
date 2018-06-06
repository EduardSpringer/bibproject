//Eduard Springer

package de.reserve;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import javabeans.ContactBean;


@WebServlet("/reservationservlet")
public class ReservationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Resource(lookup = "jdbc/MyTHIPool")
	private DataSource ds;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String datum = request.getParameter("datum");//Zugriff auf die Felder(name-tag) in .jsp-Datei
		String zeitraum = request.getParameter("zeitraum");
		String platznr = request.getParameter("platznr");
		String termin = request.getParameter("termin");
		String terminbezeichnung = request.getParameter("terminbezeichnung");
		String bis = request.getParameter("bis");
		
		System.out.println("vom: " + datum + " Zeitraum: " + zeitraum + " Platz-Nr.: " + platznr + " Termin: " + termin + " Terminbezeichnung: " + terminbezeichnung + " bis: " + bis);
		
		if ("einzeltermin".equals(termin)) {
			System.out.println("Yes: einzeltermin");
		} else if ("wiederholtermin".equals(termin)) {
			System.out.println("Yes: wiederholtermin");
		}
		
//
//		HttpSession session = request.getSession();//Nutzung einer Sitzung
//		session.setAttribute("cb", cb);// JavaBean für die aktuelle Sitzung festlegen 
//
//		persist(cb); // Übertragung der JavaBean an die DB
//		
		final RequestDispatcher dispatcher = request.getRequestDispatcher("/home/jsp/reserve.jsp");
		dispatcher.forward(request, response);	
	}

}
