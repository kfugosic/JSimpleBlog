package hr.fer.zemris.java.hw15.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet za odjavu korisnika.
 * 
 * @author Kristijan Fugosic
 *
 */
@WebServlet("/servleti/logout")
public class Logout extends HttpServlet {

	/***/
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.getSession().setAttribute("CurrentUser", null);

		resp.sendRedirect("/blog/servleti/main");
	}
	
}