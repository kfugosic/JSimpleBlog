package hr.fer.zemris.java.hw15.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;

import hr.fer.zemris.java.hw15.dao.DAO;
import hr.fer.zemris.java.hw15.dao.DAOException;
import hr.fer.zemris.java.hw15.model.BlogComment;
import hr.fer.zemris.java.hw15.model.BlogEntry;
import hr.fer.zemris.java.hw15.model.BlogUser;

/**
 * Implementacija sučelja DAO.
 * @author Kristijan Fugosic
 *
 */
public class JPADAOImpl implements DAO {

	@Override
	public BlogEntry getBlogEntry(Long id) throws DAOException {
		BlogEntry blogEntry = JPAEMProvider.getEntityManager().find(BlogEntry.class, id);
		return blogEntry;
	}
	
	@Override
	public BlogUser findNickname(String nick) throws DAOException {
		EntityManager em = JPAEMProvider.getEntityManager();
		
		@SuppressWarnings("unchecked")
		List<BlogUser> users = 
				(List<BlogUser>)em.createQuery("select b from BlogUser as b where b.nick=:nickname")
					.setParameter("nickname", nick)
					.getResultList();
		
		if(users.isEmpty()) return null;
		return users.get(0);
	}
	
	@Override
	public List<BlogEntry> getEntriesForCreator(BlogUser creator) throws DAOException {
		EntityManager em = JPAEMProvider.getEntityManager();

		@SuppressWarnings("unchecked")
		List<BlogEntry> entries = 
				(List<BlogEntry>)em.createQuery("select b from BlogEntry as b where b.creator=:cr")
					.setParameter("cr", creator)
					.getResultList();

		return entries;
	}
	
	@Override
	public List<BlogComment> getCommentsForEntry(BlogEntry entry) throws DAOException {
		EntityManager em = JPAEMProvider.getEntityManager();

		@SuppressWarnings("unchecked")
		List<BlogComment> comments = 
				(List<BlogComment>)em.createQuery("select b from BlogComment as b where b.blogEntry=:en")
					.setParameter("en", entry)
					.getResultList();

		return comments;
	}
	
	@Override
	public List<String> getAllNicknames() throws DAOException {
		EntityManager em = JPAEMProvider.getEntityManager();
		
		@SuppressWarnings("unchecked")
		List<String> nicknames = 
				(List<String>)em.createQuery("select b.nick from BlogUser as b")
					.getResultList();
		
		return nicknames;

	}
	
	@Override
	public void newUser(BlogUser user) throws DAOException  {
		EntityManager em = JPAEMProvider.getEntityManager();
		em.persist(user);	
	}
	
	@Override
	public void newComment(BlogComment comment) throws DAOException  {	
		EntityManager em = JPAEMProvider.getEntityManager();
		em.persist(comment);	
	}
	
	@Override
	public void newEntry(BlogEntry entry) throws DAOException  {	
		EntityManager em = JPAEMProvider.getEntityManager();
		em.persist(entry);	
	}
	
}