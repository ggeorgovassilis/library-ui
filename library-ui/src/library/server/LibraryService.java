package library.server;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import library.domain.Book;
import javax.ws.rs.core.Response;


@Stateless
@Named
@Path("/books")
public class LibraryService{

	@PersistenceContext
	EntityManager em;
	
	public void addBook(Book book){
		em.persist(book);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{query}")
	public List<Book> search(@PathParam("query") String query){
		return em.createQuery("SELECT book FROM Book book where book.title like :q").setParameter("q", "%"+query+"%").getResultList();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/")
	public List<Book> getAllBooks() {
		return em.createQuery("SELECT book FROM Book book").getResultList();
	}

}
