package library.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Book implements Serializable {

	@Id
    private String ISBN;
	
	@Column(name = "TITLE", nullable = false)
	private String title;

    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name = "AUTHOR_ID", nullable = false)
	private Author author;
	
	public Author getAuthor() {
		return author;
	}


	public void setAuthor(Author author) {
		this.author = author;
	}


	public Book(){
	}
	
	
	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
