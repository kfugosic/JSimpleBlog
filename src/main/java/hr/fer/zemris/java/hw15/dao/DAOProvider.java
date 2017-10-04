package hr.fer.zemris.java.hw15.dao;

import hr.fer.zemris.java.hw15.dao.jpa.JPADAOImpl;

/**
 * Singleton razred koji zna koga treba vratiti kao pružatelja
 * usluge pristupa podsustavu za perzistenciju podataka.
 * 
 * @author Kristijan Fugosic
 *
 */
public class DAOProvider {

	/**
	 * Spremljei primjerak.
	 */
	private static DAO dao = new JPADAOImpl();
	
	/**
	 * Dohvat primjerka.
	 * 
	 * @return objekt koji enkapsulira pristup sloju za perzistenciju podataka.
	 */
	public static DAO getDAO() {
		return dao;
	}
	
}