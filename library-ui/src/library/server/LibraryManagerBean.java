package library.server;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import library.domain.BookEntity;


@Stateless
@Named
public class LibraryManagerBean{

	@PersistenceContext
	EntityManager em;
	
	public void addBook(BookEntity book){
		em.persist(book);
	}
	
	public List<BookEntity> getAllBooks() {
		return em.createQuery("SELECT book FROM BookEntity book").getResultList();
	}

}
