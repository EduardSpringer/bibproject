package de.login;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import javabeans.LoginBean;

/**
 * Servlet implementation class BildServlet
 */
@WebServlet("/bildservlet")
public class BildServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Resource(lookup="jdbc/MyTHIPool")
	private DataSource ds;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		request.setCharacterEncoding("UTF-8");		
		String username = request.getParameter("username");

		
		try(Connection con = ds.getConnection();

			PreparedStatement pstmt = con.prepareStatement("SELECT profilbild FROM thidb.benutzer WHERE username = ?")){
				
			pstmt.setString(1,username);
			
			try(ResultSet rsSelect = pstmt.executeQuery();){ //Username wird gesucht
			
				if(rsSelect != null && rsSelect.next()) { //Username gefunden	
											
					
					Blob bild = rsSelect.getBlob("profilbild");
					response.reset();
					long length = bild.length();
					response.setHeader("Content-Length", String.valueOf(length));
					
		
						try(InputStream in = bild.getBinaryStream();){
							final int bufferSize = 256;
							byte[] buffer  = new byte[bufferSize];

							ServletOutputStream out = response.getOutputStream();
							while((length = in.read(buffer)) != -1) {
								out.write(buffer,0,(int) length);
							}
							out.flush();
							
						}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		} catch (SQLException e1) {
			
			e1.printStackTrace();
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
}
