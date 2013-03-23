package library.service;

import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.annotation.security.RunAs;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import library.domain.Book;


@Stateless
@Named
@Path("/books")
public class LibraryService{

	@EJB
	IBookService bookService;
	@EJB
	IWareHouseService wareHouseService;
	@EJB
	DataImporter dataImporter;
	
    @PostConstruct
    public void init() {
    	dataImporter.importData();
    }

	public void addBook(Book book, String authorName){
		bookService.addBook(book, authorName);
		wareHouseService.increaseStock(book.getISBN(), 0);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{query}")
	public List<Book> search(@PathParam("query") String query){
		return bookService.findBooks(query);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/")
	public List<Book> getAllBooks() {
		return bookService.getAllBooks();
	}

}
