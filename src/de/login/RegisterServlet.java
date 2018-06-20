package de.login;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.sql.DataSource;

import javabeans.LoginBean;
import javabeans.ProfileBean;

@WebServlet("/registerservlet")
@MultipartConfig(
		maxFileSize = 1024*1024*5
)

public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Resource(lookup="jdbc/MyTHIPool")
	private DataSource ds;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
		final String vorname = request.getParameter("vorname");
		final String nachname = request.getParameter("nachname");
		final String email = request.getParameter("email");
		final String passwort = request.getParameter("passwort");
		final Part filepart = request.getPart("profilbild"); 
		final String contenttype = filepart.getContentType(); 
		final String username = email.substring(0, 7); 
				
		HttpSession session = request.getSession(); 
		RequestDispatcher rd; 
				
		ProfileBean javabean = new ProfileBean(); 
		javabean.setVorname(vorname); 
		javabean.setNachname(nachname);
		javabean.setEmail(email); 
		javabean.setPasswort(passwort);
		javabean.setUsername(email.substring(0, 7));
		javabean.setFilename(filepart.getSubmittedFileName()); 
		
		LoginBean loginbean = new LoginBean(); 
		loginbean.setUsername(username);
		

						session.setAttribute("javabean", javabean);
	
						try(ByteArrayOutputStream baos = new ByteArrayOutputStream();
								InputStream is = filepart.getInputStream()){ 
								int i = 0; 
								while((i = is.read()) != -1) {
									baos.write(i);
								}
								if(contenttype.equalsIgnoreCase("image/jpeg") || contenttype.equalsIgnoreCase("image/jpg") || contenttype.equalsIgnoreCase("image/png")) {
									//https://javatutorial.net/java-servlet-file-upload
									javabean.setProfilbild(baos.toByteArray());
									baos.flush(); 
								}
								else {
									javabean.setProfilbild(null);
								}
							}catch (Exception ex){
								throw new ServletException(ex.getMessage()); 
							}
	
									try(Connection conn = ds.getConnection();
									PreparedStatement ps = conn.prepareStatement(
										"INSERT INTO thidb.Benutzer (Vorname, Nachname, Passwort, EMail, Username, Profilbild, Bildexist, Adminrechte) VALUES (?, ?, ?, ?, ?, ?, ?, ?)")){
									ps.setString(1, javabean.getVorname()); 
									ps.setString(2, javabean.getNachname()); 
									ps.setString(3, javabean.getPasswort()); 
									ps.setString(4, javabean.getEmail());
									ps.setString(5, javabean.getUsername());
									
									if(javabean.getProfilbild() == null) {
										ps.setInt(7, 0); 
									}
									else {
										ps.setInt(7, 1); 
									}

									ps.setBytes(6, javabean.getProfilbild());
									
									ps.setBoolean(8, false); 
									ResultSet res; 
											PreparedStatement pstmt = conn.prepareStatement(
												"SELECT * FROM thidb.Benutzer WHERE EMail Like ?"); 
												pstmt.setString(1, javabean.getEmail()); 
												res = pstmt.executeQuery(); 
												if(res.next()) {
													loginbean.setFehlermeldung("User " + loginbean.getUsername() + " bereits registriert!");
													loginbean.setUsername(null); //Header
													request.setAttribute("lb", loginbean); 
													rd = request.getRequestDispatcher("/home/jsp/registerForm.jsp");
													rd.forward(request, response);
												}
												else { 
													ps.executeUpdate();
													rd = request.getRequestDispatcher("/home/jsp/register.jsp"); 
													rd.forward(request, response);
												}
									}
									
								catch(Exception ex) {
									throw new ServletException(ex.getMessage()); 
								}
						
							}
}
	

