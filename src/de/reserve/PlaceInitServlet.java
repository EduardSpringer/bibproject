//Eduard Springer

package de.reserve;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import javax.sql.DataSource;

import javabeans.ReservationBean;


@WebServlet("/placeinitservlet")
public class PlaceInitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Resource(lookup = "jdbc/MyTHIPool")
	private DataSource ds;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		String datum = request.getParameter("datum");
		String zeitraum = request.getParameter("zeitraum");
		
		List<ReservationBean> reserviertePlaetze = search(datum, zeitraum);
				
		request.setAttribute("reserviertePlaetze", reserviertePlaetze);
		
		final RequestDispatcher dispatcher = request.getRequestDispatcher("home/jsp/JSON/placeInit.jsp"); //JSON-Objekt
		dispatcher.forward(request, response);	
	}

	private List<ReservationBean> search(String datum, String zeitraum) throws ServletException {
		List<ReservationBean> reserviertePlaetze = new ArrayList<ReservationBean>();
		
		int zeitraumInt = Integer.parseInt(zeitraum);
		
		try (Connection con = ds.getConnection();
			 PreparedStatement pstmt = con.prepareStatement("SELECT PlatzID FROM thidb.platzreservierung WHERE datum = ? AND zeitraum = ?")) {

			pstmt.setString(1, datum);
			pstmt.setInt(2, zeitraumInt);
			
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs != null && rs.next()) {
					ReservationBean rb = new ReservationBean();
					rb.setPlatzID(rs.getInt("PlatzID"));
					reserviertePlaetze.add(rb);
				}
			}
		} catch (Exception ex) {
			throw new ServletException(ex.getMessage());
		}
		return reserviertePlaetze;
	} 
}
