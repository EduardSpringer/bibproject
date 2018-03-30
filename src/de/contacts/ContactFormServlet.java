package de.contacts;

import java.io.IOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.sql.DataSource;

import javax.annotation.Resource;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import javabeans.ContactBean;

@WebServlet("/contactformservlet")
public class ContactFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Resource(lookup = "jdbc/MyTHIPool")
	private DataSource ds;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String betreff = request.getParameter("betreff");
		String nachricht = request.getParameter("nachricht");
		String name = request.getParameter("name");
		String email = request.getParameter("email");

		ContactBean cb = new ContactBean();

		cb.setBetreff(betreff);
		cb.setNachricht(nachricht);
		cb.setName(name);
		cb.setEmail(email);
		cb.setStatus(false);// 0 als Status in der DB für 'ungelesen' im Admin-Panel

		HttpSession session = request.getSession();
		session.setAttribute("cb", cb); // für die aktuelle Sitzung 

		persist(cb); // für DB-Zugriff

		response.sendRedirect("home/jsp/contactAcception.jsp"); //Weiterleitung an eine JSP als Antwort
	}

	private void persist(ContactBean cb) throws ServletException {

		try (Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(
						"INSERT INTO kontakt (betreff,nachricht,name,email,status) VALUES (?,?,?,?,?)")) { /*Platzhalter für Werte in DBS*/

			pstmt.setString(1, cb.getBetreff()); //Zugriff auf Beans
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

/* Eduard Springer */
