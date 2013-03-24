package library.dto;

import java.io.Serializable;

import library.domain.Book;
import library.domain.Stock;

public class BookDTO implements Serializable {

	private String ISBN;
	private String author;
	private String title;
	private Book.Source source;
	private boolean inStock;
	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Book.Source getSource() {
		return source;
	}
	public void setSource(Book.Source source) {
		this.source = source;
	}

	public boolean isInStock() {
		return inStock;
	}
	public void setInStock(boolean inStock) {
		this.inStock = inStock;
	}
	
}
