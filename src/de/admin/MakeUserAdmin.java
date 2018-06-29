/**
 * Sara Viktoria Miller
 */
package de.admin;

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
import javax.sql.DataSource;

@WebServlet("/makeuseradmin")
public class MakeUserAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(lookup="jdbc/MyTHIPool")
	private DataSource ds;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");	
		final String username = String.valueOf(request.getParameter("User"));
		if(!username.isEmpty()) {
			rechteVergeben(username);
		}
		final RequestDispatcher dispatcher = request.getRequestDispatcher("/adminuserservlet");
		dispatcher.forward(request, response);
	}
       
	private void rechteVergeben(String user) throws ServletException {
		try (Connection con = ds.getConnection();
			 PreparedStatement pstmt = con.prepareStatement("UPDATE thidb.Benutzer SET Adminrechte = '1' WHERE Username = ?")){
			pstmt.setString(1, user);
			pstmt.executeUpdate();
		}catch (Exception ex) {
			throw new ServletException(ex.getMessage());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}