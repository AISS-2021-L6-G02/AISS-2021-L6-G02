package aiss.model.resource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Collection;

import org.junit.BeforeClass;
import org.junit.Test;

import aiss.api.resources.FilmResource;
import aiss.model.Film;

public class TestFilmResource {
	
	public static FilmResource fr = new FilmResource();
	public static Collection<Film> films = null;

	@BeforeClass
	public static void setUp() throws Exception{
		films = fr.getAllFilms();
	}
	
	@Test
	public void testGetAllFilms() {
		Collection<Film> test = fr.getAllFilms();
		assertNotNull("The collection of films is null", test);
System.out.println("===========\ntestGetAllFilms\n=========");
		
		System.out.println("Listing all films");
		for(Film f: test) {
			System.out.println(f);
		}
	}
	
	@Test
	public void testGetOneFilm() {
		String id = films.stream().findFirst().get().getId();
		Film film = fr.getFilm(id);
		assertNotNull("The film is null", film);
		assertEquals("The film doesn't match with the obtained by getAllFilms()",film.getId(),id);
		System.out.println("===========\ntestGetOneFilm\n=========");
		System.out.println(film);
	}
	
	@Test
	public void testAddFilm() {
		String title = "Film de prueba";
		String director = "Director de prueba";
		Integer duration = 99999;
		String date = "Fecha de prueba";
		String genre = "Genero de prueba";
		Boolean status = true;
		Film test = new Film();
		
		test.setTitle(title);
		test.setDirector(director);
		test.setDuration(duration);
		test.setDate(date);
		test.setGenre(genre);
		test.setStatus(status);
		
		Film f = fr.addFilm(test);
		assertNotNull("Error when adding the film", f);
		assertEquals("The film's title has not been settled correctly", title,f.getTitle());
		assertEquals("The film's director has not been settled correctly", director,f.getDirector());
		assertEquals("The film's duration has not been settled correctly", duration,f.getDuration());
		assertEquals("The film's date has not been settled correctly", date,f.getDate());
		assertEquals("The film's genre has not been settled correctly", genre,f.getGenre());
		assertEquals("The film's status has not been settled correctly", status,f.getStatus());
		fr.deleteFilm(f.getId());
	}
}
