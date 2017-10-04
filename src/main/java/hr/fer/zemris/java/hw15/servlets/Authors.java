package hr.fer.zemris.java.hw15.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hr.fer.zemris.java.hw15.dao.DAOProvider;
import hr.fer.zemris.java.hw15.model.BlogComment;
import hr.fer.zemris.java.hw15.model.BlogEntry;
import hr.fer.zemris.java.hw15.model.BlogUser;

/**
 * Ukoliko je putanja zadan kao:
 *  - "/servleti/author/X", tada se korisniku prikazuje lista svih entrya od korisnika X
 *  - "/servleti/author/X/new ili edit, tada se korisniku prikazuje formular za uređivanje entrya
 *  - "/servleti/author/X/Y, gdje je Y id nekog entrya, tada se korisniku prikazuje 
 *   taj entry zajedno sa njegovim komentarima i mogucnoscu uređivanja entrya i dodavanja komentara
 *  
 *  Korisnik može dodat novi entry/unos samo ako se nalazi na svojoj podstranici/listi unosa!!!
 *  
 *  gdje je X nadimak nekog korisnika
 * @author Kristijan Fugosic
 *
 */
@WebServlet("/servleti/author/*")
public class Authors extends HttpServlet {

	/***/
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String pathInfo = req.getPathInfo();
		if(pathInfo == null || pathInfo.length() == 1){
			resp.sendRedirect("/blog/servleti/main");
			return;
		}
		if(pathInfo.endsWith("/")){
			pathInfo = pathInfo.substring(1, pathInfo.length()-1);
		}else{
			pathInfo = pathInfo.substring(1, pathInfo.length());
		}
		
		String[] pathParts = pathInfo.split("/");

		
		if(pathParts.length == 1){
			showEntriesList(pathParts, req, resp);
			return;
		}
		else if (pathParts.length == 2){
			
			if(pathParts[1].equals("new")){
				req.getSession().setAttribute("eid", null);
				req.getSession().setAttribute("formTitle", "");
				req.getSession().setAttribute("formText", "");
				req.getRequestDispatcher("/WEB-INF/pages/updateEntry.jsp").forward(req, resp);
				return;
			}
			if(pathParts[1].equals("edit")){
				BlogEntry current = (BlogEntry) req.getSession().getAttribute("currentEntry");
				req.getSession().setAttribute("formTitle", current.getTitle());
				req.getSession().setAttribute("formText", current.getText());
				req.getRequestDispatcher("/WEB-INF/pages/updateEntry.jsp").forward(req, resp);
				return;
			}
			else {
				showEntry(pathParts, req, resp);
				return;		
			}
		}
		
		sendError(req, resp);
	}

	/**
	 * Ukoliko je putanja zadan kao:
	 *  - "/servleti/author/X/Y, gdje je X nadimak korisnika, a Y id nekog entrya, tada se korisniku prikazuje 
	 *  taj entry zajedno sa njegovim komentarima i mogucnoscu uređivanja entrya i dodavanja komentara
	 *  
	 * @param pathParts Djelovi putanje
	 * @param req Http request
	 * @param resp Http response
	 * @throws ServletException Mogući exception
	 * @throws IOException Mogući exception
	 */
	private void showEntry(String[] pathParts, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long eid = null;
		try {
			eid = Long.parseLong(pathParts[1]);
		} catch (NumberFormatException e) {
			sendError(req, resp);
			return;
		}
		
		BlogEntry entry = DAOProvider.getDAO().getBlogEntry(eid);
		if(entry == null){
			sendError(req, resp);
			return;
		}
		
		List<BlogComment> comments = DAOProvider.getDAO().getCommentsForEntry(entry);
		if(comments.isEmpty()){
			comments=null;
		}
		req.getSession().setAttribute("eid", eid);
		req.getSession().setAttribute("currentEntry", entry);
		req.getSession().setAttribute("comments", comments);
		req.getRequestDispatcher("/WEB-INF/pages/showEntry.jsp").forward(req, resp);		
	}

	/**
	 * Ukoliko je putanja zadan kao:
	 *  - "/servleti/author/X", tada se korisniku prikazuje lista svih entrya od korisnika X
	 *  
	 * @param pathParts Djelovi putanje
	 * @param req Http request
	 * @param resp Http response
	 * @throws ServletException Mogući exception
	 * @throws IOException Mogući exception
	 */
	private void showEntriesList(String[] pathParts, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nick = pathParts[0];
		BlogUser user = DAOProvider.getDAO().findNickname(nick);
		List<BlogEntry> entries = null;
		if(user != null){
			 entries = DAOProvider.getDAO().getEntriesForCreator(user);
			 if(entries.isEmpty()){
				 entries = null;
			 }
		}
		
		req.getSession().setAttribute("entriesList", entries);
		req.getSession().setAttribute("pathNick", nick);
		req.getRequestDispatcher("/WEB-INF/pages/ListEntries.jsp").forward(req, resp);		
	}

	/**
	 * Posalji korisnika na pripremljeni jsp za pogreske
	 * @param req Http request
	 * @param resp Http response
	 * @throws ServletException Mogući exception
	 * @throws IOException Mogući exception
	 */
	private void sendError(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/pages/Error.jsp").forward(req, resp);	
	}

	
}