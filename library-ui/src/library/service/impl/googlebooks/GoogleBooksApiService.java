package library.service.impl.googlebooks;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.net.ssl.HttpsURLConnection;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import library.domain.Author;
import library.domain.Book;
import library.domain.Book.Source;
import library.dto.BookDTO;
import library.service.IBookService;

@Stateless
public class GoogleBooksApiService {

	
	private List<BookDTO> queryService(String query) throws Exception{
		List<BookDTO> books = new ArrayList<BookDTO>();
		URL url = new URL("https://www.googleapis.com/books/v1/volumes?q="+URLEncoder.encode(query));
		URLConnection connection = url.openConnection();
		connection.setDoInput(true);  
		connection.setDoOutput(true); 
		connection.setConnectTimeout(10000);
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, false);
	    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		InputStream in = connection.getInputStream();
		SearchResults results = mapper.readValue(in, SearchResults.class);
		for (BookItem bookItem:results.getItems()){
			VolumeInfo volume = bookItem.getVolumeInfo();
			BookDTO book = new BookDTO();
			book.setTitle(volume.getTitle());
			List<IndustryIdentifier> ii = volume.getIndustryIdentifiers();
			if (ii == null)
				continue;
			IndustryIdentifier firstIi = ii.get(0);
			book.setISBN(firstIi.getIdentifier());
			book.setAuthor(volume.getAuthors().get(0));
			book.setSource(Source.Google);
			books.add(book);
		}
		return books;
	}
	
	public List<BookDTO> findBooks(String query){
		try{
			return queryService(query);
		}
		catch (RuntimeException re){
			throw re;
		}
		catch (Exception e){
			throw new RuntimeException(e);
		}
	}
	
}
