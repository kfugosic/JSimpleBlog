package hr.fer.zemris.java.hw15.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hr.fer.zemris.java.hw15.dao.DAOProvider;

/**
 * Servlet podešava atribute i preusmjerava na pocetnu stranicu.
 * @author Kristijan Fugosic
 *
 */
@WebServlet(name = "HomepageRedirectServlet", urlPatterns = {"/index.html","/","/index.jsp","/servleti/main"})
public class HomepageRedirectServlet extends HttpServlet {

	/***/
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	
		List<String> nicknames = DAOProvider.getDAO().getAllNicknames();
		if(nicknames.isEmpty()){
			nicknames = null;
		}
		req.getSession().setAttribute("nicknames", nicknames);
		
		req.getSession().setAttribute("chosenusr", "");
		req.getSession().setAttribute("loginError", null);
	
		req.getRequestDispatcher("/WEB-INF/pages/Main.jsp").forward(req, resp);

	}
	
}