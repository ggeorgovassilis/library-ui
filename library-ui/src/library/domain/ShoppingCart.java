package library.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import library.service.IWareHouseService;

@Stateful
public class ShoppingCart implements Serializable {

	private List<OrderItem> items = new ArrayList<OrderItem>();
	@EJB
	IWareHouseService wareHouseService;

	public List<OrderItem> getItems() {
		return items;
	}

	public void removeItem(String isbn) {
		for (OrderItem item : items)
			if (item.getBook().getISBN().equals(isbn)) {
				item.setQuantity(item.getQuantity()-1);
				int unitPrice = wareHouseService.getStock(isbn).getPrice();
				item.setPrice(item.getQuantity()*unitPrice);
				if (item.getQuantity()<=0)
					items.remove(item);
				break;
			}
	}

	public void addItem(Book book) {
		OrderItem line = null;
		for (OrderItem item : items)
			if (item.getBook().getISBN().equals(book.getISBN())) {
				line = item;
				break;
			}
		if (line == null) {
			line = new OrderItem();
			items.add(line);
		}
		line.setBook(book);
		line.setQuantity(line.getQuantity() + 1);
		int unitPrice = wareHouseService.getStock(book.getISBN()).getPrice();
		line.setPrice(line.getQuantity() * unitPrice);
	}
	
	public void emptyCart(){
		items.clear();
	}
	
	public void checkout(){
		
	}

}
