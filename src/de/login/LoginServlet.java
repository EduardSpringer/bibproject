package de.login;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import javabeans.LoginBean;

@WebServlet("/loginservlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Resource(lookup="jdbc/MyTHIPool")
	private DataSource ds;
	
	
	private LoginBean getLoginData(String usern) throws ServletException{
		
		LoginBean benutzer = new LoginBean(usern);
			
		try(Connection con = ds.getConnection();
			//PreparedStatement pstmt = con.prepareStatement("USE thidb;");
			//ResultSet rsUse = pstmt.executeQuery();
			PreparedStatement pstmt2 = con.prepareStatement("SELECT * FROM thidb.benutzer WHERE username = ?")){
				
			pstmt2.setString(1, usern);
			try(ResultSet rsSelect = pstmt2.executeQuery();){ //Username wird gesucht
			
				if(rsSelect != null && rsSelect.next()) { //Username gefunden
					benutzer.setPasswort(rsSelect.getString("Passwort")); // Passwort des Users in LoginBean einfügen
				}
				else { //Username nicht gefunden
					benutzer.setFehlermeldung("Sie sind nicht registriert");
					benutzer.setUsername(null); //für den Headeranzeige wichtig 
					return benutzer;
				}
			}
						
		} catch (Exception e) {
			throw new ServletException(e.getMessage());
		}
		
		//System.out.println("user: " + benutzer.getUsername());
		//System.out.println("passwort: " + benutzer.getPasswort());
		return benutzer;
	}
	
		
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		
			response.setStatus(HttpServletResponse.SC_OK);	// nicht zwingend erforderlich; ist der default-Wert
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			
			//Parameter aus dem Formular:
			String username=request.getParameter("username");
		    String password=request.getParameter("password");			
			String loginbutton = request.getParameter("loginbutton");
			String checkbox = request.getParameter("check");
						
			//if (checkbox == null) {
			//	checkbox ="-";
			//}
			//System.out.println(checkbox);
		    	  
			switch(loginbutton) {
			
				case "login": //Buttonvalue
					
					LoginBean benutzer = getLoginData(username); //Userdaten aus DB holen
					
					if(benutzer.getFehlermeldung() != null) { //User nicht gefunden					
						response.sendRedirect("home/jsp/login.jsp");		
						//System.out.println("du bist nicht registriert");
					}
					else {		
						
						if(!password.equals(benutzer.getPasswort())) { //unterschiedliche Passwörter
							benutzer.setFehlermeldung("Falsches Passwort");
							benutzer.setUsername(null); //Für Headeranzeige relevant
							//System.out.println("falsches passwort");
							response.sendRedirect("home/jsp/login.jsp");
						}
												
						else{ 
						
							if(checkbox.equals("merken")) {
								Cookie cookie1 = new Cookie("usernameCookie", username);
								cookie1.setMaxAge(60 * 60 * 24); //Cookie für einen Tag
								cookie1.setPath("/");
								response.addCookie(cookie1);
								System.out.println("Cookie eingfügt");
								
								Cookie[] cookies = request.getCookies();
								if (cookies != null) {
									for (int i = 0; i< cookies.length; i++) {
										Cookie cookie = cookies[i];
										
										System.out.println(cookie.getName() + " " + cookie.getValue());		
									}
								}
								//else {
								//	System.out.println("keine Cookies gespeichert");
								//}
							}
						
							if(username.equals("admin")) {
								response.sendRedirect("home/html/admin.html");
							}
							else {
								response.sendRedirect("home/index.jsp");
							}
						}
					}
					
					HttpSession session = request.getSession();
					session.setAttribute("lb", benutzer);						
					break;
			}
	}

}
