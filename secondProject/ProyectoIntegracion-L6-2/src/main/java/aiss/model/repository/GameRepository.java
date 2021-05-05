package aiss.model.repository;

import java.util.Collection;

import aiss.model.Game;

public interface GameRepository {
	
	public void addGame(Game g);
	public Collection<Game> getAllGames();
	public Game getGame(String gameId);
	public void updateGame(Game g);
	public void deleteGame(String gameId);
	
	
	
	
}
