/**	
 * Autor: Eduard Springer
 * 
 * Das CheckTerminbezeichnungServlet entnimmt die Terminbezeichnung aus der reserve.jsp.
 * Überprüft, ob diese Terminbezeichnung für den jeweiligen User bereits in der DB existiert.
 * Falls diese Terminbezeichnung nicht existiert, wird eine leere Liste an checkTerminbezeichnung.jsp (JSON-Objekt) übergeben.
 * Ansonsten wird ein Liste mit den IDs, die dieselbe Terminbezeichnung haben, an checkTerminbezeichnung.jsp (JSON-Objekt) übergeben.
 */

package de.reserve;

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
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import javabeans.LoginBean;
import javabeans.ReservationBean;

@WebServlet("/checkterminbezeichnungservlet")
public class CheckTerminbezeichnungServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Resource(lookup = "jdbc/MyTHIPool")
	private DataSource ds;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		String terminbezeichnung = request.getParameter("terminbezeichnung");
	
		HttpSession session = request.getSession();
		LoginBean user = (LoginBean) session.getAttribute("lb");
		String username = user.getUsername();
		
		List<ReservationBean> anzGleicherTerminbezeichnungen = search(terminbezeichnung, username);
				
		request.setAttribute("anzGleicherTerminbezeichnungen", anzGleicherTerminbezeichnungen);
		
		final RequestDispatcher dispatcher = request.getRequestDispatcher("home/jsp/JSON/checkTerminbezeichnung.jsp"); 
		dispatcher.forward(request, response);	
	}
	
	private List<ReservationBean> search(String terminbezeichnung, String username) throws ServletException {
		List<ReservationBean> anzGleicherTerminbezeichnungen = new ArrayList<ReservationBean>();

		try (Connection con = ds.getConnection();
			 PreparedStatement pstmt = con.prepareStatement("SELECT ReservierungID FROM thidb.platzreservierung "
			 		+ "WHERE terminbezeichnung = ? AND username = ?")) {

			pstmt.executeQuery("SET NAMES 'UTF8'");// Im Format UTF-8 in DB speichern
			pstmt.executeQuery("SET CHARACTER SET 'UTF8'");
			
			pstmt.setString(1, terminbezeichnung);
			pstmt.setString(2, username);
			
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs != null && rs.next()) {
					ReservationBean rb = new ReservationBean();
					rb.setReservierungID(rs.getInt("ReservierungID"));
					anzGleicherTerminbezeichnungen.add(rb);
				}
			}
		} catch (Exception ex) {
			throw new ServletException(ex.getMessage());
		}
		return anzGleicherTerminbezeichnungen;
	} 
}
