package hr.fer.zemris.java.hw15.dao;

import java.util.List;

import hr.fer.zemris.java.hw15.model.BlogComment;
import hr.fer.zemris.java.hw15.model.BlogEntry;
import hr.fer.zemris.java.hw15.model.BlogUser;

/**
 * Sučelje prema podsustavu za perzistenciju podataka.
 * 
 * @author Kristijan Fugosic
 *
 */
public interface DAO {

	/**
	 * Dohvaća entry sa zadanim <code>id</code>-em. Ako takav entry ne postoji,
	 * vraća <code>null</code>.
	 * 
	 * @param id ključ zapisa
	 * @return entry ili <code>null</code> ako entry ne postoji
	 * @throws DAOException ako dođe do pogreške pri dohvatu podataka
	 */
	public BlogEntry getBlogEntry(Long id) throws DAOException;

	/**
	 * Dohvaća korisnika za zadanim nadimkom, vraća null ako takav korisnik ne postoji.
	 * 
	 * @param nick Nadimak
	 * @return BlogUser - korisnik
	 * @throws DAOException ako dođe do pogreške pri dohvatu
	 */
	public BlogUser findNickname(String nick) throws DAOException;

	/**
	 * Vraća listu svih nadimaka u bazi
	 * @return Lista nadimaka
	 * @throws DAOException ako dođe do pogreške pri dohvatu
	 */
	List<String> getAllNicknames() throws DAOException;

	/**
	 * Vrati entrye koje je napravio dani korisnik.
	 * @param creator Autor entrya
	 * @return Lista entrya
	 * @throws DAOException ako dođe do pogreške pri dohvatu
	 */
	List<BlogEntry> getEntriesForCreator(BlogUser creator) throws DAOException;

	/**
	 * Vrati sve komentare za dani entry.
	 * @param entry Blog Entry
	 * @return Lista komentara
	 * @throws DAOException  ako dođe do pogreške pri dohvatu
	 */
	List<BlogComment> getCommentsForEntry(BlogEntry entry) throws DAOException;

	/**
	 * Spremi novog korisnika u bazu.
	 * @param user Novi korisnik
	 * @throws DAOException  ako dođe do pogreške pri dohvatu
	 */
	public void newUser(BlogUser user) throws DAOException;

	/**
	 * Spremi novi entry u bazu.
	 * @param entry Novi entry
	 * @throws DAOException  ako dođe do pogreške pri dohvatu
	 */
	public void newEntry(BlogEntry entry) throws DAOException;

	/**
	 * Spremi novi komentar u bazu.
	 * @param comment Novi komentar
	 * @throws DAOException  ako dođe do pogreške pri dohvatu
	 */
	void newComment(BlogComment comment) throws DAOException;
	
}