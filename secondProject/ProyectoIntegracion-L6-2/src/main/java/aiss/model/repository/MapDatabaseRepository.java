package aiss.model.repository;

//import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import aiss.model.Developer;
import aiss.model.Game;
import aiss.model.Genre;
import aiss.model.Mode;
import aiss.model.StoreGame;
import aiss.model.Platform;
import aiss.model.Store;

public class MapDatabaseRepository implements DatabaseRepository{

	private static MapDatabaseRepository instance = null;
	Map<String, Game> gameMap;
	Map<String, Developer> developerMap;
	Map<String,Store> storeMap;
	Map<String,Genre> genreMap;
	Map<String, Platform> platformMap;
	Map<String, StoreGame> storeGameMap;
	
	
	private int indexGame = 0;
	private int indexDeveloper = 0;
	private int indexStore = 0;
	private int indexStoreGame = 0;
	private int indexGenre = 0;
	private int indexPlatform = 0;
	
	public static MapDatabaseRepository getInstance() {
		if(instance == null) {
			instance = new MapDatabaseRepository();
			instance.init();
		}
		return instance;
	}
	
	public void init() {
		Genre action, adventure, fighting, platform, puzzle, racing, rolePlaying, shooter, simulation, sports, strategy;
		Platform pc, nSwitch, play5, xboxS, android;
		Developer nintendo, ea, activision, capcom, rockStar, konami, valve, bioware;
		Game marioOdyssey, animalCrossing, gta, fifa20;
		Store store1, store2, store3, store4;
		StoreGame game1Store1, game1Store2, game2Store2, game1Store3, game2Store3, game1Store4, game2Store4, game3Store4;
		List<StoreGame> gamesStore1, gamesStore2, gamesStore3, gamesStore4;
		
		
		
		List<Platform> lP;
		List<Mode> lM;
		List<Genre> lG;
		
		
		gameMap = new HashMap<String, Game>();
		developerMap = new HashMap<String, Developer>();
		storeMap = new HashMap<String, Store>();
		genreMap = new HashMap<String, Genre>();
		platformMap = new HashMap<String, Platform>();
		storeGameMap = new HashMap<String, StoreGame>();
		
		//Genre
		action = new Genre();
		action.setName("Action");
		action.setDescription("An action game is a video game genre that emphasizes physical"
				+ "challenges, including hand–eye coordination and reaction-time. The genre"
				+ "includes a large variety of sub-genres, such as fighting games, beat 'em ups,"
				+ "shooter games and platform games");

		adventure = new Genre();
		adventure.setName("Adventure");
		adventure.setDescription("An adventure game is a video game in which the player assumes"
				+ "the role of a protagonist in an interactive story driven by exploration and/or"
				+ "puzzle-solving");

		fighting = new Genre();
		fighting.setName("Fighting");
		fighting.setDescription("A fighting game is a video game genre based around close combat"
				+ "between a limited number of characters, in a stage in which the boundaries are"
				+ "fixed. The characters fight each other's until they defeat their opponents or"
				+ "the time expires");

		platform = new Genre();
		platform.setName("Platform");
		platform.setDescription("Platform games are a video game genre and subgenre of action games"
				+ "in which the core objective is to move the player character between points in a"
				+ "rendered environment");

		puzzle = new Genre();
		puzzle.setName("Puzzle");
		puzzle.setDescription("Puzzle video games make up a broad genre of video games that emphasize"
				+ "puzzle solving. The types of puzzles can test many problem-solving skills including"
				+ "logic, pattern recognition, sequence solving, spatial recognition, and word completion");

		racing = new Genre();
		racing.setName("Racing");
		racing.setDescription("Racing video games are a video game genre in which the player participates in"
				+ "a racing competition");

		rolePlaying = new Genre();
		rolePlaying.setName("Role-playing");
		rolePlaying.setDescription("A role-playing video game (commonly referred to as simply"
				+ "a role-playing game or an RPG as well as a computer"
				+ "role-playing game or a CRPG) is a video game genre where"
				+ "the player controls the actions of a character (or several"
				+ "party members) immersed in some well-defined world, usually"
				+ "involving some form of character development by way of recording"
				+ "statistics");

		shooter = new Genre();
		shooter.setName("Shooter");
		shooter.setDescription("Shooter video games or shooter games are a subgenre of action"
				+ "video games where the focus is almost entirely on the defeat of the character's"
				+ "enemies using the weapons given to the player");

		simulation = new Genre();
		simulation.setName("Simulation");
		simulation.setDescription("A simulation game attempts to copy various activities from real"
				+ "life in the form of a game for various purposes such as training, analysis, or"
				+ "prediction. Usually there are no strictly defined goals in the game, with the player"
				+ "instead allowed to control a character or environment freely");
		
		sports = new Genre();
		sports.setName("Sports");
		sports.setDescription("A sports video game is a video game that simulates the practice of sports."
				+ "Most sports have been recreated with a game, including team sports, track and field,"
				+ "extreme sports and combat sports");
		
		strategy= new Genre();
		strategy.setName("Strategy");
		strategy.setDescription("A strategy video game is a video game genre that focuses on skillful"
				+ "thinking and planning to achieve victory. It emphasizes strategic, tactical, and sometimes"
				+ "logistical challenges. Many games also offer economic challenges and exploration");
		
		
		
		addGenre(action);
		addGenre(adventure);
		addGenre(fighting);
		addGenre(platform);
		addGenre(puzzle);
		addGenre(racing);
		addGenre(rolePlaying);
		addGenre(shooter);
		addGenre(simulation);
		addGenre(sports);
		addGenre(strategy);
		
		action.setId(getAllGenre().stream().filter(x->x.getName()==action.getName()).findFirst().get().getId());
		adventure.setId(getAllGenre().stream().filter(x->x.getName()==adventure.getName()).findFirst().get().getId());
		fighting.setId(getAllGenre().stream().filter(x->x.getName()==fighting.getName()).findFirst().get().getId());
		platform.setId(getAllGenre().stream().filter(x->x.getName()==platform.getName()).findFirst().get().getId());
		puzzle.setId(getAllGenre().stream().filter(x->x.getName()==puzzle.getName()).findFirst().get().getId());
		racing.setId(getAllGenre().stream().filter(x->x.getName()==racing.getName()).findFirst().get().getId());
		rolePlaying.setId(getAllGenre().stream().filter(x->x.getName()==rolePlaying.getName()).findFirst().get().getId());
		shooter.setId(getAllGenre().stream().filter(x->x.getName()==shooter.getName()).findFirst().get().getId());
		simulation.setId(getAllGenre().stream().filter(x->x.getName()==simulation.getName()).findFirst().get().getId());
		sports.setId(getAllGenre().stream().filter(x->x.getName()==sports.getName()).findFirst().get().getId());
		strategy.setId(getAllGenre().stream().filter(x->x.getName()==strategy.getName()).findFirst().get().getId());
		
		
		//Platform
		pc = new Platform();
		pc.setName("Personal Computer");
		
		nSwitch = new Platform();
		nSwitch.setName("Nintendo switch");
		
		android = new Platform();
		android.setName("Android");
		
		play5 = new Platform();
		play5.setName("Playstation 5");
		
		xboxS = new Platform();
		xboxS.setName("Xbox series s");

		addPlatform(pc);
		addPlatform(nSwitch);
		addPlatform(android);
		addPlatform(play5);
		addPlatform(xboxS);
		
		
		pc.setId(getAllPlatforms().stream().filter(x->x.getName()==pc.getName()).findFirst().get().getId());
		nSwitch.setId(getAllPlatforms().stream().filter(x->x.getName()==nSwitch.getName()).findFirst().get().getId());
		android.setId(getAllPlatforms().stream().filter(x->x.getName()==android.getName()).findFirst().get().getId());
		play5.setId(getAllPlatforms().stream().filter(x->x.getName()==play5.getName()).findFirst().get().getId());
		xboxS.setId(getAllPlatforms().stream().filter(x->x.getName()==xboxS.getName()).findFirst().get().getId());
		
		nintendo = new Developer();
		nintendo.setName("Nintendo");
		nintendo.setYear(1889);
		nintendo.setCountry("Japan");
		
		
		//Developer
		ea = new Developer();
		ea.setName("Electronic Arts");
		ea.setYear(1991);
		ea.setCountry("USA");
		
		activision = new Developer();
		activision.setName("Activision");
		activision.setYear(1978);
		activision.setCountry("USA");
		
		capcom = new Developer();
		capcom.setName("Capcom Co., Ltd.");
		capcom.setYear(1971);
		capcom.setCountry("Japan");
		
		rockStar = new Developer();
		rockStar.setName("Rockstar Games");
		rockStar.setYear(1998);
		rockStar.setCountry("USA");
		
		konami = new Developer();
		konami.setName("Konami");
		konami.setYear(1969);
		konami.setCountry("Japan");
		
		valve = new Developer();
		valve.setName("Valve Corporation");
		valve.setYear(1996);
		valve.setCountry("USA");
		
		bioware = new Developer();
		bioware.setName("BioWare");
		bioware.setYear(1995);
		bioware.setCountry("Canada");
		
		addDeveloper(nintendo);
		addDeveloper(ea);
		addDeveloper(activision);
		addDeveloper(capcom);
		addDeveloper(rockStar);
		addDeveloper(konami);
		addDeveloper(valve);
		addDeveloper(bioware);
		
		nintendo.setId(getAllDevelopers().stream().filter(x->x.getName()==nintendo.getName()).findFirst().get().getId());
		ea.setId(getAllDevelopers().stream().filter(x->x.getName()==ea.getName()).findFirst().get().getId());
		activision.setId(getAllDevelopers().stream().filter(x->x.getName()==activision.getName()).findFirst().get().getId());
		capcom.setId(getAllDevelopers().stream().filter(x->x.getName()==capcom.getName()).findFirst().get().getId());
		rockStar.setId(getAllDevelopers().stream().filter(x->x.getName()==rockStar.getName()).findFirst().get().getId());
		konami.setId(getAllDevelopers().stream().filter(x->x.getName()==konami.getName()).findFirst().get().getId());
		valve.setId(getAllDevelopers().stream().filter(x->x.getName()==valve.getName()).findFirst().get().getId());
		bioware.setId(getAllDevelopers().stream().filter(x->x.getName()==bioware.getName()).findFirst().get().getId());
		
		
		//Game
		marioOdyssey = new Game();
		lP = new ArrayList<Platform>();
		lM = new ArrayList<Mode>();
		lG = new ArrayList<Genre>();
		lP.add(nSwitch);
		lM.add(Mode.Individual);
		lM.add(Mode.Cooperativo_local);
		lG.add(platform);
		lG.add(adventure);
		marioOdyssey.setTitle("Super Mario Odyssey");
		marioOdyssey.setDescription("Explore incredible places far from the Mushroom Kingdom as you join Mario"
				+ "and his new ally Cappy on a massive, globe-trotting 3D adventure");
		marioOdyssey.setYear(2017);
		marioOdyssey.setDeveloper(nintendo);
		marioOdyssey.setScore(9.5);
		marioOdyssey.setPlatforms(lP);
		marioOdyssey.setModes(lM);
		marioOdyssey.setGenres(lG);
		
		animalCrossing = new Game();
		lP = new ArrayList<Platform>();
		lM = new ArrayList<Mode>();
		lG = new ArrayList<Genre>();
		
		lP.add(nSwitch);
		lM.add(Mode.Individual);
		lM.add(Mode.Multijugador);
		lG.add(simulation);
		
		animalCrossing.setTitle("Animal Crossing: New Horizons");
		animalCrossing.setDescription("Animal Crossing: New Horizons is a 2020 life simulation game developed and published"
				+ "by Nintendo for the Nintendo Switch; it is the fifth main game in the Animal Crossing series."
				+ "In New Horizons, the player controls a character who moves to a deserted island after"
				+ "purchasing a getaway package from Tom Nook, playing the game in a nonlinear fashion and"
				+ "developing the island as they choose. They can gather and craft items, customize the island,"
				+ "and form it into a community of anthropomorphic animals.");
		animalCrossing.setYear(2020);
		animalCrossing.setDeveloper(nintendo);
		animalCrossing.setScore(9.5);
		animalCrossing.setPlatforms(lP);
		animalCrossing.setModes(lM);
		animalCrossing.setGenres(lG);
		
		
		gta = new Game();
		lP = new ArrayList<Platform>();
		lM = new ArrayList<Mode>();
		lG = new ArrayList<Genre>();
		
		lP.add(xboxS);
		lP.add(play5);
		lM.add(Mode.Individual);
		lM.add(Mode.Multijugador);
		lG.add(action);
		lG.add(adventure);
		
		gta.setTitle("Grand Theft Auto: V");
		gta.setDescription("Grand Theft Auto V is a 2013 action-adventure game developed by Rockstar North and"
				+ "published by Rockstar Games. It is the first main entry in the Grand Theft Auto series since"
				+ "2008's Grand Theft Auto IV. Set within the fictional state of San Andreas, based on Southern"
				+ "California, the single-player story follows three protagonists—retired bank robber Michael De"
				+ "Santa, street gangster Franklin Clinton, and drug dealer and arms smuggler Trevor Philips—and"
				+ "their efforts to commit heists while under pressure from a corrupt government agency and powerful"
				+ "criminals. The open world design lets players freely roam San Andreas' open countryside and the fictional"
				+ "city of Los Santos, based on Los Angeles");
		gta.setYear(2013);
		gta.setDeveloper(rockStar);
		gta.setScore(9.5);
		gta.setPlatforms(lP);
		gta.setModes(lM);
		gta.setGenres(lG);
		
		
		
		fifa20 = new Game();
		lP = new ArrayList<Platform>();
		lM = new ArrayList<Mode>();
		lG = new ArrayList<Genre>();
		
		lP.add(xboxS);
		lP.add(pc);
		lP.add(nSwitch);
		lM.add(Mode.Individual);
		lM.add(Mode.Multijugador);
		lG.add(sports);
		
		fifa20.setTitle("FIFA 20");
		fifa20.setDescription("FIFA 20 is a football simulation video game published by Electronic Arts as part"
				+ "of the FIFA series. It is the 27th installment in the FIFA series, and was released on 27"
				+ "September 2019 for Microsoft Windows, PlayStation 4, Xbox One, and Nintendo Switch. Its successor,"
				+ "FIFA 21, was released on 9 October 2020");
		fifa20.setYear(2019);
		fifa20.setDeveloper(ea);
		fifa20.setScore(8.1);
		fifa20.setPlatforms(lP);
		fifa20.setModes(lM);
		fifa20.setGenres(lG);
		
		addGame(marioOdyssey);
		addGame(animalCrossing);
		addGame(gta);
		addGame(fifa20);
		
		marioOdyssey.setId(getAllGames().stream().filter(x->x.getTitle()==marioOdyssey.getTitle()).findFirst().get().getId());
		animalCrossing.setId(getAllGames().stream().filter(x->x.getTitle()==animalCrossing.getTitle()).findFirst().get().getId());
		gta.setId(getAllGames().stream().filter(x->x.getTitle()==gta.getTitle()).findFirst().get().getId());
		fifa20.setId(getAllGames().stream().filter(x->x.getTitle()==fifa20.getTitle()).findFirst().get().getId());
		
		
		//game1Store1, game1Store2, game2Store2, game1Store3, game2Store3, game1Store4, game2Store4, game3Store4
		
		
		//GameStore y ObjetoStore
		store1 = new Store();
		store1.setName("GAME");
		store1.setLocation("Los Arcos Sevilla");
		//store1.setOpenHour(LocalTime.of(8, 0));
		//store1.setCloseHour(LocalTime.of(20, 30));
		store1.setPhone("955323867");
		
		gamesStore1 = new ArrayList<>();
		game1Store1 = new StoreGame();
		
		game1Store1.setGame(marioOdyssey);
		game1Store1.setPrice(49.99);
		game1Store1.setStock(4);
		gamesStore1.add(game1Store1);
		
		
		store2 = new Store();
		store2.setName("CeX");
		store2.setLocation("Paseo de Sant Joan Barcelona");
		//store2.setOpenHour(LocalTime.of(11, 0));
		//store2.setCloseHour(LocalTime.of(21, 0));
		store2.setPhone("932319555");
		
		gamesStore2 = new ArrayList<>();
		game1Store2 = new StoreGame();
		game2Store2 = new StoreGame();
		
		game1Store2.setGame(fifa20);
		game1Store2.setPrice(9.50);
		game1Store2.setStock(2);
		gamesStore2.add(game1Store2);
		
		game2Store2.setGame(animalCrossing);
		game2Store2.setPrice(24.95);
		game2Store2.setStock(3);
		gamesStore2.add(game2Store2);
		
		
		store3 = new Store();
		store3.setName("GAME");
		store3.setLocation("Paseo de la Florida Madrid");
		//store3.setOpenHour(LocalTime.of(9, 0));
		//store3.setCloseHour(LocalTime.of(19, 30));
		store3.setPhone("917168628");
		
		gamesStore3 = new ArrayList<>();
		game1Store3 = new StoreGame();
		game2Store3 = new StoreGame();
		
		game1Store3.setGame(marioOdyssey);
		game1Store3.setPrice(45.60);
		game1Store3.setStock(1);
		gamesStore3.add(game1Store3);
		
		game2Store3.setGame(gta);
		game2Store3.setPrice(9.50);
		game2Store3.setStock(5);
		gamesStore3.add(game2Store3);
		
		
		store4 = new Store();
		store4.setName("RETRO GAME VALENCIA");
		store4.setLocation("Calle  de l'Arxiduc Carles Valencia");
		//store4.setOpenHour(LocalTime.of(11, 30));
		//store4.setCloseHour(LocalTime.of(20, 0));
		store4.setPhone("962069197");
		
		gamesStore4 = new ArrayList<>();
		game1Store4 = new StoreGame();
		game2Store4 = new StoreGame();
		game3Store4 = new StoreGame();
		
		game1Store4.setGame(animalCrossing);
		game1Store4.setPrice(29.65);
		game1Store4.setStock(2);
		gamesStore4.add(game1Store4);
		
		game2Store4.setGame(gta);
		game2Store4.setPrice(7.85);
		game2Store4.setStock(6);
		gamesStore4.add(game2Store4);
		
		game3Store4.setGame(fifa20);
		game3Store4.setPrice(10.00);
		game3Store4.setStock(4);
		gamesStore4.add(game3Store4);
		
		
		addStore(store1);
		addStore(store2);
		addStore(store3);
		addStore(store4);
		
		
		addStoreGame(game1Store1);
		addStoreGame(game1Store2);
		addStoreGame(game2Store2);
		addStoreGame(game1Store3);
		addStoreGame(game2Store3);
		addStoreGame(game1Store4);
		addStoreGame(game2Store4);
		addStoreGame(game3Store4);
		
		game1Store1.setId(getAllObjects().stream().filter(x->x.getGame().equals(game1Store1.getGame()) && x.getPrice().equals(game1Store1.getPrice()) && x.getStock().equals(game1Store1.getStock())).findFirst().get().getId());
		game1Store2.setId(getAllObjects().stream().filter(x->x.getGame().equals(game1Store2.getGame()) && x.getPrice().equals(game1Store2.getPrice()) && x.getStock().equals(game1Store2.getStock())).findFirst().get().getId());
		game2Store2.setId(getAllObjects().stream().filter(x->x.getGame().equals(game2Store2.getGame()) && x.getPrice().equals(game2Store2.getPrice()) && x.getStock().equals(game2Store2.getStock())).findFirst().get().getId());
		game1Store3.setId(getAllObjects().stream().filter(x->x.getGame().equals(game1Store3.getGame()) && x.getPrice().equals(game1Store3.getPrice()) && x.getStock().equals(game1Store3.getStock())).findFirst().get().getId());
		game2Store3.setId(getAllObjects().stream().filter(x->x.getGame().equals(game2Store3.getGame()) && x.getPrice().equals(game2Store3.getPrice()) && x.getStock().equals(game2Store3.getStock())).findFirst().get().getId());
		game1Store4.setId(getAllObjects().stream().filter(x->x.getGame().equals(game1Store4.getGame()) && x.getPrice().equals(game1Store4.getPrice()) && x.getStock().equals(game1Store4.getStock())).findFirst().get().getId());
		game2Store4.setId(getAllObjects().stream().filter(x->x.getGame().equals(game2Store4.getGame()) && x.getPrice().equals(game2Store4.getPrice()) && x.getStock().equals(game2Store4.getStock())).findFirst().get().getId());
		game3Store4.setId(getAllObjects().stream().filter(x->x.getGame().equals(game3Store4.getGame()) && x.getPrice().equals(game3Store4.getPrice()) && x.getStock().equals(game3Store4.getStock())).findFirst().get().getId());
		
		store1.setId(getAllStores().stream().filter(x->x.getName()==store1.getName()).findFirst().get().getId());
		store2.setId(getAllStores().stream().filter(x->x.getName()==store2.getName()).findFirst().get().getId());
		store3.setId(getAllStores().stream().filter(x->x.getName()==store3.getName()).findFirst().get().getId());
		store4.setId(getAllStores().stream().filter(x->x.getName()==store4.getName()).findFirst().get().getId());
		
		
		for(StoreGame i:gamesStore1) {
			addGameToStore(store1.getId(), i);
		}
		for(StoreGame i:gamesStore2) {
			addGameToStore(store2.getId(), i);
		}
		for(StoreGame i:gamesStore3) {
			addGameToStore(store3.getId(), i);
		}
		for(StoreGame i:gamesStore4) {
			addGameToStore(store4.getId(), i);
		}
	}
	
	
	//Game
	@Override
	public void addGame(Game g) {
		String id = "g" + indexGame++;
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

	//Store
	@Override
	public void addStore(Store s) {
		String id = "shop"+indexStore++;
		s.setId(id);
		storeMap.put(s.getId(), s);
		
	}
	@Override
	public Collection<Store> getAllStores() {
		return storeMap.values();
	}
	@Override
	public Store getStore(String storeId) {
		return storeMap.get(storeId);
	}
	@Override
	public void updateStore(Store s) {
		storeMap.put(s.getId(), s);
	}
	@Override
	public void deleteStore(String storeId) {
		storeMap.remove(storeId);
	}
	
	//ObjetoStore
	private Collection<StoreGame> getAllObjects(){
		return storeGameMap.values();
	}
	@Override
	public void addStoreGame(StoreGame o) {
		String id = "sg" + indexStoreGame++;
		o.setId(id);
		storeGameMap.put(id, o);
	}
	@Override
	public void addGameToStore(String storeId, StoreGame o) {
		Store s = getStore(storeId);
		s.addGame(o);
		storeMap.put(storeId, s);
	}
	@Override
	public Collection<StoreGame> getAllObjects(String storeId) {
		return storeMap.get(storeId).getGames();
	}
	@Override
	public void deleteObjeto(String storeId, String objectId) {
		storeMap.get(storeId).deleteGame(getObject(storeId, objectId));
	}
	@Override
	public StoreGame getObject(String storeId, String itemId) {
		return getAllObjects(storeId).stream().filter(x->x.getId().equals(itemId)).findFirst().get();
	}
	
	//Genre
	@Override
	public void addGenre(Genre g) {
		String id = "genre" + indexGenre++;
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

	//Platform
	@Override
	public void addPlatform(Platform p) {
		String id = "p" + indexPlatform++;
		p.setId(id);
		platformMap.put(id, p);
	}
	@Override
	public Collection<Platform> getAllPlatforms() {
		return this.platformMap.values();
	}
	@Override
	public Platform getPlatform(String platformId) {
		return this.platformMap.get(platformId);
	}
	@Override
	public void updatePlatform(Platform p) {
		this.platformMap.put(p.getId(), p);
	}
	@Override
	public void deletePlatform(String id) {
		this.platformMap.remove(id);
	}

	//Developer
	@Override
	public void addDeveloper(Developer d) {
		String id = "d" + indexDeveloper++;
		d.setId(id);
		developerMap.put(id, d);
	}
	@Override
	public Collection<Developer> getAllDevelopers() {
		return developerMap.values();
	}
	@Override
	public Developer getDeveloper(String developerId) {
		return developerMap.get(developerId);
	}
	@Override
	public void updateDeveloper(Developer d) {
		developerMap.put(d.getId(), d);
	}
	@Override
	public void deleteDeveloper(String developerId) {
		developerMap.remove(developerId);
	}
}