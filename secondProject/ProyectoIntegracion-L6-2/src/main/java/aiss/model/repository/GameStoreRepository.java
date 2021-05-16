package aiss.model.repository;

import java.util.Collection;

import aiss.model.Game;
import aiss.model.StoreGame;
import aiss.model.Store;


public interface GameStoreRepository {
	
	
	
	public void addStore(Store s);
	public Collection<Store> getAllStores();
	public Store getStore(String storeId);
	public void updateStore(Store s);
	public void deleteStore(String storeId);
	public Collection<StoreGame> getAllObjects(String storeId);
	public StoreGame getObject(String storeId, String itemId);
	public void addObjeto(String storeId, StoreGame o);
	public void deleteObjeto(String storeId,String objectId);
	

	
	
	
	

}
