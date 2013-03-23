package library.dto;


import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import library.domain.Book;
import library.domain.OrderItem;
import library.domain.ShoppingCart;
import library.service.IBookService;
import library.service.impl.LibraryService;


@ManagedBean
@SessionScoped
@Named
public class ShoppingCartForm implements Serializable{

	@EJB
	LibraryService service;
	@EJB
	IBookService bookService;
	@EJB
	ShoppingCart shoppingCart;
	
	public int getItemCount(){
		return shoppingCart.getItems().size();
	}

	public List<OrderItem> getItems(){
		return shoppingCart.getItems();
	}

	public void remove(String isbn){
		shoppingCart.removeItem(isbn);
	}

	public void addToShoppingCart(String isbn){
		Book book = bookService.findBookByISBN(isbn);
		if (book==null)
			throw new IllegalArgumentException("Didn't find book "+isbn);
		shoppingCart.addItem(book);
	}
	
	public void emptyCart(){
		shoppingCart.emptyCart();
	}

	public void buy(){
		shoppingCart.checkout();
	}

}
