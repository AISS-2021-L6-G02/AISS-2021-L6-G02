package aiss.model.resource;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import aiss.api.resources.DeveloperResource;
import aiss.api.resources.GameResource;
import aiss.api.resources.GenreResource;
import aiss.api.resources.PlatformResource;
import aiss.model.*;

public class GameResourceTest {

	static Game g1, g2, g3;
	static Developer d;
	static Genre gen;
	static Platform p;

	static GameResource gr = GameResource.getInstance();
	private static DeveloperResource dev = DeveloperResource.getInstance();
	private static GenreResource genres = GenreResource.getInstance();
	private static PlatformResource platforms = PlatformResource.getInstance();

	@BeforeClass
	public static void setup() throws Exception {
		Game g;
		List<Mode> testM = new ArrayList<Mode>();
		List<Genre> testG = new ArrayList<Genre>();
		List<Platform> testP = new ArrayList<Platform>();
		
		d = dev.getAll().stream().findFirst().get();
		gen = genres.getAll().stream().findFirst().get();
		p = platforms.getAll().stream().findFirst().get();
		
		testM.add(Mode.Individual);
		testG.add(gen);
		testP.add(p);

		g = new Game();
		g.setTitle("Test title");
		g.setDescription("Test description");
		g.setYear(2000);
		g.setDeveloper(d);
		g.setScore(5.);
		g.setPlatforms(testP);
		g.setGenres(testG);
		g.setModes(testM);
		gr.add(g);
		g1 = gr.getAll(null, g.getTitle(), g.getYear(), g.getDeveloper().getName(), g.getScore(), g.getPlatforms().get(0).getName(), g.getGenres().get(0).getName(), g.getModes().get(0), null, null).stream().findFirst().get();

		g = new Game();
		g.setTitle("Test title 2");
		g.setDescription("Test description 2");
		g.setYear(2000);
		g.setDeveloper(d);
		g.setScore(5.);
		g.setPlatforms(testP);
		g.setGenres(testG);
		g.setModes(testM);
		gr.add(g);
		g2 = gr.getAll(null, g.getTitle(), g.getYear(), g.getDeveloper().getName(), g.getScore(), g.getPlatforms().get(0).getName(), g.getGenres().get(0).getName(), g.getModes().get(0), null, null).stream().findFirst().get();



		g = new Game();
		g.setTitle("Test title 3");
		g.setDescription("Test description 3");
		g.setYear(2000);
		g.setDeveloper(d);
		g.setScore(5.);
		g.setPlatforms(testP);
		g.setGenres(testG);
		g.setModes(testM);
		gr.add(g);
		g3 = gr.getAll(null, g.getTitle(), g.getYear(), g.getDeveloper().getName(), g.getScore(), g.getPlatforms().get(0).getName(), g.getGenres().get(0).getName(), g.getModes().get(0), null, null).stream().findFirst().get();
	}

	@AfterClass
	public static void tearDown() {
		gr.delete(g1.getId());
		gr.delete(g2.getId());
		gr.delete(g3.getId());
	}

