/**
 * Sara Viktoria Miller
 */
package de.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import javabeans.LoginBean;

@WebServlet("/deleteuser")
public class DeleteUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(lookup="jdbc/MyTHIPool")
	private DataSource ds;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		final String username = String.valueOf(request.getParameter("User"));
		LoginBean loginbean = new LoginBean(); 
		loginbean.setUsername(username);
		if(username != null) {
			try(Connection con = ds.getConnection(); 
					PreparedStatement pstmt = con.prepareStatement("SELECT * FROM thidb.platzreservierung WHERE Username=?")){
					pstmt.setString(1, username); 
					ResultSet rs = pstmt.executeQuery(); 
					if(rs.next()) {
						try(Connection connect = ds.getConnection(); 
								PreparedStatement pst = connect.prepareStatement("DELETE FROM thidb.platzreservierung WHERE Username=?")){
									pst.setString(1, username);
									pst.executeUpdate(); 
									loeschen(username);
								}
					}
					else {
						loeschen(username);
					}
			}catch(Exception ex) {
				throw new ServletException(ex.getMessage()); 
			}
		}
		final RequestDispatcher dispatcher = request.getRequestDispatcher("/adminuserservlet");
		dispatcher.forward(request, response);
	}
       
	private void loeschen(String username) throws ServletException {
		try (Connection conn = ds.getConnection();
			 PreparedStatement ps = conn.prepareStatement("DELETE FROM thidb.Benutzer WHERE Username = ?")){
			ps.setString(1, username);
			ps.executeUpdate();
		}catch (Exception ex) {
			throw new ServletException(ex.getMessage());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}