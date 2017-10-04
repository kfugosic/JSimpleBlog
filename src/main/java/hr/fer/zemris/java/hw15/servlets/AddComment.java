package hr.fer.zemris.java.hw15.servlets;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hr.fer.zemris.java.hw15.dao.DAOProvider;
import hr.fer.zemris.java.hw15.model.BlogComment;
import hr.fer.zemris.java.hw15.model.BlogEntry;

/**
 * Servlet koji se koristi pri dodavanju novih komentara.
 * 
 * @author Kristijan Fugosic
 *
 */
@WebServlet("/servleti/newcomment")
public class AddComment extends HttpServlet {

	/***/
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String nick = (String) req.getSession().getAttribute("pathNick");
		Long eid = (Long) req.getSession().getAttribute("eid");
		BlogEntry entry = (BlogEntry) req.getSession().getAttribute("currentEntry");
		
		String email = (String) req.getParameter("email");
		String msg = (String) req.getParameter("msg");
				
		if(eid == null || entry == null){
			req.getRequestDispatcher("/WEB-INF/pages/Error.jsp").forward(req, resp);	
			return;
		}else{
			BlogComment comment = new BlogComment();
			comment.setBlogEntry(entry);
			comment.setMessage(msg);
			comment.setUsersEMail(email);
			comment.setPostedOn(new Date());
			DAOProvider.getDAO().newComment(comment);
			
		}

		resp.sendRedirect("/blog/servleti/author/"+nick+"/"+eid);

	}

	
}