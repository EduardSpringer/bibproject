//Eduard Springer

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
		request.setCharacterEncoding("UTF-8");

		String betreff = request.getParameter("betreff");
		String nachricht = request.getParameter("nachricht");
		String name = request.getParameter("name");
		String email = request.getParameter("email");

		ContactBean cb = new ContactBean();
		cb.setBetreff(betreff);
		cb.setNachricht(nachricht);
		cb.setName(name);
		cb.setEmail(email);
		cb.setStatus(false);// false = ungelesene Nachricht (für E-Mail-Verwaltung)

		HttpSession session = request.getSession();
		session.setAttribute("cb", cb);

		persist(cb);
		
		response.sendRedirect("home/jsp/contactAcception.jsp");
		/* Hier wird statt dem RequestDispatcher die Methode sendRedirect() verwendet,
		 * da beim "Refreshen" der Seite durch contactAcception.jsp eine erneute Anfrage der POST-Methode
		 * nicht möglich ist.*/
		
//		request.setAttribute("cb", cb);
//		RequestDispatcher disp = request.getRequestDispatcher("home/jsp/contactAcception.jsp"); 
//		disp.forward(request, response);
	}

	private void persist(ContactBean cb) throws ServletException {
		try (Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(
						"INSERT INTO thidb.kontakt (betreff,nachricht,name,email,status) VALUES (?,?,?,?,?)")) {

			pstmt.setString(1, cb.getBetreff());
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