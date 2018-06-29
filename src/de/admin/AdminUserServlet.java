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
import javabeans.ProfileBean;

@WebServlet("/adminuserservlet") 

public class AdminUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(lookup="jdbc/MyTHIPool")
	private DataSource ds;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");	
		List<ProfileBean> users = read(); 
		request.setAttribute("users", users);
		final RequestDispatcher dispatcher = request.getRequestDispatcher("/home/jsp/adminUser.jsp");
		dispatcher.forward(request, response);	
	}
 
	protected List<ProfileBean> read() throws ServletException{
		List<ProfileBean> users = new ArrayList<ProfileBean>(); 
		try(Connection conn = ds.getConnection();
				PreparedStatement ps = conn.prepareStatement("SELECT * FROM thidb.Benutzer ORDER BY Adminrechte DESC")){
			try(ResultSet rs = ps.executeQuery()){ 
				while(rs.next()) {
					ProfileBean pb = new ProfileBean(); 
					pb.setVorname(rs.getString("Vorname")); 
					pb.setNachname(rs.getString("Nachname")); 
					pb.setEmail(rs.getString("EMail"));
					pb.setUsername(rs.getString("Username"));
					pb.setAdminrechte(rs.getBoolean("Adminrechte"));
					users.add(pb); ; 
				}
			}
		}catch(Exception ex) {
			throw new ServletException(ex.getMessage()); 
		}
		return users; 
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}   
}