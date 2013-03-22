package library.dto;


import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import library.domain.Book;
import library.server.LibraryService;


@ManagedBean
@RequestScoped
@Named
public class LibraryDTO implements Serializable{

	@EJB
	LibraryService bean;

	private Book entity = new Book();

	public Book getEntity() {
		return entity;
	}

	public void setEntity(Book entity) {
		this.entity = entity;
	}

	public void store(){
		bean.addBook(entity);
	}

	public List<Book> getAllBooks() {
		return bean.getAllBooks();
	}
}
