package hr.fer.zemris.java.hw15.model;

/**
 * Trenutno aktivni korisnik
 * @author Kristijan Fugosic
 *
 */
public class CurrentUser {

	/**
	 * ID
	 */
	private Long id;
	/**
	 * Ime
	 */
	private String firstName;
	/**
	 * Prezime
	 */
	private String lastName;
	/**
	 * Nadimak
	 */
	private String nick;
	
	/**
	 * Konstruktor
	 */
	public CurrentUser() {
	}
	
	/**
	 * Konstruktor sa argumentima.
	 * @param id ID
	 * @param firstName Ime
	 * @param lastName Prezime
	 * @param nick Nadimak
	 */
	public CurrentUser(Long id, String firstName, String lastName, String nick) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.nick = nick;
	}
	
	/**
	 * Getter za ime
	 * @return Ime
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * Setter za ime
	 * @param firstName Ime
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	/**
	 * Getter za prezime
	 * @return Prezime
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * Setter za prezime
	 * @param lastName Prezime
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/**
	 * Getter za nadimak
	 * @return Nadimak
	 */
	public String getNick() {
		return nick;
	}
	/**
	 * Setter za nadimak
	 * @param nick Nadimak
	 */
	public void setNick(String nick) {
		this.nick = nick;
	}
	
	/**
	 * Getter za ID
	 * @return ID korisnika
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Setter za ID
	 * @param id ID korisnika
	 */
	public void setId(Long id) {
		this.id = id;
	}

}
