package de.login;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.Connection;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import javabeans.ProfileBean;

@WebServlet("/profileeditservlet")
@MultipartConfig
public class ProfileEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Resource(lookup="jdbc/MyTHIPool")
	private DataSource ds;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String vorname = request.getParameter("vorname");
		final String nachname = request.getParameter("nachname");
		final String passwort = request.getParameter("passwort");
		
		HttpSession session = request.getSession();
		
		ProfileBean javabean = new ProfileBean(); 
		javabean.setVorname(vorname); 
		javabean.setNachname(nachname);
		javabean.setPasswort(passwort);
		 
		session.setAttribute("javabean", javabean);
		persist(javabean); 
		response.sendRedirect("home/jsp/profileEdit.jsp");
	}

	private void persist(ProfileBean javabean) throws ServletException{
		String[] generatedKeys = new String[]{"id"}; 				
			try(Connection con = ds.getConnection(); 
				PreparedStatement ps = con.prepareStatement("UPDATE Benutzer " + "SET Vorname = ?, "
					+ "Nachname = ?, Passwort = ?, Profilbild = ?" + "WHERE ID = ?", generatedKeys)){
				//SQL-Statement: https://www.techonthenet.com/sql/insert.php
				ps.setString(1, javabean.getVorname()); 
				ps.setString(2, javabean.getNachname()); 
				ps.setString(3, javabean.getPasswort()); 
				ps.setBytes(4, javabean.getImage()); 
				ps.setString(5, javabean.getUsername());
				ps.executeUpdate(); 
			}	
			catch (Exception ex) {
				throw new ServletException(ex.getMessage()); 
			}
	}

}