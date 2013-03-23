package library.service;

import java.io.Serializable;

import javax.ejb.Remote;

import library.domain.Stock;

@Remote
public interface IWareHouseService extends Serializable{

	boolean isBookInStock(String isbn);
	void increaseStock(String isbn, int amount);
	void decreaseStock(String isbn, int amount);
	void setPrice(String isbn, int price);
	Stock getStock(String isbn);
}
