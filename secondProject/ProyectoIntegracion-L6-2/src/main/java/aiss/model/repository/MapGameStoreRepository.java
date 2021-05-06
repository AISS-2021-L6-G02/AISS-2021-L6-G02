package aiss.model.repository;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import aiss.model.Game;
import aiss.model.ObjetoStore;
import aiss.model.Store;



public class MapGameStoreRepository implements GameStoreRepository{

	Map<String,Store> storeMap;
	private static MapGameStoreRepository instance = null;
	private int index=0;
	private int oIndex = 0;

	
	public static MapGameStoreRepository getInstance() {
		if(instance==null) {
			instance = new MapGameStoreRepository();
			instance.iniData();
		}
		return instance;
	}
	
	public void iniData() {
		Collection<Game> games = MapGameRepository.getInstance().getAllGames();
		storeMap = new HashMap<String, Store>();
		Game g = null;
		//init gamesMap
		//Create games
		//Create stores
		Store store1 = new Store();
		store1.setName("GAME");
		store1.setLocation("Los Arcos Sevilla");
		store1.setOpenHour(LocalTime.of(8, 0));
		store1.setCloseHour(LocalTime.of(20, 30));
		store1.setPhone("955323867");
		List<ObjetoStore> gamesStore1 = new ArrayList<>();
		ObjetoStore game1Store1 = new ObjetoStore();
		game1Store1.setGame(games.stream().filter(x -> x.getTitle().toLowerCase().contains("super mario odyssey")).findFirst().get());
		game1Store1.setPrice(49.99);
		game1Store1.setStock(4);
		gamesStore1.add(game1Store1);
		addStore(store1);
		for(ObjetoStore i:gamesStore1) {
			addObjeto(store1.getId(), i);
		}
		
		Store store2 = new Store();
		store2.setName("CeX");
		store2.setLocation("Paseo de Sant Joan Barcelona");
		store2.setOpenHour(LocalTime.of(11, 0));
		store2.setCloseHour(LocalTime.of(21, 0));
		store2.setPhone("932319555");
		List<ObjetoStore> gamesStore2 = new ArrayList<>();
		ObjetoStore game1Store2 = new ObjetoStore();
		game1Store2.setGame(games.stream().filter(x -> x.getTitle().toLowerCase().contains("fifa")).findFirst().get());
		game1Store2.setPrice(9.50);
		game1Store2.setStock(2);
		gamesStore2.add(game1Store2);
		ObjetoStore game2Store2 = new ObjetoStore();
		game2Store2.setGame(games.stream().filter(x -> x.getTitle().toLowerCase().contains("animal crossing")).findFirst().get());
		game2Store2.setPrice(24.95);
		game2Store2.setStock(3);
		gamesStore2.add(game2Store2);
		addStore(store2);
		for(ObjetoStore i:gamesStore2) {
			addObjeto(store2.getId(), i);
		}
		
		
		Store store3 = new Store();
		store3.setName("GAME");
		store3.setLocation("Paseo de la Florida Madrid");
		store3.setOpenHour(LocalTime.of(9, 0));
		store3.setCloseHour(LocalTime.of(19, 30));
		store3.setPhone("917168628");
		List<ObjetoStore> gamesStore3 = new ArrayList<>();
		ObjetoStore game1Store3 = new ObjetoStore();
		game1Store3.setGame(games.stream().filter(x -> x.getTitle().toLowerCase().contains("super mario odyssey")).findFirst().get());
		game1Store3.setPrice(45.60);
		game1Store3.setStock(1);
		gamesStore3.add(game1Store3);
		ObjetoStore game2Store3 = new ObjetoStore();
		game2Store3.setGame(games.stream().filter(x -> x.getTitle().toLowerCase().contains("grand theft auto")).findFirst().get());
		game2Store3.setPrice(9.50);
		game2Store3.setStock(5);
		gamesStore3.add(game2Store3);
		addStore(store3);
		for(ObjetoStore i:gamesStore3) {
			addObjeto(store3.getId(), i);
		}
		
		Store store4 = new Store();
		store4.setName("RETRO GAME VALENCIA");
		store4.setLocation("Calle  de l'Arxiduc Carles Valencia");
		store4.setOpenHour(LocalTime.of(11, 30));
		store4.setCloseHour(LocalTime.of(20, 0));
		store4.setPhone("962069197");
		List<ObjetoStore> gamesStore4 = new ArrayList<>();
		ObjetoStore game1Store4 = new ObjetoStore();
		game1Store4.setGame(games.stream().filter(x -> x.getTitle().toLowerCase().contains("animal crossing")).findFirst().get());
		game1Store4.setPrice(29.65);
		game1Store4.setStock(2);
		gamesStore4.add(game1Store4);
		ObjetoStore game2Store4 = new ObjetoStore();
		game2Store4.setGame(games.stream().filter(x -> x.getTitle().toLowerCase().contains("grand theft auto")).findFirst().get());
		game2Store4.setPrice(7.85);
		game2Store4.setStock(6);
		gamesStore4.add(game2Store4);
		ObjetoStore game3Store4 = new ObjetoStore();
		game3Store4.setGame(games.stream().filter(x -> x.getTitle().toLowerCase().contains("fifa 20")).findFirst().get());
		game3Store4.setPrice(10.00);
		game3Store4.setStock(4);
		gamesStore4.add(game3Store4);
		addStore(store4);
		for(ObjetoStore i:gamesStore4) {
			addObjeto(store4.getId(), i);
		}
	}
	
	@Override
	public void addStore(Store s) {
		String id = "shop"+index++;
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

	@Override
	public Collection<ObjetoStore> getAllObjects(String storeId) {
		return storeMap.get(storeId).getGames();
	}

	@Override
	public void addObjeto(String storeId, ObjetoStore o) {
		if (storeMap.get(storeId).getGames()==null||storeMap.get(storeId).getGamesSize()==0) oIndex=0;
		String id="o"+oIndex++;
		o.setId(id);
		storeMap.get(storeId).addGame(o);
		
	}

	@Override
	public void deleteObjeto(String storeId, String objectId) {
		storeMap.get(storeId).deleteGame(getObject(storeId, objectId));
	}

	@Override
	public ObjetoStore getObject(String storeId, String itemId) {
		return getAllObjects(storeId).stream().filter(x->x.getId().equals(itemId)).findFirst().get();
	}
	
}
