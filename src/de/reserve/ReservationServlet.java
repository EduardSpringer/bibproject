//Eduard Springer

package de.reserve;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

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


@WebServlet("/reservationservlet")
public class ReservationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Resource(lookup = "jdbc/MyTHIPool")
	private DataSource ds;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String datum = request.getParameter("datum");
		String zeitraum = request.getParameter("zeitraum");
		String platznr = request.getParameter("platznr");
		String termin = request.getParameter("termin");
		String terminbezeichnung = request.getParameter("terminbezeichnung");
		String bis = request.getParameter("bis");
		
		HttpSession session = request.getSession();
		LoginBean user = (LoginBean) session.getAttribute("lb");
		String username = user.getUsername();
		
		if ("einzeltermin".equals(termin)) {
			resTermin(datum, zeitraum, platznr, username, null);
			
		//deprecated, da Wiederholtermine mittels Ajax im anderen Servlet (BookingWiederholtermineServlet) gebucht werden
		} else if ("wiederholtermin".equals(termin)) {
			
//			http://www.baeldung.com/java-date-difference
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		    Date firstDate = null;
			try {
				firstDate = sdf.parse(datum);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    Date secondDate = null;
			try {
				secondDate = sdf.parse(bis);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 
		    long diffInMillies = Math.abs(secondDate.getTime() - firstDate.getTime());
		    long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS)/ 7;
	
//			https://stackoverflow.com/questions/428918/how-can-i-increment-a-date-by-one-day-in-java
			SimpleDateFormat simpledf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar c = Calendar.getInstance();
			
			resTermin(datum, zeitraum, platznr, username, terminbezeichnung);
			
			int k = 0;
			
			for(; k < diff; k++) {
			
				try {
					c.setTime(simpledf.parse(datum));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				c.add(Calendar.DATE, 7);  // number of days to add
				datum = simpledf.format(c.getTime()); 
				
				resTermin(datum, zeitraum, platznr, username, terminbezeichnung);
			}
		}
		
		final RequestDispatcher dispatcher = request.getRequestDispatcher("/home/jsp/reserve.jsp");
		dispatcher.forward(request, response);	
	}
	
	private void resTermin(String datum, String zeitraum, String platznr, String username, String terminbezeichnung) throws ServletException {
		try (Connection con = ds.getConnection();
			 PreparedStatement pstmt = con.prepareStatement("INSERT INTO thidb.platzreservierung (datum, zeitraum, platzid, username, terminbezeichnung) VALUES (?,?,?,?,?)")){
			pstmt.setString(1, datum);
			pstmt.setString(2, zeitraum);
			pstmt.setString(3, platznr);
			pstmt.setString(4, username);
			pstmt.setString(5, terminbezeichnung);
			pstmt.executeUpdate();
		} catch (Exception ex) {
			throw new ServletException(ex.getMessage());
		}
	}
}
