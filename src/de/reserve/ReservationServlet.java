//Eduard Springer

package de.reserve;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javabeans.LoginBean;



@WebServlet("/reservationservlet")
public class ReservationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Resource(lookup = "jdbc/MyTHIPool")
	private DataSource ds;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//		request.setCharacterEncoding("UTF-8");

		String datum = request.getParameter("datum");
		String zeitraum = request.getParameter("zeitraum");
		String platznr = request.getParameter("platznr");
		String termin = request.getParameter("termin");

		HttpSession session = request.getSession();
		LoginBean user = (LoginBean) session.getAttribute("lb");
		String username = user.getUsername();
		
		if ("einzeltermin".equals(termin)) {
			resTermin(datum, zeitraum, platznr, username, null);
		}
		else {
//			response.setContentType("text/plain");
			JSONArray json;
				
			try {
				json = new JSONArray(request.getParameter("liste"));

				for(int i = 0; i < json.length(); i++) {

					JSONObject jsonObject = json.getJSONObject(i);
						
					if(jsonObject.has("datum")) {//Überprüfen, ob es die Plätze für Buchung sind (freiePlätze=datum; besetztePlätze=datumBesetzt)
						String strDatum = jsonObject.getString("datum");
						String strZeitraum = jsonObject.getString("zeitraum");
						String strPlatznr = jsonObject.getString("platznr");
						String strTerminbezeichnung = jsonObject.getString("terminbezeichnung");

						resTermin(strDatum, strZeitraum, strPlatznr, username, strTerminbezeichnung);
					}
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		final RequestDispatcher dispatcher = request.getRequestDispatcher("/home/jsp/reserve.jsp");
		dispatcher.forward(request, response);	
	}
		
		//@deprecated Da Wiederholtermine mittels Ajax gebucht werden, um Überschneidungen zu vermeiden!
//		else if ("wiederholtermin".equals(termin)) {
//			
////			http://www.baeldung.com/java-date-difference
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		    Date firstDate = null;
//			try {
//				firstDate = sdf.parse(datum);
//			} catch (ParseException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		    Date secondDate = null;
//			try {
//				secondDate = sdf.parse(bis);
//			} catch (ParseException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		 
//		    long diffInMillies = Math.abs(secondDate.getTime() - firstDate.getTime());
//		    long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS)/ 7;
//	
////			https://stackoverflow.com/questions/428918/how-can-i-increment-a-date-by-one-day-in-java
//			SimpleDateFormat simpledf = new SimpleDateFormat("yyyy-MM-dd");
//			Calendar c = Calendar.getInstance();
//			
//			resTermin(datum, zeitraum, platznr, username, terminbezeichnung);
//			
//			int k = 0;
//			
//			for(; k < diff; k++) {
//			
//				try {
//					c.setTime(simpledf.parse(datum));
//				} catch (ParseException e) {
//					e.printStackTrace();
//				}
//				c.add(Calendar.DATE, 7);  // number of days to add
//				datum = simpledf.format(c.getTime()); 
//				
//				resTermin(datum, zeitraum, platznr, username, terminbezeichnung);
//			}
//		}
//		
	
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
