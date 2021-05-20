package aiss.api.resources;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.spi.BadRequestException;
import org.jboss.resteasy.spi.NotFoundException;

import aiss.model.Game;
import aiss.model.Store;
import aiss.model.StoreGame;
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
		return getAll(null, null, null, null, null, null);
	}

	@GET
	@Produces("application/json")
	public Collection<Store> getAll(@QueryParam("order") String order, @QueryParam("name") String name,
			@QueryParam("location") String location, @QueryParam("titleGame") String titleGame,
			@QueryParam("limit") Integer limit, @QueryParam("offset") Integer offset) {
		Collection<Store> result = repository.getAllStores();
		
		if (name != null) {

			result = result.stream().filter(x -> x.getName().toLowerCase().contains(name.toLowerCase()))
					.collect(Collectors.toList());
		}
		
		if (location != null) {

			result = result.stream().filter(x -> x.getLocation().toLowerCase().contains(location.toLowerCase()))
					.collect(Collectors.toList());
		}

		if (titleGame != null) {
			List<Store> aux = new ArrayList<Store>();
			Boolean predicate = false;
			int i = 0;
			for (Store s : result) {
				// predicate = false;
				List<StoreGame> aux2 = new ArrayList<StoreGame>();
				if (s.getGames() != null) {
					if (s.getGames().size() > 0)
						for (StoreGame sg : s.getGames()) {
							if (sg.getGame().getTitle().toLowerCase().contains(titleGame.toLowerCase())) {
								aux2.add(sg);
								predicate = true;

							}
						}
				}

				if (predicate) {
					aux.add(s);
					aux.get(i).setGames(aux2);
					i++;
				}

			}
			result = aux;
		}

		if ((order != null)) {
			switch (order) {
			case "name":
				result = result.stream().sorted(Comparator.comparing(Store::getName))
						.collect(Collectors.toList());
				break;
			case "-name":
				result = result.stream().sorted(Comparator.comparing(Store::getName).reversed())
						.collect(Collectors.toList());
				break;
			case "location":
				result = result.stream().sorted(Comparator.comparing(Store::getLocation))
						.collect(Collectors.toList());
				break;
			case "-location":
				result = result.stream().sorted(Comparator.comparing(Store::getLocation).reversed())
						.collect(Collectors.toList());
				break;
			default:
				throw new BadRequestException(
						"The format of the order parameter must be name, -name, year, -year, country, or -country");
			}
		}
		List<Store> res = new ArrayList<Store>();
		if (result != null) {
			if (result.size() > 0)
				res = result.stream().collect(Collectors.toList());
		}

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
	public Store getStore(@PathParam("id") String id) {
		Store list = repository.getStore(id);

		if (list == null) {
			throw new NotFoundException("The store with id=" + id + " was not found");
		}

		return list;
	}

	//Sigue dando problemas
	@GET
	@Path("/cheapestGames")
	@Produces("aplication/json")
	public Collection<StoreGame> getCheapestGamesInArea(@QueryParam("titleGame") String titleGame,
			@QueryParam("location") String location) {
		
		Collection<Store> result = repository.getAllStores();
		
		if (location != null) {

			result = result.stream().filter(x -> x.getLocation().toLowerCase().contains(location.toLowerCase()))
					.collect(Collectors.toList());
		}
		
		
		if (titleGame.equals("") || titleGame.equals(null)) {
			throw new BadRequestException("The title game must not be null");
		}
		List<StoreGame> storeGames = result.stream().filter(
					x -> x.getGames() != null && x.getGames().size() > 0 && 
					x.getGames().stream().anyMatch(y -> y.getGame().getTitle().toLowerCase().contains(titleGame.toLowerCase()) && y.getStock() > 0)
					)
				.map(x -> x.getGames())
				.flatMap(Collection::stream)
				.collect(Collectors.toList()
		);
		
		storeGames.sort(Comparator.comparing(StoreGame::getPrice));
		
		return storeGames;
	}

	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response addStore(Store g) {
		if (g.getName() == null || "".equals(g.getName())) {
			throw new BadRequestException("The name of the store must not be null");
		}

		if (g.getLocation().equals(null) || g.getLocation().equals("")) {
			throw new BadRequestException("The location of the store must not be null");

		}
		if (g.getPhone().equals(null) || g.getPhone().equals("")) {
			throw new BadRequestException("The phone of the store must not be null");

		}
		g.setGames(new ArrayList<StoreGame>());

		repository.addStore(g);
		return Response.noContent().build();
	}

	@Path("/{id}")
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response addGameToStore(@PathParam("id") String storeId, StoreGame g) {
		if (storeId.equals("") || storeId.equals(null)) {
			throw new BadRequestException("The storeId of the store must not be null");
		}
		if (g.getGame().equals(null))
			throw new BadRequestException("The game of store must not be null");
		if (g.getPrice().equals(null) || g.getPrice() < 0.0)
			throw new BadRequestException("The price of the game must not be null");
		if (g.getStock().equals(null))
			throw new BadRequestException("The stock Game store must not be null");

		repository.addGameToStore(storeId, g);
		return Response.noContent().build();
	}

	@PUT
	@Consumes("application/json")
	public Response updateStore(Store g) {
		Store old = repository.getStore(g.getId());
		if (old == null) {
			throw new NotFoundException("The store with id=" + g.getId() + " was not found");
		}
		if (!(g.getName().equals(null) || g.getName().equals(""))) {
			old.setName(g.getName());
		}
		if (!(g.getLocation().equals(null) || g.getLocation().equals(""))) {
			old.setLocation(g.getLocation());
		}
		if (!(g.getGames().isEmpty() || g.getGames().equals(null))) {
			old.setGames(g.getGames());
		}

		if (!(g.getPhone().equals("") || g.getPhone().equals(null))) {
			old.setPhone(g.getPhone());
		}
		repository.updateStore(old);
		return Response.noContent().build();
	}

	@DELETE
	@Path("/{id}")
	public Response deleteStore(@PathParam("id") String id) {
		Store toRemove = repository.getStore(id);

		if (toRemove == null) {
			throw new NotFoundException("The store with id=" + id + " was not found");
		} else {
			Store s = repository.getStore(id);
			// TODO borrar lineas de juegos de store
//			if (s.getGames() != null) {
//				List<StoreGame> aux = new ArrayList<StoreGame>(s.getGames());
//				for(StoreGame s2:s.getGames()) {
//					repository.deleteObjeto(s.getId(), s2.getId());
//
//				}
//			}
			repository.deleteStore(s.getId());

		}
		return Response.noContent().build();
	}

	@POST
	@Path("/{id}")
	@Consumes("application/json")
	@Produces("application/json")
	public Response addStoreGame(@PathParam("id") String storeId, StoreGame game) {

		if (game.getGame().equals(null)) {
			throw new BadRequestException("The game must not be null");
		}
		if (game.getPrice().equals(null) || game.getPrice() < 0.) {
			throw new BadRequestException("The price of the game must not be null or negative");
		}
		if (game.getStock().equals(null) || game.getStock() < 0) {
			throw new BadRequestException("The stock must not be null or negative");
		}

		repository.addGameToStore(storeId, game);
		return Response.noContent().build();
	}

	@PUT
	@Path("/{id}")
	@Consumes("application/json")
	@Produces("application/json")
	public Response updateStoreGame(@PathParam("id") String storeId, StoreGame g) {
		Store s = repository.getStore(storeId);
		StoreGame old = repository.getObject(storeId, g.getId());

		List<StoreGame> newList = s.getGames();
		Integer index = newList.indexOf(old);

		if (old == null) {
			throw new NotFoundException(
					"The game with id=" + g.getId() + " was not found in the store with id=" + storeId);
		}
		if (!(g.getGame().equals(null) || g.getGame().equals(new Game()))) {
			old.setGame(g.getGame());
		}

		if (!(g.getPrice() < 0. || g.getPrice().equals(null))) {
			old.setPrice(g.getPrice());
		}

		if (!(g.getStock() < 0 || g.getStock().equals(null))) {
			old.setStock(g.getStock());
		}

		newList.set(index, old);
		s.setGames(newList);
		repository.updateStore(s);
		return Response.noContent().build();
	}

	@DELETE
	@Path("/{id}/{gameId}")
	public Response deleteGameFromStore(@PathParam("id") String storeId, @PathParam("gameId") String gameId) {
		Store toRemoveFrom = repository.getStore(storeId);

		if (toRemoveFrom == null) {
			throw new NotFoundException("The store with id=" + storeId + " was not found");
		}

		else {
			StoreGame toRemove = repository.getObject(storeId, gameId);

			if (toRemove == null) {
				throw new NotFoundException(
						"The game with id=" + gameId + " was not found in the store with id=" + storeId);
			} else {
				repository.deleteObjeto(storeId, gameId);
			}
		}

		return Response.noContent().build();
	}

	public Collection<StoreGame> getGamesFromStore(String storeId) {
		return repository.getAllObjects(storeId);
	}

}
