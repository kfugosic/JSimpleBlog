package hr.fer.zemris.java.hw15.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * Implementacija entrya na blogu.
 * @author Kristijan Fugosic
 *
 */
@NamedQueries({
	@NamedQuery(name="BlogEntry.upit1",query="select b from BlogComment as b where b.blogEntry=:be and b.postedOn>:when")
})
@Entity
@Table(name="blog_entries")
@Cacheable(true)
public class BlogEntry {
	/**
	 * ID Entrya
	 */
	private Long id;
	/**
	 * Lista komentara na entry
	 */
	private List<BlogComment> comments = new ArrayList<>();
	/**
	 * Datum kreiranja
	 */
	private Date createdAt;
	/**
	 * Datum zadnje izmjene
	 */
	private Date lastModifiedAt;
	/**
	 * Naslov 
	 */
	private String title;
	/**
	 * Tekst, sadržaj
	 */
	private String text;
	/**
	 * Korisnik koji je napravio entry
	 */
	private BlogUser creator;
	
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
	 * Dohvati komentare.
	 * @return Lista komentara.
	 */
	@OneToMany(mappedBy="blogEntry", fetch = FetchType.LAZY, cascade=CascadeType.PERSIST, orphanRemoval=true)
	@OrderBy("postedOn")
	public List<BlogComment> getComments() {
		return comments;
	}
	
	/**
	 * Postavi komentare.
	 * @param comments Lista komentara.
	 */
	public void setComments(List<BlogComment> comments) {
		this.comments = comments;
	}

	/**
	 * Dohvati vrijeme kreiranja.
	 * @return Vrijeme kreiranja.
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	public Date getCreatedAt() {
		return createdAt;
	}

	/**
	 * Postavi vrijeme kreiranja.
	 * @param createdAt	Vrijeme kreiranja.
	 */
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * Dohvati vrijeme zadnje izmjene.
	 * @return Vrijeme zadnje izmjene.
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	public Date getLastModifiedAt() {
		return lastModifiedAt;
	}

	/**
	 * Postavi vrijeme zadnje izmjene.
	 * @param lastModifiedAt Vrijeme zadnje izmjene.
	 */
	public void setLastModifiedAt(Date lastModifiedAt) {
		this.lastModifiedAt = lastModifiedAt;
	}

	/**
	 * Dovhvati naslov.
	 * @return Naslov
	 */
	@Column(nullable = false, length = 200)
	public String getTitle() {
		return title;
	}

	/**
	 * Postavi naslov.
	 * @param title Naslov
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Dohvati sadržaj/tekst.
	 * @return Sadržaj/tekst
	 */
	@Column(nullable = false, length = 4096)
	public String getText() {
		return text;
	}


	/**
	 * Postavi sadržaj/tekst.
	 * @param text Sadržaj/tekst
	 */
	public void setText(String text) {
		this.text = text;
	}
	
	/**
	 * Dohvati autora.
	 * @return Autor
	 */
	@ManyToOne()
	@JoinColumn(nullable = false)
	public BlogUser getCreator() {
		return creator;
	}

	/**
	 * Postavi autora.
	 * @param creator Autor
	 */
	public void setCreator(BlogUser creator) {
		this.creator = creator;
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
		BlogEntry other = (BlogEntry) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}