package library.service.impl;

import javax.annotation.security.RunAs;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import library.domain.Book;
import library.service.IBookService;
import library.service.IWareHouseService;

@Stateless
@RunAs("library")
public class DataImporter {

	@EJB
	IBookService bookService;
	@EJB
	IWareHouseService wareHouseService;
	
	private void addBook(String title, String authorName, int quantity, int price){
		Book book = new Book();
		book.setTitle(title);
		book.setSource(Book.Source.Library);
		book = bookService.addBook(book, authorName);
		wareHouseService.setPrice(book.getISBN(), price);
		wareHouseService.increaseStock(book.getISBN(), quantity);
	}

	public void importData(){
		bookService.deleteAllBooks();
    	addBook("The Art of War","Sun Tzu", 10, 2);
    	addBook("Phaedrus","Plato", 9, 3);
    	addBook("Metaphysics","Plato", 8, 4);
    	addBook("The Republic","Plato", 7, 5);
    	addBook("War and Peace","Leo Tolstoy", 6, 6);
	}
}
