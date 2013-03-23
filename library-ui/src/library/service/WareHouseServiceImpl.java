package library.service;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import library.domain.Stock;

@Stateless
public class WareHouseServiceImpl implements IWareHouseService {

	@PersistenceContext
	EntityManager em;

	@Override
	public Stock getStock(String isbn) {
		List<Stock> results = em
				.createQuery(
						"SELECT stock FROM Stock stock WHERE stock.ISBN = :isbn")
				.setParameter("isbn", isbn).getResultList();
		if (results.isEmpty())
			return null;
		return results.get(0);
	}
	
	protected Stock getOrCreateStock(String isbn){
		Stock stock = getStock(isbn);
		if (stock == null) {
			stock = new Stock();
			stock.setISBN(isbn);
			stock.setPrice(0);
			stock.setCount(0);
		}
		return stock;
	}

	@Override
	public boolean isBookInStock(String isbn) {
		return getStock(isbn) != null;
	}

	
	
    @RolesAllowed({ "library" })
	@Override
	public void increaseStock(String isbn, int amount) {
		Stock stock = getOrCreateStock(isbn);
		stock.setCount(stock.getCount() + amount);
		em.persist(stock);
	}

    @RolesAllowed({ "library" })
	@Override
	public void decreaseStock(String isbn, int amount) {
		Stock stock = getStock(isbn);
		if (stock == null)
			throw new IllegalArgumentException("Don't have book "+isbn);
		if (stock.getCount()<amount)
			throw new IllegalArgumentException("Insufficient stock left for "+isbn);
		stock.setCount(stock.getCount() - amount);
		em.persist(stock);
	}

    @RolesAllowed({ "library" })
	@Override
	public void setPrice(String isbn, int price) {
		Stock stock = getOrCreateStock(isbn);
		stock.setPrice(price);
		em.persist(stock);
	}

}
