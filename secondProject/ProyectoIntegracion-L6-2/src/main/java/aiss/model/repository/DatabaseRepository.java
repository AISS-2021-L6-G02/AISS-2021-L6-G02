package aiss.model.repository;

import java.util.Collection;

import aiss.model.Developer;
import aiss.model.Game;
import aiss.model.Genre;
import aiss.model.ObjetoStore;
import aiss.model.Platform;
import aiss.model.Store;

public interface DatabaseRepository {
	//Game
	public void addGame(Game g);
	public Collection<Game> getAllGames();
	public Game getGame(String gameId);
	public void updateGame(Game g);
	public void deleteGame(String gameId);
	
	//GameStore
	public void addStore(Store s);
	public Collection<Store> getAllStores();
	public Store getStore(String storeId);
	public void updateStore(Store s);
	public void deleteStore(String storeId);
	public Collection<ObjetoStore> getAllObjects(String storeId);
	public ObjetoStore getObject(String storeId, String itemId);
	public void addObjeto(String storeId, ObjetoStore o);
	public void deleteObjeto(String storeId,String objectId);
	
	//Genre
	public void addGenre(Genre g);
	public Collection<Genre> getAllGenre();
	public Genre getGenre(String id);
	public void updateGenre(Genre g);
	public void deleteGenre(String id);
	
	//Platform
	public void addPlatform(Platform p);
	public Collection<Platform> getAllPlatforms();
	public Platform getPlatform(String platformId);
	public void updatePlatform(Platform p);
	public void deletePlatform(String id);
	
	//Developer
	public void addDeveloper(Developer d);
	public Collection<Developer> getAllDevelopers();
	public Developer getDeveloper(String developerId);
	public void updateDeveloper(Developer d);
	public void deleteDeveloper(String developerId);
}