	@Test
	public void testGetAll() {
		Collection<Game> games = gr.getAll();

		Collection<Game> gamesOrderTitle = gr.getAll("title", null, null, null, null, null, null, null, null, null);
		Collection<Game> gamesOrderTitleReverse = gr.getAll("-title", null, null, null, null, null, null, null, null, null);

		Collection<Game> gamesOrderYear = gr.getAll("year", null, null, null, null, null, null, null, null, null);
		Collection<Game> gamesOrderYearReverse = gr.getAll("-year", null, null, null, null, null, null, null, null, null);

		Collection<Game> gamesOrderDev = gr.getAll("developerName", null, null, null, null, null, null, null, null, null);
		Collection<Game> gamesOrderDevReverse = gr.getAll("-developerName", null, null, null, null, null, null, null, null, null);

		Collection<Game> gamesOrderScore = gr.getAll("score", null, null, null, null, null, null, null, null, null);
		Collection<Game> gamesOrderScoreReverse = gr.getAll("-score", null, null, null, null, null, null, null, null, null);

		Collection<Game> gamesOrderGenre = gr.getAll("genreName", null, null, null, null, null, null, null, null, null);
		Collection<Game> gamesOrderGenreReverse = gr.getAll("-genreName", null, null, null, null, null, null, null, null, null);

		Collection<Game> gamesFiltroTitle = gr.getAll(null, "Animal Crossing", null, null, null, null, null, null, null, null);
		Collection<Game> gamesFiltroYear = gr.getAll(null, null, 2020, null, null, null, null, null, null, null);
		Collection<Game> gamesFiltroDev = gr.getAll(null, null, null, "Nintendo", null, null, null, null, null, null);
		Collection<Game> gamesFiltroScore = gr.getAll(null, null, null, null, 9., null, null, null, null, null);
		Collection<Game> gamesFiltroPlatform = gr.getAll(null, null, null, null, null, "Switch", null, null, null, null);
		Collection<Game> gamesFiltroGenre = gr.getAll(null, null, null, null, null, null, "platform", null, null, null);
		Collection<Game> gamesFiltroMode = gr.getAll(null, null, null, null, null, null, null, Mode.Individual, null, null);

		Collection<Game> gamesPaginacion = gr.getAll(null, null, null, null, null, null, null, null, 4, 2);


		assertNotNull("The collection of games is null", games);
		System.out.println("\n\n\n=====================================================");
		System.out.println("Listing all games:");
		System.out.println("=====================================================");

		for(Game g : games) {
			System.out.println(g.toString());
		}
		
		assertNotNull("The collection of games sorted by title is null", gamesOrderTitle);
		System.out.println("=====================================================");

		System.out.println("Listing all games sorted by title:");
		System.out.println("=====================================================");

		for(Game g : gamesOrderTitle) {
			System.out.println(g.toString());
		}
		
		assertNotNull("The collection of games sorted reverse by title is null", gamesOrderTitleReverse);
		System.out.println("=====================================================");
		System.out.println("Listing all games sorted reverse by title:");
		System.out.println("=====================================================");

		for(Game g : gamesOrderTitleReverse) {
			System.out.println(g.toString());
		}
		
		assertNotNull("The collection of games sorted by year is null", gamesOrderYear);
		System.out.println("=====================================================");
		System.out.println("Listing all games sorted by year:");
		System.out.println("=====================================================");
		for(Game g : gamesOrderYear) {
			System.out.println(g.toString());
		}
		
		assertNotNull("The collection of games sorted reverse by year is null", gamesOrderYearReverse);
		System.out.println("=====================================================");
		System.out.println("Listing all games sorted reverse by year:");
		System.out.println("=====================================================");
		for(Game g : gamesOrderYearReverse) {
			System.out.println(g.toString());
		}
		
		assertNotNull("The collection of games sorted by dev is null", gamesOrderDev);
		System.out.println("=====================================================");
		System.out.println("Listing all games sorted by dev:");
		System.out.println("=====================================================");
		for(Game g : gamesOrderDev) {
			System.out.println(g.toString());
		}
		
		assertNotNull("The collection of games sorted reverse by dev is null", gamesOrderDevReverse);
		System.out.println("=====================================================");
		System.out.println("Listing all games sorted reverse by dev:");
		System.out.println("=====================================================");
		for(Game g : gamesOrderDevReverse) {
			System.out.println(g.toString());
		}
		
		assertNotNull("The collection of games sorted by score is null", gamesOrderScore);
		System.out.println("=====================================================");
		System.out.println("Listing all games sorted by score:");
		System.out.println("=====================================================");
		for(Game g : gamesOrderScore) {
			System.out.println(g.toString());
		}
		
		assertNotNull("The collection of games sorted reverse by score is null", gamesOrderScoreReverse);
		System.out.println("=====================================================");
		System.out.println("Listing all games sorted reverse by score:");
		System.out.println("=====================================================");
		for(Game g : gamesOrderScoreReverse) {
			System.out.println(g.toString());
		}
		
		assertNotNull("The collection of games sorted by genre is null", gamesOrderGenre);
		System.out.println("Listing all games sorted by genre:");
		for(Game g : gamesOrderGenre) {
			System.out.println(g.toString());
		}
		
		assertNotNull("The collection of games sorted reverse genre by is null", gamesOrderGenreReverse);
		System.out.println("Listing all games sorted reverse genre:");
		for(Game g : gamesOrderGenreReverse) {
			System.out.println(g.toString());
		}
		
		assertNotNull("The collection of games filtered by title is null", gamesFiltroTitle);
		System.out.println("Listing all games filtered by title:");
		for(Game g : gamesFiltroTitle) {
			System.out.println(g.toString());
		}
		
		assertNotNull("The collection of games filtered by year is null", gamesFiltroYear);
		System.out.println("Listing all games filtered by year:");
		for(Game g : gamesFiltroYear) {
			System.out.println(g.toString());
		}
		
		assertNotNull("The collection of games filtered by developer is null", gamesFiltroDev);
		System.out.println("Listing all games filtered by developer:");
		for(Game g : gamesFiltroDev) {
			System.out.println(g.toString());
		}
		
		assertNotNull("The collection of games filtered by score is null", gamesFiltroScore);
		System.out.println("Listing all games filtered by score:");
		for(Game g : gamesFiltroScore) {
			System.out.println(g.toString());
		}
		
		assertNotNull("The collection of games filtered by platform is null", gamesFiltroPlatform);
		System.out.println("Listing all games filtered by platform:");
		for(Game g : gamesFiltroPlatform) {
			System.out.println(g.toString());
		}
		
		assertNotNull("The collection of games filtered by genre is null", gamesFiltroGenre);
		System.out.println("Listing all games filtered by genre:");
		for(Game g : gamesFiltroGenre) {
			System.out.println(g.toString());
		}
		
		assertNotNull("The collection of games filtered by mode is null", gamesFiltroMode);
		System.out.println("Listing all games filtered by mode:");
		for(Game g : gamesFiltroMode) {
			System.out.println(g.toString());
		}
		
		assertNotNull("The collection of games with pagination is null", gamesPaginacion);
		System.out.println("Listing all games with pagination:");
		for(Game g : gamesPaginacion) {
			System.out.println(g.toString());
		}
	}


