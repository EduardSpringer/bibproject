package de.login;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/logoutservlet")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LogoutServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		HttpSession session=request.getSession(false); 
		if (session != null){
			/*
			session.removeAttribute("username");
			session.removeAttribute("passwort");
			session.removeAttribute("fehlermeldung");
			session.removeAttribute("adminrechte");*/
			session.removeAttribute("lb");
			session.invalidate();  
        }
        
        final RequestDispatcher dispatcher = request.getRequestDispatcher("home/index.jsp");
		dispatcher.forward(request, response);
	}

}
