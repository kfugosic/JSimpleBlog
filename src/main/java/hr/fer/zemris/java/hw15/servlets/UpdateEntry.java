package hr.fer.zemris.java.hw15.servlets;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hr.fer.zemris.java.hw15.dao.DAOProvider;
import hr.fer.zemris.java.hw15.model.BlogEntry;
import hr.fer.zemris.java.hw15.model.BlogUser;
import hr.fer.zemris.java.hw15.model.CurrentUser;

/**
 * Servlet za izmjeni ili dodavanje novog blog entrya.
 * 
 * @author Kristijan Fugosic
 *
 */
@WebServlet("/servleti/updateentry")
public class UpdateEntry extends HttpServlet {

	/***/
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String nick = (String) req.getSession().getAttribute("pathNick");
		
		CurrentUser user = (CurrentUser) req.getSession().getAttribute("CurrentUser");
		BlogUser creator = DAOProvider.getDAO().findNickname(user.getNick());

		String title = req.getParameter("title");
		String text = req.getParameter("text");
		
		Long eid = (Long) req.getSession().getAttribute("eid");
		
		// Novi entry
		if(eid == null){
			
			BlogEntry entry = new BlogEntry();
			entry.setTitle(title);
			entry.setText(text);
			entry.setCreatedAt(new Date());
			entry.setLastModifiedAt(entry.getCreatedAt());
			entry.setCreator(creator);
			DAOProvider.getDAO().newEntry(entry);
			
		// Izmjena postojećeg entrya
		}else{
			
			BlogEntry entry = DAOProvider.getDAO().getBlogEntry(eid);
			entry.setTitle(title);
			entry.setText(text);
			entry.setLastModifiedAt(new Date());
			
		}

		resp.sendRedirect("/blog/servleti/author/"+nick);

	}

	
}