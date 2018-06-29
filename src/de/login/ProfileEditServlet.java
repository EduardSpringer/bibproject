/**
 * Sara Viktoria Miller
 */
package de.login;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.Connection;
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

@WebServlet("/profileeditservlet")
@MultipartConfig(
		maxFileSize = 1024*1024, 
		location= "/tmp",
		fileSizeThreshold=1024*1024
)
public class ProfileEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(lookup="jdbc/MyTHIPool")
	private DataSource ds;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String vorname = request.getParameter("vorname");
		final String nachname = request.getParameter("nachname");
		final String passwort = request.getParameter("passwort");
		final String email = request.getParameter("email"); 
		final Part filepart = request.getPart("profilbild"); 
		final String contenttype = filepart.getContentType(); 
		final String username = request.getParameter("username"); 
		final String bild = request.getParameter("bild");
		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");
		RequestDispatcher rd; 
		ProfileBean javabean = new ProfileBean(); 
		javabean.setVorname(vorname);  
		javabean.setNachname(nachname);
		javabean.setPasswort(passwort);
		javabean.setEmail(email);
		javabean.setFilename(filepart.getSubmittedFileName()); 
		LoginBean loginbean = new LoginBean(); 
		loginbean.setUsername(username);
		if(username.isEmpty()) {
			loginbean.setFehlermeldung("Bitte einloggen!");
			request.setAttribute("lb", loginbean);
			rd = request.getRequestDispatcher("/home/jsp/login.jsp");
			rd.forward(request, response);
		}
		else {
			try(ByteArrayOutputStream baos = new ByteArrayOutputStream();
				InputStream is = filepart.getInputStream()){ 
				int i = 0; 
				while((i = is.read()) != -1) {
					baos.write(i);
				}
				//Dateityp überprüfen: https://javatutorial.net/java-servlet-file-upload
				if(!contenttype.isEmpty()) {
					if(contenttype.equalsIgnoreCase("image/jpeg") || contenttype.equalsIgnoreCase("image/jpg") || contenttype.equalsIgnoreCase("image/png")) {
						javabean.setProfilbild(baos.toByteArray());
						baos.flush(); 
					}
					else {
						javabean.setProfilbild(null);
					}
				}
			}catch (IOException ex){
				throw new ServletException(ex.getMessage());  
			}
			if(bild != null && javabean.getProfilbild() == null) {
				try(Connection con = ds.getConnection();    
					PreparedStatement ps = con.prepareStatement("UPDATE thidb.Benutzer SET Profilbild = NULL, Bildexist = 0 WHERE Username = ?")){
					ps.setString(1, loginbean.getUsername());
					ps.executeUpdate(); 
				}catch(Exception ex) {
					throw new ServletException(ex.getMessage()); 
				}
			}
			try(Connection con = ds.getConnection();    
				PreparedStatement ps = con.prepareStatement("UPDATE thidb.Benutzer SET Vorname = COALESCE(NULLIF(?, ''), Vorname), Nachname = COALESCE(NULLIF(?, ''), Nachname), Passwort = COALESCE(NULLIF(?, ''), Passwort), Profilbild = COALESCE(NULLIF(?, null), Profilbild), Bildexist = COALESCE(NULLIF(?, '2'), Bildexist) WHERE Username = ?")){
					ps.setString(1, javabean.getVorname()); 
					ps.setString(2, javabean.getNachname()); 
					ps.setString(3, javabean.getPasswort()); 
					ps.setBytes(4, javabean.getProfilbild());
				if(javabean.getProfilbild() != null) {
					ps.setInt(5, 1); 
					loginbean.setBildexist(true);
				}
				else {
					loginbean.setBildexist(false);
					ps.setInt(5, 2);
				}
				loginbean.setAdminrechte(javabean.getAdminrechte());
				session.setAttribute("lb", loginbean);
				ps.setString(6, loginbean.getUsername()); 
				javabean.setUsername(loginbean.getUsername());
				request.setAttribute("jb", javabean); 
				ps.executeUpdate(); 
				rd = request.getRequestDispatcher("/home/jsp/profileEdit.jsp");
				rd.forward(request, response);
			}	
			catch (Exception ex) {
				throw new ServletException(ex.getMessage()); 
			}
		}
	}
}