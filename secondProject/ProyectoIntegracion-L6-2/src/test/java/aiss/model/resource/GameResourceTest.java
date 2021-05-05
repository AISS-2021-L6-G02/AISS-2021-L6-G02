package aiss.model.resource;

import java.util.Collection;

import org.junit.BeforeClass;

import aiss.api.resources.DeveloperResource;
import aiss.api.resources.GameResource;
import aiss.api.resources.GenreResource;
import aiss.model.Developer;
import aiss.model.Game;
import aiss.model.Genre;

public class GameResourceTest {

	static Game g1, g2, g3;
	static Developer d1, d2, d3;
	static Genre gen;
	
	static GameResource gr = GameResource.getInstance();
	private static DeveloperResource dev = DeveloperResource.getInstance();
	private static GenreResource genres = GenreResource.getInstance();
	
	@BeforeClass
	public static void setup() {
		Game g;
		Collection<Game> test;
		Collection<Genre> genCol;
		
		Developer devTest;
		Genre genTest;
		
		g = new Game();
		devTest = new Developer();
		genTest = new Genre();
		
		devTest.setName("Test developer name");
		devTest.setCountry("Test developer country");
		devTest.setYear(2000);
		
		dev.addDeveloper(devTest);
		d1 = dev.getAll(null, devTest.getName(), devTest.getCountry(), devTest.getYear(), null, null).stream().findFirst().get();
		
		
		genTest.setName("Test genre name");
		genTest.setDescription("Test genre description");
		
		
		
		
		g.setTitle("Test title");
		g.setDescription("Test description");
		g.setDeveloper(devTest);
		g.setGenres(null);
	}
	
}
