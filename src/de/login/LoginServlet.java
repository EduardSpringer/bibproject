package de.login;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
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
		
		
		LoginBean benutzer = new LoginBean();
			
		try(Connection con = ds.getConnection();
			//PreparedStatement pstmt = con.prepareStatement("USE thidb;");
			//ResultSet rsUse = pstmt.executeQuery();
			PreparedStatement pstmt2 = con.prepareStatement("SELECT * FROM thidb.benutzer WHERE username = ?")){
				
			pstmt2.setString(1, usern);
			try(ResultSet rsSelect = pstmt2.executeQuery();){ //Username wird gesucht
			
				if(rsSelect != null && rsSelect.next()) { //Username gefunden
					benutzer.setPasswort(rsSelect.getString("Passwort")); // Passwort des Users in LoginBean einf�gen
					benutzer.setUsername(usern);
					benutzer.setBildexist(rsSelect.getBoolean("bildexist")); //true oder false
					benutzer.setAdminrechte(rsSelect.getBoolean("Adminrechte")); //true oder false
				
				}
				else { //Username nicht gefunden
					benutzer.setFehlermeldung("⚠ FEHLER:<br> Sie sind nicht registriert!");
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
		
			request.setCharacterEncoding("UTF-8");
		
			//Parameter aus dem Formular:
			String username=request.getParameter("username");
		    String password=request.getParameter("password");			
			String loginbutton = request.getParameter("loginbutton");
			String checkbox = request.getParameter("check");
			
			RequestDispatcher disp;
						
			if (checkbox == null) { 
				checkbox ="-"; //sonst NullPointerException Z.102
			}
			//System.out.println(checkbox);
		    	  
			switch(loginbutton) {
			
				case "login": //Buttonvalue
					
					LoginBean benutzer = getLoginData(username); //Userdaten aus DB holen
					HttpSession session = request.getSession();
					
					if(benutzer.getFehlermeldung() != null) { //User nicht gefunden						
						request.setAttribute("lb", benutzer); //request wird nur einmal angezeigt beim Aufruf
						disp = request.getRequestDispatcher("/home/jsp/login.jsp");
						disp.forward(request, response);
								
						//System.out.println("du bist nicht registriert");
					}
					
					else {		
						
						if(!password.equals(benutzer.getPasswort())) { //unterschiedliche Passw�rter
				
							benutzer.setFehlermeldung("⚠ FEHLER:<br> Das Passwort für den Benutzernamen " + 
																benutzer.getUsername()+ " ist falsch!");
							
							benutzer.setUsername(null); //F�r Headeranzeige relevant
							//System.out.println("falsches passwort");
							request.setAttribute("lb", benutzer); //request wird nur einmal angezeigt beim Aufruf
							disp = request.getRequestDispatcher("/home/jsp/login.jsp");
							disp.forward(request, response);
							
						}
												
						else{  //Anmeldedaten passen
														
							session.setAttribute("lb", benutzer);
							
							if(checkbox.equals("merken")) {
								Cookie cookie1 = new Cookie("usernameCookie", username);
								cookie1.setMaxAge(60 * 60 * 24); //Cookie f�r einen Tag
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
								else {
									System.out.println("keine Cookies gespeichert");
								}
							}
							//history.back()
							disp = request.getRequestDispatcher("/home/index.jsp");
							disp.forward(request, response);
						}
					}
					
					break;
			}
	}

}
