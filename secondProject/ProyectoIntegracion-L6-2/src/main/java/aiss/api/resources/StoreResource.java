package aiss.api.resources;

import java.util.Collection;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import aiss.model.ObjetoStore;
import aiss.model.Store;
import aiss.model.repository.GameStoreRepository;
import aiss.model.repository.MapGameStoreRepository;

@Path("/stores")
public class StoreResource {
	
	public static StoreResource _instance = null;
	GameStoreRepository repository;
	private StoreResource() {
		repository = MapGameStoreRepository.getInstance();
	}
	
	@GET
	@Produces("aplication/json")
	public Collection<Store>getAll(){
		return repository.getAllStores();
		
	}
	
	@GET
	@Path("/{storeId}")
	@Produces("aplication/json")
	public Store get(@PathParam("storeId") String storeId) {
		return repository.getStore(storeId);
	}
	
	@GET
	@Path("/{storeId}/objects")
	@Produces("aplication/json")
	public Collection<ObjetoStore> getAllObjects(@PathParam("storeId") String storeId) {
		return repository.getAllObjects(storeId);
	}


}
