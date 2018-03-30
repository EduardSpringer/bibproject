package de.login;

import java.io.IOException;
import java.io.PrintWriter;
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
			try(ResultSet rsSelect = pstmt2.executeQuery();){
			
				if(rsSelect != null && rsSelect.next()) {
					benutzer.setPasswort(rsSelect.getString("Passwort"));
				}
				else {
					benutzer = null;
					return benutzer;
				}
			}
						
		} catch (Exception e) {
			throw new ServletException(e.getMessage());
		}
		
		System.out.println("user: " + benutzer.getUsername());
		System.out.println("passwort: " + benutzer.getPasswort());
		return benutzer;
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		
			response.setStatus(HttpServletResponse.SC_OK);	// nicht zwingend erforderlich; ist der default-Wert
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			
			final PrintWriter out = response.getWriter();
			String username=request.getParameter("username");
		    String password=request.getParameter("password");			
			String loginbutton = request.getParameter("loginbutton");
			String checkbox = request.getParameter("check");
			if (checkbox == null) {
				checkbox ="-";
			}
			System.out.println(checkbox);
		    	  
			switch(loginbutton) {
			
				case "login":
					
					LoginBean benutzer = getLoginData(username);
					
					if(benutzer == null) {
						response.sendRedirect("login.jsp");
						System.out.println("du bist nicht registriert");
					}
					else {		
					
						if(password.equals(benutzer.getPasswort())){
		
							HttpSession session = request.getSession();
							session.setAttribute("lb", benutzer);
						
							if(checkbox.equals("merken")) {
								Cookie cookie1 = new Cookie("usernameCookie", username);
								cookie1.setMaxAge(60 * 60 * 24);
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
						
							if(username.equals("admin")) {
								response.sendRedirect("home/html/admin.html");
							}
							else {
								response.sendRedirect("home/index.jsp");
							}
						}
						
						else {
							System.out.println("falsches passwort");
							response.sendRedirect("home/jsp/login.jsp");
						}
					}
									
					break;
			}
	}

}
