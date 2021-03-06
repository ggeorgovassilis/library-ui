package library.dto;


import java.io.Serializable;

import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import library.domain.Book;
import library.domain.Book.Source;
import library.service.impl.LibraryService;


@ManagedBean
@RequestScoped
@Named
public class AddBooksForm implements Serializable{

	@EJB
	LibraryService service;

	private String authorName = "";
	private String bookTitle = "";

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}
	
	public String addBook(){
		Book book = new Book();
		book.setTitle(bookTitle);
		book.setSource(Source.UserContributed);
		service.addBook(book, authorName);
		return "index";
	}
	
}
