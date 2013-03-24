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
import library.domain.Book.Source;
import library.domain.ShoppingCart;
import library.domain.Stock;
import library.dto.BookDTO;
import library.service.IBookService;
import library.service.IWareHouseService;
import library.service.impl.googlebooks.GoogleBooksApiService;

@Stateless
@Named
@Path("/books")
public class LibraryService {

	@EJB
	IBookService bookService;
	
	@EJB
	GoogleBooksApiService googleBooksService;

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

	public void addBook(Book book, String authorName) {
		book = bookService.addBook(book, authorName);
		wareHouseService.increaseStock(book.getISBN(), 0);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{query}")
	public List<BookDTO> search(@PathParam("query") String query) {
		List<Book> books = bookService.findBooks(query);
		List<BookDTO> results = new ArrayList<BookDTO>();
		for (Book book : books)
			if (book.getSource() == Source.Library){
				Stock stock = wareHouseService.getStock(book.getISBN());
				BookDTO dto = new BookDTO();
				dto.setAuthor(book.getAuthor().getName());
				dto.setISBN(book.getISBN());
				dto.setTitle(book.getTitle());
				dto.setInStock(stock!=null && stock.getCount()>0);
				dto.setSource(book.getSource());
				results.add(dto);
			}

		if (query != null && !query.isEmpty())
			try {
				List<BookDTO> booksFromGoogle = googleBooksService
						.findBooks(query);
				results.addAll(booksFromGoogle);
			} catch (Exception e) {
				e.printStackTrace();
			}

		return results;
	}

}
