package hr.fer.zemris.java.hw15.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
/**
 * Implementacija korisnika na blogu.
 * @author Kristijan Fugosic
 *
 */
@Entity
@Table(name="blog_users")
@Cacheable(true)
public class BlogUser {
	/**
	 * Maksimalna duljina imena, prezima i nadimka.
	 */
	private static final int MAX_NAME_LENGTH = 20;
	/**
	 * Maksimalna duljina emaila.
	 */
	private static final  int MAX_EMAIL_LENGTH = 30;
	/**
	 * Maksimalna duljina enkriptirane lozinke.
	 */
	private static final  int HSHPW_LENGTH = 65;
	
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
	 * E-Mail
	 */
	private String email;
	/**
	 * Enkriptirana lozinka
	 */
	private String passwordHash;
	/**
	 * Svi unosi korisnika
	 */
	private List<BlogEntry> entries = new ArrayList<>();

	/**
	 * Dohvati ID.
	 * @return ID
	 */
	@Id @GeneratedValue
	public Long getId() {
		return id;
	}
	/**
	 * Postavi ID.
	 * @param id ID
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * Dohvati ime.
	 * @return Ime
	 */
	@Column(nullable = false, length = MAX_NAME_LENGTH)
	public String getFirstName() {
		return firstName;
	}
	/**
	 * Postavi ime.
	 * @param firstName Ime
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	/**
	 * Dohvati prezime.
	 * @return Prezime
	 */
	@Column(nullable = false, length = MAX_NAME_LENGTH)
	public String getLastName() {
		return lastName;
	}
	/**
	 * Postavi prezime.
	 * @param lastName Prezime
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/**
	 * Dohvati nadimak.
	 * @return Nadimak
	 */
	@Column(nullable = false, length = MAX_NAME_LENGTH, unique=true)
	public String getNick() {
		return nick;
	}
	/**
	 * Postavi nadimak.
	 * @param nick Nadimak
	 */
	public void setNick(String nick) {
		this.nick = nick;
	}
	
	/**
	 * Dohvati email.
	 * @return E-Mail
	 */
	@Column(nullable = false, length = MAX_EMAIL_LENGTH)
	public String getEmail() {
		return email;
	}
	/**
	 * Postavi email.
	 * @param email E-Mail
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * Dohvati enkriptiranu lozinku.
	 * @return Lozinka (SHA-256)
	 */
	@Column(nullable = false, length = HSHPW_LENGTH)
	public String getPasswordHash() {
		return passwordHash;
	}
	/**
	 * Postavi enkriptiranu lozinku.
	 * @param passwordHash Lozinka (SHA-256)
	 */
	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}
	
	/**
	 * Dohvati sve unose.
	 * @return Lista unosa.
	 */
	@OneToMany(mappedBy="creator", fetch = FetchType.LAZY, cascade=CascadeType.PERSIST, orphanRemoval=true)
	@OrderBy("createdAt")
	public List<BlogEntry> getEntries() {
		return entries;
	}
	/**
	 * Postavi sve unose.
	 * @param entries Lista unosa.
	 */
	public void setEntries(List<BlogEntry> entries) {
		this.entries = entries;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BlogUser other = (BlogUser) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


}
