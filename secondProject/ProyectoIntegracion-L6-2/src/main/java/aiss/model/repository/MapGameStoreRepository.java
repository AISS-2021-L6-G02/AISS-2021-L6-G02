package aiss.model.repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import aiss.model.Game;
import aiss.model.ObjetoStore;
import aiss.model.Store;



public class MapGameStoreRepository implements GameStoreRepository{

	Map<String,Store> storeMap;
	private static MapGameStoreRepository instance = null;
	private int index=0;

	
	public static MapGameStoreRepository getInstance() {
		if(instance==null) {
			instance = new MapGameStoreRepository();
			instance.iniData();
		}
		return instance;
	}
	
	public void iniData() {
		
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
		String id="o"+index++;
		o.setId(id);
		storeMap.get(storeId).addGame(o);
		
	}

	@Override
	public void deleteObjeto(String storeId, String objectId) {
		
	}

	@Override
	public ObjetoStore getObject(String storeId, String itemId) {
		return getAllObjects(storeId).stream().filter(x->x.getId().equals(itemId)).findFirst().get();
	}
}
