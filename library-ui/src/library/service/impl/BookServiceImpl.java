package library.service.impl;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import library.domain.Author;
import library.domain.Book;
import library.service.IBookService;
import library.service.IIsbnService;

@Stateless
public class BookServiceImpl implements IBookService {
	
	@PersistenceContext
	EntityManager em;

	@EJB
	IIsbnService isbnService;

    @RolesAllowed({ "library" })
	@Override
	public Book addBook(Book book, String authorName){
		Author author = null;
		if (book.getISBN()==null){
			String isbn = isbnService.allocateNewIsbn();
			book.setISBN(isbn);
		}
		@SuppressWarnings("unchecked")
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
    	return book;
    }
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Book> findBooks(String query){
		if (query == null)
			query="";
		query = query.toUpperCase();
		return em.createQuery("SELECT DISTINCT book FROM Book book where upper(book.title) like :q OR upper(book.author.name )like :q").setParameter("q", "%"+query+"%").getResultList();
	}

	@Override
	public Book findBookByISBN(String isbn) {
		List<Book> books = em.createQuery("SELECT book FROM Book book where book.ISBN = :isbn").setParameter("isbn", isbn).getResultList();
		if (books.isEmpty())
			return null;
		return books.get(0);
	}

    @RolesAllowed({ "library" })
	@Override
	public void deleteAllBooks() {
    	for (Book book:findBooks(""))
    		em.remove(book);
	}

}