	@Test
	public void testGetGame() {
		Game gTest = gr.get(g1.getId());
		assertEquals("The id of the game do not match", g1.getId(), gTest.getId());
		assertEquals("The title of the games do not match", g1.getTitle(), gTest.getTitle());
		assertEquals("The description of the games do not match", g1.getDescription(), gTest.getDescription());
		assertEquals("The year of the games do not match", g1.getYear(), gTest.getYear());
		assertEquals("The developer of the games do not match", g1.getDeveloper(), gTest.getDeveloper());
		assertEquals("The score of the games do not match", g1.getScore(), gTest.getScore());
		assertEquals("The platforms of the games do not match", g1.getPlatforms(), gTest.getPlatforms());
		assertEquals("The genres of the games do not match", g1.getGenres(), gTest.getGenres());
		assertEquals("The modes of the games do not match", g1.getModes(), gTest.getModes());
		
		System.out.println("\n\n\n=====================================================");
		System.out.println("testGetGame()");
		System.out.println("=====================================================");

		System.out.println("Game id: " + gTest.getId());
		System.out.println("Game title: " + gTest.getTitle());
		
	}

	@Test
	public void testAddGame() {
		Game g = new Game();
		
		g.setTitle("Add game test title");
		g.setDescription("Add game test description");
		g.setYear(0);
		g.setDeveloper(d);
		g.setScore(5.);
		g.setPlatforms(new ArrayList<Platform>(platforms.getAll()));
		g.setGenres(new ArrayList<Genre>(genres.getAll()));
		g.setModes(Arrays.asList(Mode.values()));
		
		gr.add(g);
		
		Game gTest = gr.getAll(null, g.getTitle(), null, null, null, null, null, null, null, null).stream().findFirst().get();
		assertNotNull("Error when adding the game", gTest);
		assertEquals("The game's title has not been setted correctly", g.getTitle(), gTest.getTitle());
		assertEquals("The game's description has not been setted correctly", g.getDescription(), gTest.getDescription());
		assertEquals("The game's year has not been setted correctly", g.getYear(), gTest.getYear());
		assertEquals("The game's developer has not been setted correctly", g.getDeveloper(), gTest.getDeveloper());
		assertEquals("The game's score has not been setted correctly", g.getScore(), gTest.getScore());
		assertEquals("The game's platforms have not been setted correctly", g.getPlatforms(), gTest.getPlatforms());
		assertEquals("The game's genres have not been setted correctly", g.getGenres(), gTest.getGenres());
		assertEquals("The game's modes have not been setted correctly", g.getModes(), gTest.getModes());
		
		
	}

	@Test
	public void testUpdateGame() {
		Game g = g1;
		g.setTitle("Update game title test");
		g.setDescription("Update game description test");
		g.setYear(0);
		g.setDeveloper(d);
		g.setScore(5.);
		g.setPlatforms(new ArrayList<Platform>(platforms.getAll()));
		g.setGenres(new ArrayList<Genre>(genres.getAll()));
		g.setModes(Arrays.asList(Mode.values()));
		
		gr.update(g);
		
		assertEquals("The game's title has not been updated correctly", g.getTitle(), g1.getTitle());
		assertEquals("The game's description has not been updated correctly", g.getDescription(), g1.getDescription());
		assertEquals("The game's year has not been updated correctly", g.getYear(), g1.getYear());
		assertEquals("The game's developer has not been updated correctly", g.getDeveloper(), g1.getDeveloper());
		assertEquals("The game's score has not been updated correctly", g.getScore(), g1.getScore());
		assertEquals("The game's platforms have not been updated correctly", g.getPlatforms(), g1.getPlatforms());
		assertEquals("The game's genres have not been updated correctly", g.getGenres(), g1.getGenres());
		assertEquals("The game's modes have not been updated correctly", g.getModes(), g1.getModes());
		
	}

	@Test
	public void testDeleteGame() {
		String id = g1.getId();
		gr.delete(id);
		Game gT = gr.get(id);
		assertNull("Error when deleting game", gT);
		if(gT==null) {
			gr.add(g1);
			g1 = gr.getAll(null, g1.getTitle(), g1.getYear(), g1.getDeveloper().getName(), g1.getScore(), g1.getPlatforms().get(0).getName(), g1.getGenres().get(0).getName(), g1.getModes().get(0), null, null).stream().findFirst().get();
		}
	}


}
