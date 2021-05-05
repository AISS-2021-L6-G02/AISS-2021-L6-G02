package aiss.model.repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import aiss.model.Developer;
import aiss.model.Game;

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
		Collection<Developer> devs = MapDeveloperRepository.getInstance().getAllDevelopers();
		Game g1, g2, g3, g4, g5;
		gameMap = new HashMap<String, Game>();
		g1 = new Game();
		g1.setTitle("Super Mario Odyssey");
		g1.setDescription("Super Mario Odyssey es un videojuego de plataformas de mundo abierto para Nintendo Switch que se lanzó en Japón y los Estados Unidos el 27 de octubre de 2017");
		g1.setYear(0);
		
		g2 = new Game();
		
		g3 = new Game();
		
		g4 = new Game();
		
		g5 = new Game();
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
