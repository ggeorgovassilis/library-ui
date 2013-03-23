package library.dto;


import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import library.service.impl.LibraryService;


@ManagedBean
@RequestScoped
@Named
public class SearchBooksForm implements Serializable{

	@EJB
	LibraryService service;

	private String query = "";

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}
	
	public void search(){
	}

	public List<BookDTO> getSearchResults() {
		return service.search(query);
	}

}
