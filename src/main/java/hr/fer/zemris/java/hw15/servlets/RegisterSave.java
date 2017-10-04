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
import hr.fer.zemris.java.hw15.model.CurrentForm;
import hr.fer.zemris.java.hw15.model.CurrentUser;

/**
 * Servlet za registraciju korisnika.
 * Provjerava se je li odabrani nick zauzet, ostale provjere poput nepraznih argumenata
 * i maksimalne duljine stringova rješavaju se u .jsp datoteci.
 * Lozinka se enkriptira prije spremanja (SHA-256).
 * @author Kristijan Fugosic
 *
 */
@WebServlet("/servleti/register")
public class RegisterSave extends HttpServlet {

	/***/
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		CurrentForm f = new CurrentForm();
		f.fillFromHTTPRequest(req);
	
		String nick = req.getParameter("nick");
		String password = req.getParameter("password");

		if(!nickValid(nick)){
			req.setAttribute("form", f);
			req.getSession().setAttribute("nickExists", "yes");
			req.getRequestDispatcher("/WEB-INF/pages/Register.jsp").forward(req, resp);
			return;
		}

		BlogUser user = new BlogUser();
		
		f.transferDataToUser(user);		
		String pw = Crypto.calcHash(password);
		user.setPasswordHash(pw);

		DAOProvider.getDAO().newUser(user);
				
		CurrentUser cUser = new CurrentUser(user.getId(), user.getFirstName(), user.getLastName(), nick);

		req.getSession().setAttribute("CurrentUser", cUser);

		req.getRequestDispatcher("/WEB-INF/pages/RegistrationCompleted.jsp").forward(req, resp);
	}

	/**
	 * Ako nadimak vec postoji u bazi tada nije ispravan (zauzet je).
	 * 
	 * @param nick Nadimak
	 * @return True ako nadimak ne postoji u bazi, false ako je zauzet.
	 */
	private boolean nickValid(String nick) {
		if(DAOProvider.getDAO().findNickname(nick) == null){
			return true;
		}
		return false;
	}
	
}