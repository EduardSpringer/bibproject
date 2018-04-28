//Eduard Springer

package de.contacts;

import java.io.IOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.sql.DataSource;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import javabeans.ContactBean;

@WebServlet("/contactformservlet")//Datenquelle durch Web-Container injizieren
public class ContactFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Resource(lookup = "jdbc/MyTHIPool")
	private DataSource ds;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		request.setCharacterEncoding("utf-8");

		String betreff = request.getParameter("betreff");//Zugriff auf die Felder(name-tag) in .jsp-Datei
		String nachricht = request.getParameter("nachricht");
		String name = request.getParameter("name");
		String email = request.getParameter("email");

		ContactBean cb = new ContactBean();//JavaBeans in einer Session hinterlegen
		cb.setBetreff(betreff);
		cb.setNachricht(nachricht);
		cb.setName(name);
		cb.setEmail(email);
		cb.setStatus(false);// 0 als Status in der DB für 'ungelesen' im Admin-Panel

		HttpSession session = request.getSession();//Nutzung einer Sitzung
		session.setAttribute("cb", cb);// JavaBean für die aktuelle Sitzung festlegen 

		persist(cb); // Übertragung der JavaBean an die DB
		
		response.sendRedirect("home/jsp/contactAcception.jsp"); //Weiterleitung an eine JSP als Antwort
		
//		Wegen "refresh" der Seite ist die Verwendung des Dispatchers nicht möglich!
//		request.setAttribute("cb", cb);
//		RequestDispatcher disp = request.getRequestDispatcher("home/jsp/contactAcception.jsp"); 
//		disp.forward(request, response);
	}

	private void persist(ContactBean cb) throws ServletException {
		//Verbindung zur DB herstellen und SQL-Anweisungen (INSERT) absetzen
		try (Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(
						"INSERT INTO kontakt (betreff,nachricht,name,email,status) VALUES (?,?,?,?,?)")) { /*Platzhalter fuer Werte in DBS*/

			pstmt.setString(1, cb.getBetreff()); //Zugriff auf die JavaBeans und Speicherung in die DB
			pstmt.setString(2, cb.getNachricht());
			pstmt.setString(3, cb.getName());
			pstmt.setString(4, cb.getEmail());
			pstmt.setBoolean(5, cb.getStatus());
			pstmt.executeUpdate();

		} catch (Exception ex) {
			throw new ServletException(ex.getMessage());
		}
	}
}