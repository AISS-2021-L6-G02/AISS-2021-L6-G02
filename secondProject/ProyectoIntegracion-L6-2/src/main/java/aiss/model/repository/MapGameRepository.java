package aiss.model.repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

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
		gameMap = new HashMap<String, Game>();
		Game g = new Game();
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
