package aiss.model.repository;

import java.util.Collection;

import aiss.model.Game;
import aiss.model.ObjetoStore;
import aiss.model.Store;


public interface GameStoreRepository {
	
	
	
	public void addStore(Store s);
	public Collection<Store> getAllStores();
	public Store getStore(String storeId);
	public void updateStore(Store s);
	public void deleteStore(String storeId);
	public Collection<ObjetoStore> getAllObjects(String storeId);
	public ObjetoStore getObject(String storeId, String itemId);
	public void addObjeto(String storeId, ObjetoStore o);
	public void deleteObjeto(String storeId,String objectId);
	

	
	
	
	

}
