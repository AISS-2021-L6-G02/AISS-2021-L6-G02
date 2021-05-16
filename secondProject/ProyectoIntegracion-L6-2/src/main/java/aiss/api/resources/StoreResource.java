package aiss.api.resources;

import java.time.LocalTime;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.spi.BadRequestException;
import org.jboss.resteasy.spi.NotFoundException;

import aiss.model.Game;
import aiss.model.ObjetoStore;
import aiss.model.Platform;
import aiss.model.Store;
import aiss.model.repository.DatabaseRepository;
import aiss.model.repository.MapDatabaseRepository;

@Path("/stores")
public class StoreResource {

	private static StoreResource _instance = null;
	DatabaseRepository repository;

	public StoreResource() {
		repository = MapDatabaseRepository.getInstance();
	}

	public static StoreResource getInstance() {
		if (_instance == null) {
			_instance = new StoreResource();
		}
		return _instance;
	}

	public Collection<Store> getAll() {
		return getAll(null,null,null,null);
	}

	
	@GET
	@Produces("application/json")
	public Collection<Store> getAll(@QueryParam("order") String order, @QueryParam("name") String name,
			@QueryParam("limit") Integer limit, @QueryParam("offset") Integer offset) {
		Stream<Store> result = repository.getAllStores().stream();
		if (!(name == null || name.equals(""))) {
			result = result.filter(x -> x.getName().toLowerCase().contains(name.toLowerCase())
					|| x.getName().toLowerCase().equals(name.toLowerCase()));
		}

		if (!(order == null || order.equals(""))) {
			Boolean noValido = false;
			switch (order) {
			default:
				noValido = true;
				break;
			case "name":
				result = result.sorted(Comparator.comparing(Store::getName));
				break;
			case "-name":
				result = result.sorted(Comparator.comparing(Store::getName).reversed());
				break;
			case "location":
				result = result.sorted(Comparator.comparing(Store::getLocation));
				break;
			case "-location":
				result = result.sorted(Comparator.comparing(Store::getLocation).reversed());
				break;
			case "openHour":
				result = result.sorted(Comparator.comparing(Store::getOpenHour));
				break;
			case "-openHour":
				result = result.sorted(Comparator.comparing(Store::getOpenHour).reversed());
				break;
			case "closeHour":
				result = result.sorted(Comparator.comparing(Store::getCloseHour));
				break;
			case "-closeHour":
				result = result.sorted(Comparator.comparing(Store::getCloseHour).reversed());
				break;
			}
			if (noValido) {
				throw new BadRequestException(
						"The format of the order parameter must be name, -name, year, -year, country, or -country");
			}
		}
		List<Store> res = result.collect(Collectors.toList());

		if (offset == null) {
			offset = 0;
		}

		if (limit == null || limit + offset > res.size()) {
			limit = res.size();
		}

		else {
			limit += offset;
		}

		return res.subList(offset, limit);

	}
	@GET
	@Path("/{id}")
	@Produces("application/json")
	public Store get(@PathParam("id") String id)
	{
		Store list = repository.getStore(id);
		
		if (list == null) {
			throw new NotFoundException("The store with id="+ id +" was not found");			
		}
		
		return list;
	}
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response add(Store g) {
		if (g.getName() == null || "".equals(g.getName())) {
			throw new BadRequestException("The name of the store must not be null");
		}
		if(g.getCloseHour().equals(null)) {
			throw new BadRequestException("The close hour of the store must not be null");

		}
		if(g.getOpenHour().equals(null)) {
			throw new BadRequestException("The close hour of the store must not be null");

		}
		if(g.getGames().isEmpty() || g.getGames().equals(null)) {
			throw new BadRequestException("The games of the store must not be null");

		}
		if(g.getLocation().equals(null) || g.getLocation().equals("")) {
			throw new BadRequestException("The location of the store must not be null");

		}
		if(g.getPhone().equals(null) || g.getPhone().equals("")) {
			throw new BadRequestException("The phone of the store must not be null");

		}
		
		repository.addStore(g);
		return Response.noContent().build();
	}


//	
//	@GET
//	@Path("/{storeId}")
//	@Produces("aplication/json")
//	public Store get(@PathParam("storeId") String storeId) {
//		Store s = repository.getStore(storeId);
//		if(s==null)
//			throw new NotFoundException("The store with id "+storeId+" does not exist");
//		return s;
//	}
//	
//	@GET
//	@Path("/{storeId}/objects")
//	@Produces("aplication/json")
//	public Collection<ObjetoStore> getAllObjects(@PathParam("storeId") String storeId, @QueryParam("hasStock") Boolean hasStock,
//			@QueryParam("maxPrice")Double maxPrice,@QueryParam("order") String order, @QueryParam("games") Collection<Game> games, @QueryParam("offset") Integer offset,
//			@QueryParam("limit") Integer limit) {
//		
//		Stream<ObjetoStore> items = repository.getAllObjects(storeId).stream();
//		
//		if(items==null)
//			throw new NotFoundException("The store with id "+storeId+" does not contain any game");
//		
//		if(hasStock) {
//			items.filter(x->x.getStock()>0).collect(Collectors.toList());
//		}
//		
//		
//		
//		if(order!=null)
//			switch(order) {
//				case "price":
//					items.sorted(Comparator.comparing(ObjetoStore::getPrice)).collect(Collectors.toList());
//					break;
//				case "-price":
//					items.sorted(Comparator.comparing(ObjetoStore::getPrice).reversed()).collect(Collectors.toList());
//					break;
//				case "stock":
//					items.sorted(Comparator.comparing(ObjetoStore::getStock)).collect(Collectors.toList());
//					break;
//				case "-stock":
//					items.sorted(Comparator.comparing(ObjetoStore::getStock).reversed()).collect(Collectors.toList());
//					break;
//				
//				default:
//					throw new BadRequestException("Format  not supported");
//			}
//		
//		List<ObjetoStore> res = items.collect(Collectors.toList());
//
//
//		if(offset==null) {
//			offset = 0;
//		}
//
//		if(limit==null || limit+offset>res.size()) {
//			limit = res.size();
//		}
//		
//		else{
//			limit += offset;
//		}
//		return res;
//	}
//	
//	@GET
//	@Path("/{storeId}/objects/{itemId}")
//	@Produces("aplication/json")
//	public ObjetoStore getObject(@PathParam("storeId") String storeId,@PathParam("itemId") String itemId) {
//		ObjetoStore item = repository.getObject(storeId, itemId);
//		if(item==null)
//			throw new NotFoundException("The item with id "+itemId+" does not exist in the store with id "+storeId);
//		return item;
//	}

//	@GET
//	@Path("/cheapestGames")
//	@Produces("aplication/json")
//	public Collection<ObjetoStore> getCheapestGamesInArea(@QueryParam("q") String q,@QueryParam("maxprice") Double maxprice, 
//			@QueryParam("games") Collection<Game> games){
//		Collection<Store> stores = getAll(q, null, games, null, null, null);
//		List<ObjetoStore> res = new ArrayList<ObjetoStore>();
//		for(Store s:stores) {
//			int values = games==null?5:games.size();
//			int limit = s.getGamesSize()==0? 0:s.getGamesSize()> values? values:s.getGamesSize();
//			Collection<ObjetoStore> items = getAllObjects(s.getId(), true, maxprice, "price", games, null, limit);
//			res.addAll(items);			
//		}
//		return res;
//	}
//	
//	@POST
//	@Consumes("aplication/json")
//	@Produces("aplication/json")
//	public Response addStore(/*@Context UriInfo uriInfo, */Store store) {
//		//Lanza error si nombre o lugar null o vacio o si hora de cierre < hora de apertura
//		if(store.getName()==null||store.getName().equals("")
//				||store.getLocation()==null||store.getLocation().equals("")
//				||store.getOpenHour().compareTo(store.getCloseHour())>0)
//			throw new BadRequestException("Can not add the store with id "+store.getId());
//		
//		repository.addStore(store);
//		
//		/*
//		UriBuilder ub = uriInfo.getAbsolutePathBuilder().path(this.getClass(), "get");
//		URI uri = ub.build(store.getId());
//		ResponseBuilder resp = Response.created(uri);
//		resp.entity(store);
//		return resp.build();
//		*/
//		return Response.noContent().build();
//	}
//	
//	@PUT
//	@Consumes("aplication/json")
//	public Response updateStore(Store store) {
//		Store old = repository.getStore(store.getId());
//		if(old==null)
//			throw new NotFoundException("The store with id "+store.getId()+" does not exist");
//		if(store.getName()!=null&&!store.getName().equals(""))
//			old.setName(store.getName());
//		if(store.getLocation()!=null&&!store.getLocation().equals(""))
//			old.setLocation(store.getLocation());
//		if(store.getOpenHour().compareTo(store.getCloseHour())<0)
//			old.setOpenHour(store.getOpenHour());
//			old.setCloseHour(store.getCloseHour());
//		if(store.getPhone()!=null)
//			old.setPhone(store.getPhone());
//		//La lista de juegos no necesita check en mi opinion
//		old.setGames(store.getGames());
//		
//		return Response.noContent().build();
//	}
//	
//	@POST
//	@Path("/object")
//	@Consumes("aplication/json")
//	public Response addObject(String storeId, ObjetoStore item) {
//		Store store = repository.getStore(storeId);
//		
//		if(store==null)
//			throw new NotFoundException("The store with id "+storeId+" does not exist");
//		else
//			repository.addObjeto(storeId, item);
//		
//		return Response.noContent().build();
//		
//	}
//	
//	@PUT
//	@Path("/object")
//	@Consumes("aplication/json")
//	public Response updateObject(String storeId, ObjetoStore item) {
//		ObjetoStore old = repository.getObject(storeId, item.getId());
//		
//		if(old==null)
//			throw new NotFoundException("The item with id "+item.getId()+" does not exist in the store with id "+storeId);
//		else
//			if(!item.getGame().equals(old.getGame()))
//				throw new BadRequestException("Can not change the game who item with id "+item.getId()+" refers to");
//			if(item.getPrice()!=null)
//				old.setPrice(item.getPrice());
//			if(item.getStock()!=null)
//				old.setStock(item.getStock());
//				
//		
//		return Response.noContent().build();
//		
//	}
//	
//	@Delete
//	@Path("/{storeId}")
//	public Response deleteStore(@PathParam("storeId") String storeId) {
//		Store s = repository.getStore(storeId);
//		
//		if(s==null)
//			throw new NotFoundException("The store with id "+storeId+" does not exist");
//		else
//			repository.deleteStore(storeId);
//		
//		return Response.noContent().build();
//	}
//	
//	@Delete
//	@Path("/{storeId}/objects/{itemId}")
//	public Response deleteObject(@PathParam("storeId") String storeId, @PathParam("itemId") String itemId) {
//		ObjetoStore item = repository.getObject(storeId, itemId);
//		
//		if(item==null)
//			throw new NotFoundException("The item with id "+itemId+" does not exist in the store with id "+storeId);
//		else
//			repository.getAllObjects(storeId).remove(item);
//		
//		return Response.noContent().build();
//	}
//	

}
