package library.service;

import java.io.Serializable;

import javax.ejb.Remote;

@Remote
public interface IIsbnService extends Serializable{

	boolean isValidIsbn(String isbn);
	String allocateNewIsbn();

}
