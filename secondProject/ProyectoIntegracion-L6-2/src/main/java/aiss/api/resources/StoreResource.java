package aiss.api.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.jboss.resteasy.spi.BadRequestException;
import org.jboss.resteasy.spi.NotFoundException;
import org.restlet.resource.Delete;

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
	public Collection<Store>getAll(@QueryParam("q") String q,@QueryParam("nogames")Boolean nogames,
			@QueryParam("order") String order,@QueryParam("offset")Integer offset,@QueryParam("limit") Integer limit){
		
		List<Store> out = new ArrayList<Store>();
		
		for(Store s: repository.getAllStores()) {
			if(nogames==null
					||nogames&&(s.getGames()==null||s.getGames().size()==0)
					||!nogames&&(s.getGames().size()>0))
				if(q==null||q.isEmpty()
						||s.getName().contains(q)
						||s.getLocation().contains(q))
					out.add(s);
		}
		
		if(order!=null)
			switch (order) {
			case "name":
				out = out.stream().sorted(Comparator.comparing(Store::getName)).collect(Collectors.toList());
				break;
			case "-name":
				out = out.stream().sorted(Comparator.comparing(Store::getName).reversed()).collect(Collectors.toList());
				break;
			case "location":
				out = out.stream().sorted(Comparator.comparing(Store::getLocation)).collect(Collectors.toList());
				break;
			case "-location":
				out = out.stream().sorted(Comparator.comparing(Store::getLocation).reversed()).collect(Collectors.toList());
				break;
			case "games":
				out = out.stream().sorted(Comparator.comparing(Store::getGamesSize)).collect(Collectors.toList());
				break;
			case "-games":
				out = out.stream().sorted(Comparator.comparing(Store::getGamesSize).reversed()).collect(Collectors.toList());
				break;

			default:
				throw new BadRequestException("Format  not supported");
			}
		
		if(offset!=null) {
			if(offset >=0 && offset<out.size())
				out = out.subList(offset, out.size());
			else
				throw new BadRequestException("Operation not supported");
		}
		
		if(limit!=null) {
			if(limit >=0 && limit<=out.size())
				out = out.subList(0, limit);
			else
				throw new BadRequestException("Operation not supported");
		}
		return out;
		
	}
	
	@GET
	@Path("/{storeId}")
	@Produces("aplication/json")
	public Store get(@PathParam("storeId") String storeId) {
		Store s = repository.getStore(storeId);
		if(s==null)
			throw new NotFoundException("The store with id "+storeId+" does not exist");
		return s;
	}
	
	@GET
	@Path("/{storeId}/objects")
	@Produces("aplication/json")
	public Collection<ObjetoStore> getAllObjects(@PathParam("storeId") String storeId, @QueryParam("hasStock") Boolean hasStock,
			@QueryParam("maxPrice")Double maxPrice,@QueryParam("order") String order,@QueryParam("offset") Integer offset,
			@QueryParam("limit") Integer limit) {
		
		Collection<ObjetoStore> items = repository.getAllObjects(storeId);
		List<ObjetoStore> out = new ArrayList<ObjetoStore>();
		
		if(items==null)
			throw new NotFoundException("The store with id "+storeId+" does not contain any game");
		
		for(ObjetoStore item:items) {
			if(hasStock==null
					||hasStock&&item.getStock()>0
					||!hasStock&&item.getStock()==0)
				if(maxPrice==null||maxPrice>=item.getPrice())
					out.add(item);
		}
		
		if(order!=null)
			switch(order) {
				case "price":
					out = out.stream().sorted(Comparator.comparing(ObjetoStore::getPrice)).collect(Collectors.toList());
					break;
				case "-price":
					out = out.stream().sorted(Comparator.comparing(ObjetoStore::getPrice).reversed()).collect(Collectors.toList());
					break;
				case "stock":
					out = out.stream().sorted(Comparator.comparing(ObjetoStore::getStock)).collect(Collectors.toList());
					break;
				case "-stock":
					out = out.stream().sorted(Comparator.comparing(ObjetoStore::getStock).reversed()).collect(Collectors.toList());
					break;
				
				default:
					throw new BadRequestException("Format  not supported");
			}
		
		if(offset!=null) {
			if(offset >=0 && offset<out.size())
				out = out.subList(offset, out.size());
			else
				throw new BadRequestException("Operation not supported");
		}	
		if(limit!=null) {
			if(limit >=0 && limit<=out.size())
				out = out.subList(0, limit);
			else
				throw new BadRequestException("Operation not supported");
		}
		return out;
	}
	
	@GET
	@Path("/{storeId}/objects/{itemId}")
	@Produces("aplication/json")
	public ObjetoStore getObject(@PathParam("storeId") String storeId,@PathParam("itemId") String itemId) {
		ObjetoStore item = repository.getObject(storeId, itemId);
		if(item==null)
			throw new NotFoundException("The item with id "+itemId+" does not exist in the store with id "+storeId);
		return item;
	}
	
	@POST
	@Consumes("aplication/json")
	@Produces("aplication/json")
	public Response addStore(@Context UriInfo uriInfo, Store store) {
		//Lanza error si nombre o lugar null o vacio o si hora de cierre < hora de apertura
		if(store.getName()==null||store.getName().equals("")
				||store.getLocation()==null||store.getLocation().equals("")
				||store.getOpenHour().getValue()>=store.getCloseHour().getValue())
			throw new BadRequestException("Can not add the store with id "+store.getId());
		
		repository.addStore(store);
		
		UriBuilder ub = uriInfo.getAbsolutePathBuilder().path(this.getClass(), "get");
		URI uri = ub.build(store.getId());
		ResponseBuilder resp = Response.created(uri);
		resp.entity(store);
		return resp.build();
	}
	
	@PUT
	@Consumes("aplication/json")
	public Response updateStore(Store store) {
		Store old = repository.getStore(store.getId());
		if(old==null)
			throw new NotFoundException("The store with id "+store.getId()+" does not exist");
		if(store.getName()!=null&&!store.getName().equals(""))
			old.setName(store.getName());
		if(store.getLocation()!=null&&!store.getLocation().equals(""))
			old.setLocation(store.getLocation());
		if(store.getOpenHour().getValue()<store.getCloseHour().getValue())
			old.setOpenHour(store.getOpenHour());
			old.setCloseHour(store.getCloseHour());
		if(store.getPhone()!=null)
			old.setPhone(store.getPhone());
		//La lista de juegos no necesita check en mi opinion
		old.setGames(store.getGames());
		
		return Response.noContent().build();
	}
	
	@PUT
	@Consumes("aplication/json")
	public Response addObject(String storeId, ObjetoStore item) {
		Store store = repository.getStore(storeId);
		
		if(store==null)
			throw new NotFoundException("The store with id "+store.getId()+" does not exist");
		else
			repository.addObjeto(storeId, item);
		
		return Response.noContent().build();
		
	}
	
	@PUT
	@Consumes("aplication/json")
	public Response updateObject(String storeId, ObjetoStore item) {
		ObjetoStore old = repository.getObject(storeId, item.getId());
		
		if(old==null)
			throw new NotFoundException("The item with id "+item.getId()+" does not exist in the store with id "+storeId);
		else
			if(!item.getGame().equals(old.getGame()))
				throw new BadRequestException("Can not change the game who item with id "+item.getId()+" refers to");
			if(item.getPrice()!=null)
				old.setPrice(item.getPrice());
			if(item.getStock()!=null)
				old.setStock(item.getStock());
				
		
		return Response.noContent().build();
		
	}
	
	@Delete
	@Path("/{storeId}")
	public Response deleteStore(@PathParam("storeId") String storeId) {
		Store s = repository.getStore(storeId);
		
		if(s==null)
			throw new NotFoundException("The store with id "+storeId+" does not exist");
		else
			repository.deleteStore(storeId);
		
		return Response.noContent().build();
	}
	
	@Delete
	@Path("/{storeId}/objects/{itemId}")
	public Response deleteObject(@PathParam("storeId") String storeId, @PathParam("itemId") String itemId) {
		ObjetoStore item = repository.getObject(storeId, itemId);
		
		if(item==null)
			throw new NotFoundException("The item with id "+itemId+" does not exist in the store with id "+storeId);
		else
			repository.getAllObjects(storeId).remove(item);
		
		return Response.noContent().build();
	}
	
	
	


}
