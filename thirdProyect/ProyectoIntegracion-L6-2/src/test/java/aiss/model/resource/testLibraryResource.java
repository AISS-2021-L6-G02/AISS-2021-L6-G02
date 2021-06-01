package aiss.model.resource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Collection;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import aiss.api.resources.LibraryResource;
import aiss.model.Book;
import aiss.model.Library;

public class testLibraryResource {
	
	public static LibraryResource lr = new LibraryResource();
	public static Collection<Library> libs = null;
	
	@BeforeClass
	public static void setUp() throws Exception{
		libs = lr.getAllLibraries();
	}
	
	@Test
	public void testGetAllLibs() {
		Collection<Library> test = lr.getAllLibraries();
		assertNotNull("The collection of libraries is null", test);
		System.out.println("===========\ntestGetAllLibs\n=========");
		
		System.out.println("Listing all libs");
		for(Library f: test) {
			System.out.println(f);
		}
	}
	
	@Test
	public void testGetOneLib() {
		String id = libs.stream().findFirst().get().getId();
		Library lib = lr.getOneLibrary(id);
		assertNotNull("The lib is null", lib);
		assertEquals("The lib doesn't match with the obtained by getAllLibraries", lib.getId(), id);
		System.out.println("===========\ntestGetOneLib\n=========");
		System.out.println(lib);
	}
	
	@Test
	public void testAddLib() {
		Integer ac = 10;
		boolean open = true;
		String tName = "test lib";
		String loc = "location test";
		List<Book> books = libs.stream().findFirst().get().getBooks();
		Object films = libs.stream().findFirst().get().getFilms();
		Library test = new Library();
		
		test.setAvailableComputers(ac);
		test.setIsOpen(open);
		test.setName(tName);
		test.setLocation(loc);
		test.setBooks(books);
		test.setFilms(films);
		
		Library lib = lr.addLibrary(test);
		assertNotNull("Error when adding the library", lib);
		assertEquals("The library's avaible computers has not been settled correctly",ac, lib.getAvailableComputers());
		assertEquals("The library's open info has not been settled correctly",open, lib.getIsOpen());
		assertEquals("The library's name has not been settled correctly",tName, lib.getName());
		assertEquals("The library's location has not been settled correctly",loc, lib.getLocation());
		assertEquals("The library's book collection has not been settled correctly",books, lib.getBooks());
		assertEquals("The library's film collection has not been settled correctly",films, lib.getFilms());
		lr.deleteLibrary(lib.getId());
	}

}
