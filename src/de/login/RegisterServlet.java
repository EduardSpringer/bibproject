package de.login;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

@WebServlet("/registerservlet")
@MultipartConfig
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Resource(lookup="jdbc/MyTHIPool")
	private DataSource ds;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
		final String vorname = request.getParameter("vorname");
		final String nachname = request.getParameter("nachname");
		final String email = request.getParameter("email");
		final String passwort = request.getParameter("passwort");
		final String passwortbestaetigen = request.getParameter("passwortbestaetigen");
		//Passwortbestätigung mit JavaScript?
				
		HttpSession session = request.getSession(); 
				
		ProfileBean javabean = new ProfileBean(); 
		javabean.setVorname(vorname); 
		javabean.setNachname(nachname);
		javabean.setEmail(email); 
		javabean.setPasswort(passwort);
		javabean.setUsername(email.substring(0, 7));
		//System.out.println(javabean.getUsername());

						session.setAttribute("javabean", javabean);
						String[] generatedKeys = new String[]{"id"}; 				
						try(Connection con = ds.getConnection();
							PreparedStatement ps = con.prepareStatement(
								"INSERT INTO thidb.Benutzer (Vorname, Nachname, Passwort, EMail, Username, Profilbild) VALUES (?, ?, ?, ?, ?, ?)", generatedKeys)){
							ps.setString(1, javabean.getVorname()); 
							ps.setString(2, javabean.getNachname()); 
							ps.setString(3, javabean.getPasswort()); 
							ps.setString(4, javabean.getEmail());
							ps.setString(5, javabean.getUsername());
							ps.setBytes(6, javabean.getImage()); 
							//ps.setString(7, javabean.getUsername());
							//ps.executeUpdate(); 
							try(Connection c = ds.getConnection()){
								ps.executeUpdate(); 
								response.sendRedirect("home/jsp/register.jsp"); 
							}
							catch(Exception e) {
								response.sendRedirect("home/jsp/benutzervorhanden.jsp"); 
							}
						}
						catch(Exception ex) {
							throw new ServletException(ex.getMessage()); 
						
						}
						
						

	}
	
	/*private void persist(ProfileBean javabean) throws ServletException{
		String[] generatedKeys = new String[]{"id"}; 				
			try(Connection con = ds.getConnection();
				PreparedStatement ps = con.prepareStatement(
					"INSERT INTO thidb.Benutzer (Vorname, Nachname, Passwort, EMail, Username, Profilbild) VALUES (?, ?, ?, ?, ?, ?)", generatedKeys)){
					//FROM DUAL WHERE NOT EXISTS (SELECT Username FROM thidb.benutzer WHERE Benutzer.Username = ?)", 
					//generatedKeys)){
					//SQL-Statement: https://www.techonthenet.com/sql/insert.php
				ps.setString(1, javabean.getVorname()); 
				ps.setString(2, javabean.getNachname()); 
				ps.setString(3, javabean.getPasswort()); 
				ps.setString(4, javabean.getEmail());
				ps.setString(5, javabean.getUsername());
				ps.setBytes(6, javabean.getImage()); 
				//ps.setString(7, javabean.getUsername());
				ps.executeUpdate(); 
			}
			catch(Exception ex) {
				throw new ServletException(ex.getMessage()); 
			}
	}*/

}