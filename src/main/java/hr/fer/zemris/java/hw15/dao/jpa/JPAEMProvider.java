package hr.fer.zemris.java.hw15.dao.jpa;

import javax.persistence.EntityManager;

import hr.fer.zemris.java.hw15.dao.DAOException;


/**
 * Pohrana veza prema bazi podataka u ThreadLocal object.
 * 
 * @author Kristijan Fugosic
 *
 */
public class JPAEMProvider {

	/**
	 * Mapa čiji su ključevi identifikator dretve koji radi operaciju nad mapom.
	 */
	private static ThreadLocal<LocalData> locals = new ThreadLocal<>();

	/**
	 * Dohvati vezu.
	 * @return Veza prema bazi podataka.
	 */
	public static EntityManager getEntityManager() {
		LocalData ldata = locals.get();
		if(ldata==null) {
			ldata = new LocalData();
			ldata.em = JPAEMFProvider.getEmf().createEntityManager();
			ldata.em.getTransaction().begin();
			locals.set(ldata);
		}
		return ldata.em;
	}

	/**
	 * Zatvori vezu prema bazi.
	 * @throws DAOException Ako dođe do pogreške.
	 */
	public static void close() throws DAOException {
		LocalData ldata = locals.get();
		if(ldata==null) {
			return;
		}
		DAOException dex = null;
		try {
			ldata.em.getTransaction().commit();
		} catch(Exception ex) {
			dex = new DAOException("Unable to commit transaction.", ex);
		}
		try {
			ldata.em.close();
		} catch(Exception ex) {
			if(dex!=null) {
				dex = new DAOException("Unable to close entity manager.", ex);
			}
		}
		locals.remove();
		if(dex!=null) throw dex;
	}
	
	/**
	 * Enkapsulacija entity managera.
	 * @author Kristijan Fugosic
	 *
	 */
	private static class LocalData {
		/**
		 * Entity Manager
		 */
		EntityManager em;
	}
	
}