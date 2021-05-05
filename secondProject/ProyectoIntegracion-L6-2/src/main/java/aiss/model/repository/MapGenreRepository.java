package aiss.model.repository;

import java.util.Collection;
import java.util.Map;
import java.util.HashMap;

import aiss.model.Genre;

public class MapGenreRepository implements GenreRepository {
	Map<String,Genre> genreMap;
	private static MapGenreRepository instance = null;
	private int index=0;

	public static MapGenreRepository getInstance() {

		if (instance == null) {
			instance = new MapGenreRepository();
			instance.init();
		}
		return instance;
	}

	public void init() {
		genreMap = new HashMap<String,Genre>();

		Genre action = new Genre();
		action.setName("Action");
		action.setDescription("An action game is a video game genre that emphasizes physical"
				+ "challenges, including hand–eye coordination and reaction-time. The genre"
				+ "includes a large variety of sub-genres, such as fighting games, beat 'em ups,"
				+ "shooter games and platform games");
		addGenre(action);

		Genre adventure = new Genre();
		adventure.setName("Adventure");
		adventure.setDescription("An adventure game is a video game in which the player assumes"
				+ "the role of a protagonist in an interactive story driven by exploration and/or"
				+ "puzzle-solving");
		addGenre(adventure);


		Genre fighting = new Genre();
		fighting.setName("Fighting");
		fighting.setDescription("A fighting game is a video game genre based around close combat"
				+ "between a limited number of characters, in a stage in which the boundaries are"
				+ "fixed. The characters fight each other's until they defeat their opponents or"
				+ "the time expires");
		addGenre(fighting);


		Genre platform = new Genre();
		platform.setName("Platform");
		platform.setDescription("Platform games are a video game genre and subgenre of action games"
				+ "in which the core objective is to move the player character between points in a"
				+ "rendered environment");
		addGenre(platform);

		Genre puzzle = new Genre();
		puzzle.setName("Puzzle");
		puzzle.setDescription("Puzzle video games make up a broad genre of video games that emphasize"
				+ "puzzle solving. The types of puzzles can test many problem-solving skills including"
				+ "logic, pattern recognition, sequence solving, spatial recognition, and word completion");
		addGenre(puzzle);

		Genre racing = new Genre();
		racing.setName("Racing");
		racing.setDescription("Racing video games are a video game genre in which the player participates in"
				+ "a racing competition");
		addGenre(racing);

		Genre role_playing = new Genre();
		role_playing.setName("Role-playing");
		role_playing.setDescription("A role-playing video game (commonly referred to as simply"
				+ "a role-playing game or an RPG as well as a computer"
				+ "role-playing game or a CRPG) is a video game genre where"
				+ "the player controls the actions of a character (or several"
				+ "party members) immersed in some well-defined world, usually"
				+ "involving some form of character development by way of recording"
				+ "statistics");
		addGenre(role_playing);


		Genre shooter = new Genre();
		shooter.setName("Shooter");
		shooter.setDescription("Shooter video games or shooter games are a subgenre of action"
				+ "video games where the focus is almost entirely on the defeat of the character's"
				+ "enemies using the weapons given to the player");
		addGenre(shooter);

		Genre simulation = new Genre();
		simulation.setName("Simulation");
		simulation.setDescription("A simulation game attempts to copy various activities from real"
				+ "life in the form of a game for various purposes such as training, analysis, or"
				+ "prediction. Usually there are no strictly defined goals in the game, with the player"
				+ "instead allowed to control a character or environment freely");
		addGenre(simulation);

		Genre sports = new Genre();
		sports.setName("Sports");
		sports.setDescription("A sports video game is a video game that simulates the practice of sports."
				+ "Most sports have been recreated with a game, including team sports, track and field,"
				+ "extreme sports and combat sports");
		addGenre(sports);

		Genre strategy= new Genre();
		strategy.setName("Strategy");
		strategy.setDescription("A strategy video game is a video game genre that focuses on skillful"
				+ "thinking and planning to achieve victory. It emphasizes strategic, tactical, and sometimes"
				+ "logistical challenges. Many games also offer economic challenges and exploration");
		addGenre(strategy);
	}


	@Override
	public void addGenre(Genre g) {
		String id = "g" + index++;
		g.setId(id);
		genreMap.put(id, g);
	}

	@Override
	public Collection<Genre> getAllGenre() {
		return genreMap.values();
	}

	@Override
	public Genre getGenre(String id) {
		return genreMap.get(id);
	}

	@Override
	public void updateGenre(Genre g) {
		genreMap.put(g.getId(), g);

	}

	@Override
	public void deleteGenre(String id) {
		genreMap.remove(id);

	}


}
