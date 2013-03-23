package library.dto;

import java.io.Serializable;

import library.domain.Book;
import library.domain.Stock;

public class BookDTO implements Serializable {

	private Book book;
	private Stock stock;

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}
}
