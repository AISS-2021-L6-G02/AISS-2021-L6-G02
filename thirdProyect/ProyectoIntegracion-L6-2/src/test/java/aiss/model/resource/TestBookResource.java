package aiss.model.resource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Collection;

import org.junit.BeforeClass;
import org.junit.Test;

import aiss.api.resources.BookResource;
import aiss.model.Book;

public class TestBookResource {
	public static BookResource br = new BookResource();
	public static Collection<Book> libros = null;

	
	@BeforeClass
	public static void setup() throws Exception{
		libros = br.getAllBooks();
	}
	@Test
	public void testGetAllBooks() {
		Collection<Book> test = br.getAllBooks();
		
		assertNotNull("The collection of books is null", test);
		System.out.println("===========\ntestGetAllBooks\n=========");
		
		System.out.println("Listing all books");
		
		for(Book b : test) {
			System.out.println(b);
		}
	}
	@Test
	public void testGetOneBook() {
		String id = libros.stream().findFirst().get().getId();
		Book libroTest = br.getOneBook(id);
		assertNotNull("The book is null", libroTest);
		assertEquals("The book doesn't match with the one obtained by getAllBooks()", libroTest.getId(), id);
		System.out.println("===========\ntestGetOneBook\n=========");
		System.out.println(libroTest);
	}
}
