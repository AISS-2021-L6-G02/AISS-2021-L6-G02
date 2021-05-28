package aiss.api.resources;

import java.util.Arrays;
import java.util.Collection;

import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

import aiss.model.Book;

public class BookResource {
	private static BookResource instance = null;
	private String uri = "https://proyecto-integracion-l3-g6.appspot.com/api/books";

	
	
	public static BookResource getInstance() {
		if(instance==null) {
			instance = new BookResource();
		}
		return instance;
	}
	
	public Collection<Book> getAllBooks(){
		ClientResource cr = null;
		Book[] libros = null;
		try {
			cr = new ClientResource(uri);
			libros = cr.get(Book[].class);
			
		} catch(ResourceException re) {
			System.err.println("Error when retrieving all books: " + cr.getResponse().getStatus());
			throw re;
		}
		for(int i = 0; i<libros.length; i++) {
			System.out.println((i+1) + ": " + libros[i].getTitle());
		}
		return Arrays.asList(libros);
	}
	public Book getOneBook(String id) {
		ClientResource cr = null;
		Book b = null;
		try {
			cr = new ClientResource(uri + "/" + id);
			b = cr.get(Book.class);
		} catch(ResourceException re) {
			System.err.println("Error when retrieving one book: " + cr.getResponse().getStatus());
			throw re;
		}
		return b;
	}
	
	public Book addBook(Book b) {
		ClientResource cr = null;
		Book libro = null;
		try {
			cr = new ClientResource(uri);
			cr.setEntityBuffering(true);
			libro = cr.post(b, Book.class);
		} catch(ResourceException re) {
			System.err.println("Error when adding the book: " + cr.getResponse().getStatus());
			throw re;
		}
		
		return libro;
	}
	public boolean updateBook(Book b) {
		//TODO
		return false;
	}
	public boolean deleteBook(String id) {
		//TODO
		return false;
	}
	public BookResource() {
		
	}
}
