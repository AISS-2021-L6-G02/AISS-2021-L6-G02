package aiss.model.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import aiss.model.Developer;
import aiss.model.Game;
import aiss.model.Genre;
import aiss.model.Mode;
import aiss.model.Platform;

public class MapGameRepository implements GameRepository{

	Map<String, Game> gameMap;
	private static MapGameRepository instance = null;
	private int index = 0;

	public static MapGameRepository getInstance() {
		if(instance==null) {
			instance = new MapGameRepository();
			instance.iniData();
		}
		return instance;
	}

	public void iniData() {
		Collection<Genre> genres = MapGenreRepository.getInstance().getAllGenre();
		Collection<Developer> devs = MapDeveloperRepository.getInstance().getAllDevelopers();
		Collection<Platform> p = MapPlatformRepository.getInstance().getAllPlatforms();
		List<Platform> lP;
		List<Mode> lM;
		List<Genre> lG;



		Game g1, g2, g3, g4;
		gameMap = new HashMap<String, Game>();

		g1 = new Game();
		lP = new ArrayList<Platform>();
		lM = new ArrayList<Mode>();
		lG = new ArrayList<Genre>();

		lP.add(p.stream().filter(x->x.getName().toLowerCase().contains("switch")).findFirst().get());

		lM.add(Mode.Individual);
		lM.add(Mode.Cooperativo_local);

		lG.add(genres.stream().filter(x->x.getName().toLowerCase().contains("platform")).findFirst().get());
		lG.add(genres.stream().filter(x->x.getName().toLowerCase().contains("adventure")).findFirst().get());

		g1.setTitle("Super Mario Odyssey");
		g1.setDescription("Explore incredible places far from the Mushroom Kingdom as you join Mario"
				+ "and his new ally Cappy on a massive, globe-trotting 3D adventure");
		g1.setYear(2017);
		g1.setDeveloper(devs.stream().filter(x->x.getName().toLowerCase().contains("nintendo")).findFirst().get());
		g1.setScore(9.5);
		g1.setPlatforms(lP);
		g1.setModes(lM);
		g1.setGenres(lG);

		addGame(g1);

		g2 = new Game();
		lP = new ArrayList<Platform>();
		lM = new ArrayList<Mode>();
		lG = new ArrayList<Genre>();


		lP.add(p.stream().filter(x->x.getName().toLowerCase().contains("switch")).findFirst().get());
		lM.add(Mode.Individual);
		lM.add(Mode.Multijugador);
		lG.add(genres.stream().filter(x->x.getName().toLowerCase().contains("simulation")).findFirst().get());

		g2.setTitle("Animal Crossing: New Horizons");
		g2.setDescription("Animal Crossing: New Horizons is a 2020 life simulation game developed and published"
				+ "by Nintendo for the Nintendo Switch; it is the fifth main game in the Animal Crossing series."
				+ "In New Horizons, the player controls a character who moves to a deserted island after"
				+ "purchasing a getaway package from Tom Nook, playing the game in a nonlinear fashion and"
				+ "developing the island as they choose. They can gather and craft items, customize the island,"
				+ "and form it into a community of anthropomorphic animals.");
		g2.setYear(2020);
		g2.setDeveloper(devs.stream().filter(x->x.getName().toLowerCase().contains("nintendo")).findFirst().get());
		g2.setScore(9.5);
		g2.setPlatforms(lP);
		g2.setModes(lM);
		g2.setGenres(lG);

		addGame(g2);


		g3 = new Game();
		lP = new ArrayList<Platform>();
		lM = new ArrayList<Mode>();
		lG = new ArrayList<Genre>();


		lP.addAll(p.stream().filter(x->x.getName().toLowerCase().contains("xbox")).collect(Collectors.toList()));
		lP.addAll(p.stream().filter(x->x.getName().toLowerCase().contains("playstation")).collect(Collectors.toList()));
		lM.add(Mode.Individual);
		lM.add(Mode.Multijugador);
		lG.add(genres.stream().filter(x->x.getName().toLowerCase().contains("action")).findFirst().get());
		lG.add(genres.stream().filter(x->x.getName().toLowerCase().contains("adventure")).findFirst().get());

		g3.setTitle("Grand Theft Auto: V");
		g3.setDescription("Grand Theft Auto V is a 2013 action-adventure game developed by Rockstar North and"
				+ "published by Rockstar Games. It is the first main entry in the Grand Theft Auto series since"
				+ "2008's Grand Theft Auto IV. Set within the fictional state of San Andreas, based on Southern"
				+ "California, the single-player story follows three protagonists—retired bank robber Michael De"
				+ "Santa, street gangster Franklin Clinton, and drug dealer and arms smuggler Trevor Philips—and"
				+ "their efforts to commit heists while under pressure from a corrupt government agency and powerful"
				+ "criminals. The open world design lets players freely roam San Andreas' open countryside and the fictional"
				+ "city of Los Santos, based on Los Angeles");
		g3.setYear(2013);
		g3.setDeveloper(devs.stream().filter(x->x.getName().toLowerCase().contains("rockstar")).findFirst().get());
		g3.setScore(9.5);
		g3.setPlatforms(lP);
		g3.setModes(lM);
		g3.setGenres(lG);
		addGame(g3);





		g4 = new Game();
		lP = new ArrayList<Platform>();
		lM = new ArrayList<Mode>();
		lG = new ArrayList<Genre>();


		lP.addAll(p.stream().filter(x->x.getName().toLowerCase().contains("xbox")).collect(Collectors.toList()));
		lP.add(p.stream().filter(x->x.getName().toLowerCase().contains("switch")).findFirst().get());
		lP.add(p.stream().filter(x->x.getName().toLowerCase().contains("personal")).findFirst().get());

		lM.add(Mode.Individual);
		lM.add(Mode.Multijugador);

		lG.add(genres.stream().filter(x->x.getName().toLowerCase().contains("sports")).findFirst().get());

		g4.setTitle("FIFA 20");
		g4.setDescription("FIFA 20 is a football simulation video game published by Electronic Arts as part"
				+ "of the FIFA series. It is the 27th installment in the FIFA series, and was released on 27"
				+ "September 2019 for Microsoft Windows, PlayStation 4, Xbox One, and Nintendo Switch. Its successor,"
				+ "FIFA 21, was released on 9 October 2020");
		g4.setYear(2019);
		g4.setDeveloper(devs.stream().filter(x->x.getName().toLowerCase().contains("electronic arts")).findFirst().get());
		g4.setScore(8.1);
		g4.setPlatforms(lP);
		g4.setModes(lM);
		g4.setGenres(lG);

		addGame(g4);

	}


	@Override
	public void addGame(Game g) {
		String id = "g" + index++;
		g.setId(id);
		gameMap.put(id, g);
	}
	@Override
	public Collection<Game> getAllGames() {
		return gameMap.values();
	}
	@Override
	public Game getGame(String gameId) {
		return gameMap.get(gameId);
	}
	@Override
	public void updateGame(Game g) {
		gameMap.put(g.getId(), g);
	}
	@Override
	public void deleteGame(String gameId) {
		gameMap.remove(gameId);
	}

}
