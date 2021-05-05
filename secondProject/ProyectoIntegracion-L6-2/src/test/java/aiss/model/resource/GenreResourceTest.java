package aiss.model.resource;

import static org.junit.Assert.*;

import java.util.Collection;


import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;


import aiss.api.resources.GenreResource;
import aiss.model.Genre;
;

public class GenreResourceTest {
	static Genre g1, g2, g3, g4;
	
	static GenreResource r = new GenreResource();


	@BeforeClass
	public static void setUp() throws Exception {
		
		   
		g1 = new Genre("Test name1");
		g2 = new Genre("Test name2");
		g3 = new Genre("Test name3");
		g4 = new Genre("Test name4");
		r.addGenre(g1);
		r.addGenre(g2);
		r.addGenre(g3);
		r.addGenre(g4);
		}
	
	
	@AfterClass
	public static void tearDown() throws Exception {
		System.out.println("tearDown");
		r.removeGenre(g1.getId());
		r.removeGenre(g2.getId());
		r.removeGenre(g3.getId());
		r.removeGenre(g4.getId());

	}

	@Test
	public void testGetAll() {

		// basic getAll
		Collection<Genre> genres = r.getAll(null, null, null, null);
		Collection<Genre> ordered = r.getAll("name", null, null, null);
		Collection<Genre> reversedOrdered = r.getAll("-name", null, null, null);
		Collection<Genre> pagination = r.getAll(null, null, 2, 2);
		assertNotNull("The collection of genres is null", genres);

		// Show result
		System.out.println("========================================");
		System.out.println("Test getAllGenres basic:");
		System.out.println("========================================");

		int i = 1;
		for (Genre g1 : genres) {
			System.out.println("Genres " + i++ + " : " + g1.getName() + " (ID=" + g1.getId() + ")" + "(ID=" + g1.getDescription() + ")" );
			assertNotNull("The id of the genres is null" + g1.getId());
			assertNotNull("The name of the genres is null" + g1.getName());
			assertNotNull("The description of the genres is null" + g1.getDescription());


		}
		// Show result
		System.out.println("========================================");
		System.out.println("Test getAllGenres order=name:");
		System.out.println("========================================");
		assertNotNull("The collection of genres ordered by name is null", ordered);

		i = 1;
		for (Genre g2 : ordered) {
			System.out.println("Genres " + i++ + " : " + g2.getName() + " (ID=" + g2.getId() + ")" + "(ID=" + g2.getDescription() + ")" );
			assertNotNull("The id of the genres is null" + g2.getId());
			assertNotNull("The name of the genres is null" + g2.getName());
			assertNotNull("The description of the genres is null" + g2.getDescription());

		}
		System.out.println("========================================");
		System.out.println("Test getAllGenres order=-name:");
		System.out.println("========================================");
		assertNotNull("The collection of genres ordered by -name is null", ordered);

		i = 1;
		for (Genre g2 : reversedOrdered) {
			System.out.println("Genres " + i++ + " : " + g2.getName() + " (ID=" + g2.getId() + ")" + "(ID=" + g2.getDescription() + ")" );
			assertNotNull("The id of the genres is null" + g2.getId());
			assertNotNull("The name of the genres is null" + g2.getName());
			assertNotNull("The description of the genres is null" + g2.getDescription());


		}

		System.out.println("========================================");
		System.out.println("Test getAllGenres with pagination");
		System.out.println("========================================");
		assertNotNull("The collection with pagination is null", ordered);

		i = 1;
		for (Genre g2 : pagination) {
			System.out.println("Genres " + i++ + " : " + g2.getName() + " (ID=" + g2.getId() + ")" + "(ID=" + g2.getDescription() + ")" );
			assertNotNull("The id of the genres is null" + g2.getId());
			assertNotNull("The name of the genres is null" + g2.getName());
			assertNotNull("The description of the genres is null" + g2.getDescription());

		}

	}

	@Test
	public void testGetGenre() {
		Genre g = r.get(g1.getId());
		System.out.println("========================================");
		System.out.println("Test get one Genre:");
		System.out.println("========================================");
		assertEquals("The id of the genre do not match", g1.getId(), g.getId());
		assertEquals("The name of the genre do not match", g1.getName(), g.getName());
		assertEquals("The description of the genre do not match", g1.getDescription(), g.getDescription());

		// Show result
		System.out.println("Genre id: " + g.getId());
		System.out.println("Genre name: " + g.getName());
		System.out.println("Genre description: " + g.getDescription());

	}

	@Test
	public void testAddGenre() {
		System.out.println("========================================");
		System.out.println("Test add Genre:");
		System.out.println("========================================");
		String name = "Add genre test title";
		r.addGenre(new Genre(name)).getEntity();
		Genre g2 = new Genre();
		g2 = r.getAll(null, null, null, null).stream().filter(x -> x.getName().equals(name)).findFirst().get();
		System.out.println("Platform added --> " + g2);
		assertNotNull("Error when adding the genre", g2);
		assertEquals("The genre's name has not been setted correctly", name, g2.getName());

	}

	@Test
	public void testUpdateGenre() {
		System.out.println("========================================");
		System.out.println("Test update Genre:");
		System.out.println("========================================");
		String name = "Update Genre name test";

		Genre g = new Genre();
		g.setName(name);
		g.setId(g1.getId());
		g.setDescription(g1.getId());
		r.updateGenre(g);

		assertEquals("The Genre's name has not been updated correctly", name, g.getName());
		System.out.println("Updated Genre: " + r.get(g1.getId()));
	}

	@Test
	public void testDeleteGenre() {
		System.out.println("========================================");
		System.out.println("Test delete Genre:");
		System.out.println("========================================");
		System.out.println(r.get(g1.getId()));
		Genre copy = r.get(g1.getId());
		Boolean deleted = r.removeGenre(g1.getId()).equals(null);
		assertFalse("The Genre is not deleted", deleted);
		System.out.println("Success deleting Genre");
		r.addGenre(copy);

	}
}
