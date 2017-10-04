package hr.fer.zemris.java.hw15.dao;

/**
 * Iznimka ako dođe do pogreške u sklopu DAO.
 * @author Kristijan Fugosic
 *
 */
public class DAOException extends RuntimeException {

	/***/
	private static final long serialVersionUID = 1L;

	/**
	 * Konstruktor
	 * @param message Poruka
	 * @param cause Uzrok
	 */
	public DAOException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Konstruktor
	 * @param message Poruka
	 */
	public DAOException(String message) {
		super(message);
	}
}