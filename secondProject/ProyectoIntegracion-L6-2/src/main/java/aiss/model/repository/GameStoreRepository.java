package aiss.model.repository;

import java.util.Collection;

import aiss.model.Game;


public interface GameStoreRepository {
	
	
	// Games
	public void addGame(Game g);
	public Collection<Game> getAllSongs();
	public Game getGame(String gameId);
	public void updateGame(Game g);
	public void deleteGame(String gameId);
	

	
	
	
	

}
