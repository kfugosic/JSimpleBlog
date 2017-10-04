package hr.fer.zemris.java.hw15.model;

import javax.servlet.http.HttpServletRequest;

/**
 * Razred se koristi kako bi zapamtili trenutno stanje popunjavnaja forme kod registracije,
 * Tj. da se popunjeni podaci ne bi izgubili u slucaju da je nick zauzet.
 * 
 * @author Kristijan Fugosic
 *
 */
public class CurrentForm {

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
	 * Email
	 */
	private String email;
	
	/**
	 * Getter za ime
	 * @return Ime
	 */
	public String getFirstName() {
		return convert(firstName);
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
		return convert(lastName);
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
		return convert(nick);
	}
	/**
	 * Setter za nadimak
	 * @param nick Nadimak
	 */
	public void setNick(String nick) {
		this.nick = nick;
	}
	
	/**
	 * Getter za E-Mail
	 * @return E-Mail
	 */
	public String getEmail() {
		return convert(email);
	}
	/**
	 * Setter za E-Mail
	 * @param email E-Mail
	 */
	public void setEmail(String email) {
		this.email = email;
	}
		
	/**
	 * Na temelju parametara primljenih kroz {@link HttpServletRequest} popunjava
	 * svojstva ovog formulara.
	 * 
	 * @param req objekt s parametrima
	 */
	public void fillFromHTTPRequest(HttpServletRequest req) {
		this.firstName = req.getParameter("fname");
		this.lastName = req.getParameter("lname");
		this.nick = req.getParameter("nick");
		this.email = req.getParameter("email");
	}

	/**
	 * Temeljem sadržaja ovog formulara puni svojstva predanog domenskog
	 * objekta. Metodu ne bi trebalo pozivati ako formular prethodno nije
	 * validiran i ako nije utvrđeno da nema pogrešaka.
	 * 
	 * @param user domenski objekt koji treba napuniti
	 */
	public void transferDataToUser(BlogUser user) {
		user.setFirstName(this.firstName);
		user.setLastName(this.lastName);
		user.setEmail(this.email);
		user.setNick(this.nick);
	}

	/**
	 * Pomoćna metoda koja <code>null</code> stringove konvertira u prazne stringove, što je
	 * puno pogodnije za uporabu na webu.
	 * 
	 * @param s string
	 * @return primljeni string ako je različit od <code>null</code>, prazan string inače.
	 */
	private String convert(String s) {
		if(s==null) return "";
		return s.trim();
	}
	
}
