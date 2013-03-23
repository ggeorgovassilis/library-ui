package library.service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Remote;

import library.domain.Book;

@Remote
public interface IBookService extends Serializable{

	public abstract void addBook(Book book, String authorName);

	public abstract List<Book> getAllBooks();

	public abstract List<Book> findBooks(String query);

}