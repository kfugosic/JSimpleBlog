package hr.fer.zemris.java.hw15.dao.jpa;

import javax.persistence.EntityManagerFactory;

/**
 * Razred koji čuva primjerak EntityManagerFactory-a.
 * @author Kristijan Fugosic
 *
 */
public class JPAEMFProvider {

	/**
	 * Primjerak EntityManagerFactory-a.
	 */
	public static EntityManagerFactory emf;
	
	/**
	 * Getter za EMF
	 * @return EMF
	 */
	public static EntityManagerFactory getEmf() {
		return emf;
	}
	
	/**
	 * Setter za EMF
	 * @param emf EMF
	 */
	public static void setEmf(EntityManagerFactory emf) {
		JPAEMFProvider.emf = emf;
	}
}