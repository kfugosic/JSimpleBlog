package hr.fer.zemris.java.hw15.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Implementacija komentara na blogu.
 * @author Kristijan Fugosic
 *
 */
@Entity
@Table(name="blog_comments")
public class BlogComment {
	/**
	 * ID Komentara
	 */
	private Long id;
	/**
	 * Blog entry na koj je komentar objavljen
	 */
	private BlogEntry blogEntry;
	/**
	 * Email korisnika koji je objavio komentar
	 */
	private String usersEMail;
	/**
	 * Poruka/komentar
	 */
	private String message;
	/**
	 * Datum kada je komentar postavljen
	 */
	private Date postedOn;
	
	/**
	 * Getter za ID 
	 * @return ID
	 */
	@Id @GeneratedValue
	public Long getId() {
		return id;
	}
	
	/**
	 * Setter za ID
	 * @param id ID
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Getter za blog entry
	 * @return Blog entry gdje je komentar postavljen
	 */
	@ManyToOne
	@JoinColumn(nullable = false)
	public BlogEntry getBlogEntry() {
		return blogEntry;
	}
	
	/**
	 * Setter za blog entry
	 * @param blogEntry Blog entry
	 */
	public void setBlogEntry(BlogEntry blogEntry) {
		this.blogEntry = blogEntry;
	}

	/**
	 * Getter za email korisnika koji je postavio komentar
	 * @return E-Mail
	 */
	@Column(nullable = false, length = 30)
	public String getUsersEMail() {
		return usersEMail;
	}

	/**
	 * Setter za email korisnika koji je postavio komentar
	 * @param usersEMail E-Mail
	 */
	public void setUsersEMail(String usersEMail) {
		this.usersEMail = usersEMail;
	}

	/**
	 * Getter za poruku
	 * @return Komentar
	 */
	@Column(nullable = false, length = 4096)
	public String getMessage() {
		return message;
	}

	/**
	 * Setter za poruku
	 * @param message Komentar
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Getter za vrijeme postavljanja komentara
	 * @return Vrijeme postavljanja komentara
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	public Date getPostedOn() {
		return postedOn;
	}

	/**
	 * Getter za vrijeme postavljanja komentara
	 * @param postedOn Vrijeme postavljanja komentara
	 */
	public void setPostedOn(Date postedOn) {
		this.postedOn = postedOn;
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
		BlogComment other = (BlogComment) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}