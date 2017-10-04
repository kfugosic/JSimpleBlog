package hr.fer.zemris.java.hw15.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hr.fer.zemris.java.hw15.model.CurrentForm;

/**
 * Servlet koji priprema atribute prije preusmjeravanja na jsp stranicu za registraciju.
 * 
 * @author Kristijan Fugosic
 *
 */
@WebServlet("/servleti/registerNew")
public class RegisterNewUser extends HttpServlet {

	/***/
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		CurrentForm f = new CurrentForm();
		
		req.getSession().setAttribute("form", f);		
		req.getSession().setAttribute("nickExists", null);

		req.getRequestDispatcher("/WEB-INF/pages/Register.jsp").forward(req, resp);

	}
	
}