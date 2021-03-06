package library.service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Remote;

import library.domain.Book;

@Remote
public interface IBookService extends Serializable{

	Book addBook(Book book, String authorName);

	List<Book> findBooks(String query);
	
	Book findBookByISBN(String isbn);
	
	void deleteAllBooks();

}