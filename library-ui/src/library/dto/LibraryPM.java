package library.dto;


import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import library.domain.BookEntity;
import library.server.LibraryManagerBean;


@ManagedBean
@RequestScoped
@Named
public class LibraryPM implements Serializable{

	@EJB
	LibraryManagerBean bean;

	private BookEntity entity = new BookEntity();

	public BookEntity getEntity() {
		return entity;
	}

	public void setEntity(BookEntity entity) {
		this.entity = entity;
	}

	public void store(){
		bean.addBook(entity);
	}

	public List<BookEntity> getAllBooks() {
		return bean.getAllBooks();
	}
}
