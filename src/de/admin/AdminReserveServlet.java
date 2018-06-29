/**
 * Sara Viktoria Miller
 */
package de.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import javabeans.ReservationBean;

@WebServlet("/adminreserveservlet") 

public class AdminReserveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(lookup="jdbc/MyTHIPool")
	private DataSource ds;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");	
		List<ReservationBean> reservierungen = read(); 
		request.setAttribute("reservierungen", reservierungen);
		final RequestDispatcher dispatcher = request.getRequestDispatcher("/home/jsp/adminReserve.jsp");
		dispatcher.forward(request, response);	
	}

	protected List<ReservationBean> read() throws ServletException{
		List<ReservationBean> reservierungen = new ArrayList<ReservationBean>(); 
		try(Connection conn = ds.getConnection();
				PreparedStatement ps = conn.prepareStatement("SELECT * FROM thidb.platzreservierung INNER JOIN thidb.Benutzer ON platzreservierung.Username = Benutzer.Username ORDER BY Datum DESC")){
			try(ResultSet rs = ps.executeQuery()){
				while(rs.next()) {
					ReservationBean rb = new ReservationBean(); 
					rb.setReservierungID(rs.getInt("ReservierungID")); 
					rb.setDatumString(rs.getString("Datum"));
					rb.setZeitraum(rs.getString("Zeitraum"));
					rb.setPlatzID(rs.getInt("PlatzID"));
					rb.setNachname(rs.getString("Nachname")); 
					rb.setVorname(rs.getString("Vorname")); 
					rb.setUsername(rs.getString("Username"));
					reservierungen.add(rb); 
				}	
			}
		}catch(Exception ex) {
			throw new ServletException(ex.getMessage()); 
		}
		return reservierungen; 
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}       
}