package library.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import library.domain.Book;
import library.domain.ShoppingCart;
import library.domain.Stock;
import library.dto.BookDTO;
import library.service.IBookService;
import library.service.IWareHouseService;


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
	@EJB
	ShoppingCart shoppingCart;
	
    @PostConstruct
    public void init() {
    	dataImporter.importData();
    }

	public void addBook(Book book, String authorName){
		book = bookService.addBook(book, authorName);
		wareHouseService.increaseStock(book.getISBN(), 0);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{query}")
	public List<BookDTO> search(@PathParam("query") String query){
		List<Book> books = bookService.findBooks(query);
		List<BookDTO> results = new ArrayList<BookDTO>();
		for (Book book:books){
			Stock stock = wareHouseService.getStock(book.getISBN());
			BookDTO dto = new BookDTO();
			dto.setBook(book);
			dto.setStock(stock);
			results.add(dto);
		}
		return results;
	}
	
}
