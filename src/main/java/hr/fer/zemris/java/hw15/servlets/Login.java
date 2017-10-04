package hr.fer.zemris.java.hw15.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hr.fer.zemris.java.hw06.crypto.Crypto;
import hr.fer.zemris.java.hw15.dao.DAOProvider;
import hr.fer.zemris.java.hw15.model.BlogUser;
import hr.fer.zemris.java.hw15.model.CurrentUser;

/**
 * Servler za logiranje korisnika.
 * 
 * @author Kristijan Fugosic
 *
 */
@WebServlet("/servleti/login")
public class Login extends HttpServlet {

	/***/
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String nick = req.getParameter("nick");
		String password = Crypto.calcHash(req.getParameter("password"));

		BlogUser user = DAOProvider.getDAO().findNickname(nick);
		if(user == null || !password.equals(user.getPasswordHash())){
			loginError(req, resp, nick);
			return;
		}
			
		CurrentUser cUser = new CurrentUser(
				user.getId(), user.getFirstName(), user.getLastName(), nick);

		req.getSession().setAttribute("CurrentUser", cUser);

		resp.sendRedirect("/blog/servleti/main");

	}

	/**
	 * Namjesti atribute i posalji error korisniku.
	 * @param req Http request
	 * @param resp Http response
	 * @param nick Nadimak
	 * @throws ServletException Mogući exception
	 * @throws IOException Mogući exception
	 */
	private void loginError(HttpServletRequest req, HttpServletResponse resp, String nick) throws ServletException, IOException {

		req.getSession().setAttribute("chosenusr", nick);
		req.getSession().setAttribute("loginError", "err");
		
		req.getRequestDispatcher("/WEB-INF/pages/Main.jsp").forward(req, resp);
	}
	
}