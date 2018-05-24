//Helene Akulow
package de.reserve;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

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
/**
 * Servlet implementation class MyReservation
 */
@WebServlet("/myreservationservlet")
public class MyReservationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Resource(lookup="jdbc/MyTHIPool")
	private DataSource ds;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Servlet zur Entgegennahme von Formularinhalten, Lesen der Daten in einer DB und Generierung
		// eines Beans zur Weitergabe der Formulardaten an eine JSP
		
		request.setCharacterEncoding("UTF-8");	// In diesem Format erwartet das Servlet jetzt die Formulardaten

		HttpSession session = request.getSession();
		LoginBean user = (LoginBean) session.getAttribute("lb");
		
		// DB-Zugriff
		List<ReservationBean> einzeltermine = sucheEinzeltermine(user.getUsername());
		
				
		// Scope "Request"
		request.setAttribute("einzeltermine", einzeltermine);
		
		// Weiterleiten an JSP
		final RequestDispatcher dispatcher = request.getRequestDispatcher("/home/jsp/myReservation.jsp");
		dispatcher.forward(request, response);	
	}

	private List<ReservationBean> sucheEinzeltermine(String username) throws ServletException {

		List<ReservationBean> einzeltermine = new ArrayList<ReservationBean>();
		
		// DB-Zugriff
		try (Connection con = ds.getConnection();
			 PreparedStatement pstmt = con.prepareStatement("SELECT * FROM thidb.platzreservierung WHERE username = ?")) {
			
			pstmt.setString(1,username);
			try (ResultSet rs = pstmt.executeQuery()) {
				
				while (rs != null && rs.next()) {
					ReservationBean rb = new ReservationBean();
					rb.setDatumString(changeDateFormat(rs.getDate("datum")));
					rb.setZeitraum(changeTimeFormat(rs.getString("zeitraum")));
					rb.setPlatzID(rs.getInt("PlatzID"));
					
					einzeltermine.add(rb);
				} // while rs.next()
			}
		} catch (Exception ex) {
			throw new ServletException(ex.getMessage());
		}
		
		return einzeltermine;
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
