package library.server;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import library.domain.Book;


@Stateless
@Named
public class LibraryService{

	@PersistenceContext
	EntityManager em;
	
	public void addBook(Book book){
		em.persist(book);
	}
	
	public List<Book> getAllBooks() {
		return em.createQuery("SELECT book FROM Book book").getResultList();
	}

}
