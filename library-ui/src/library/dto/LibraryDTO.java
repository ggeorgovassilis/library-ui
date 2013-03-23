package library.dto;


import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import library.domain.Author;
import library.domain.Book;
import library.service.LibraryService;


@ManagedBean
@RequestScoped
@Named
public class LibraryDTO implements Serializable{

	@EJB
	LibraryService service;

	private Book book = new Book();
	private String authorName = "";

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book entity) {
		this.book = entity;
	}
	
	public void search(){
		
	}

	public void store(){
		service.addBook(book, authorName);
	}

	public List<Book> getSearchResults() {
		return service.search(book.getTitle());
	}

	public List<Book> getAllBooks() {
		return service.getAllBooks();
	}
}
