package library.service;

import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;
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

	private void addBook(String title, String authorName, int quantity, int price){
		Book book = new Book();
		book.setTitle(title);
		bookService.addBookInternal(book, authorName);
		wareHouseService.setPrice(book.getISBN(), price);
		wareHouseService.increaseStock(book.getISBN(), quantity);
	}
	
    @PostConstruct
    void init() {
    	addBook("The Art of War","Sun Tzu", 10, 2);
    	addBook("Phaedrus","Plato", 9, 3);
    	addBook("Metaphysics","Plato", 8, 4);
    	addBook("Metaphysics","Plato", 7, 5);
    	addBook("War and Peace","Leo Tolstoy", 6, 6);
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
