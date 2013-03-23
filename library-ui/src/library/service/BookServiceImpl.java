package library.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import library.domain.Author;
import library.domain.Book;

@Stateless
public class BookServiceImpl implements IBookService {
	
	@PersistenceContext
	EntityManager em;

	@EJB
	IIsbnService isbnService;

	@Override
	public void addBook(Book book, String authorName){
		Author author = null;
		if (book.getISBN()==null){
			String isbn = isbnService.allocateNewIsbn();
			book.setISBN(isbn);
		}
		List<Author> authors = em.createQuery("SELECT author FROM Author author where author.name = :n").setParameter("n", authorName).getResultList();
		if (!authors.isEmpty()){
			author = authors.get(0);
		} else{
			author = new Author();
			author.setName(authorName);
		}
		author.getBooks().add(book);
		book.setAuthor(author);
		em.persist(book);
		em.persist(author);
		
	}
	
	@Override
	public List<Book> getAllBooks(){
		return em.createQuery("SELECT book FROM Book book").getResultList();
	}

	@Override
	public List<Book> findBooks(String query){
		if (query == null)
			query="";
		query = query.toUpperCase();
		return em.createQuery("SELECT DISTINCT book FROM Book book where upper(book.title) like :q OR upper(book.author.name )like :q").setParameter("q", "%"+query+"%").getResultList();
	}
}
