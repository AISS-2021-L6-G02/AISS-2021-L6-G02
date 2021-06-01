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
	@Test
	public void testAddBook() {
		
		String bookTitle = "Add book test title";
		String bookAuthor = "Add book test title";
		String bookDate = "2002";
		String bookDetails = "Add book test title";
		Boolean bookStatus = true;
		String bookGenre = "Fantasia";
		Book t = new Book();
		
		t.setTitle(bookTitle);
		t.setAuthor(bookAuthor);
		t.setDate(bookDate);
		t.setDetails(bookDetails);
		t.setStatus(bookStatus);
		t.setGenre(bookGenre);
		
		
		Book b = br.addBook(t);
		assertNotNull("Error when adding the book", b);
		assertEquals("The book's title has not been setted correctly", bookTitle, b.getTitle());
		assertEquals("The book's author has not been setted correctly", bookAuthor, b.getAuthor());
		assertEquals("The book's date has not been setted correctly", bookDate, b.getDate());
		assertEquals("The book's details have not been setted correctly", bookDetails, b.getDetails());
		assertEquals("The book's statur has not been setted correctly", bookStatus, b.getStatus());
		assertEquals("The book's genre has not been setted correctly", bookGenre, b.getGenre());
		br.deleteBook(b.getId());
	}
}
