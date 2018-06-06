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

import javabeans.ContactBean;
import javabeans.LoginBean;


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
		
		HttpSession session = request.getSession();
		LoginBean user = (LoginBean) session.getAttribute("lb");
		String username = user.getUsername();
		
		if ("einzeltermin".equals(termin)) {
			resEinzeltermin(datum, zeitraum, platznr, username);
		} else if ("wiederholtermin".equals(termin)) {
			
		}
		
		final RequestDispatcher dispatcher = request.getRequestDispatcher("/home/jsp/reserve.jsp");
		dispatcher.forward(request, response);	
	}
	
	private void resEinzeltermin(String datum, String zeitraum, String platznr, String username) throws ServletException {
		try (Connection con = ds.getConnection();
			 PreparedStatement pstmt = con.prepareStatement("INSERT INTO thidb.platzreservierung (datum, zeitraum, platzid, username) VALUES (?,?,?,?)")){
			pstmt.setString(1, datum);
			pstmt.setString(2, zeitraum);
			pstmt.setString(3, platznr);
			pstmt.setString(4, username);
			pstmt.executeUpdate();
		} catch (Exception ex) {
			throw new ServletException(ex.getMessage());
		}
	}
}
