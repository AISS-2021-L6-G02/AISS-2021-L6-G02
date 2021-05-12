package aiss.api.resources;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
import aiss.model.Genre;
import aiss.model.Mode;
import aiss.model.repository.DatabaseRepository;
import aiss.model.repository.MapDatabaseRepository;
import utils.GameDeveloperSorter;
import utils.GameGenreSorter;

@Path("/games")
public class GameResource {
	private static GameResource instance = null;
	DatabaseRepository repository;

	private GameResource() {
		repository = MapDatabaseRepository.getInstance();
	}

	public static GameResource getInstance() {
		if (instance == null) {
			instance = new GameResource();
		}
		return instance;
	}
	
	
	@GET
	@Produces("application/json")
	public Collection<Game> getAll(){
		return getAll(null, null, null, null, null, null, null, null, null, null);
	}
	

	@GET
	@Produces("application/json")
	public Collection<Game> getAll(@QueryParam("order") String order, @QueryParam("title") String title,
			@QueryParam("year") Integer year, @QueryParam("developerName") String developerName,
			@QueryParam("score") Double score, @QueryParam("platformName") String platformName,
			@QueryParam("genreName") String genreName, @QueryParam("mode") Mode mode,
			@QueryParam("limit") Integer limit, @QueryParam("offset") Integer offset) {
		Collection<Game> result = repository.getAllGames();
		if (!(title == null || title.equals(""))) {
			result = result.stream().filter(x -> x.getTitle().toLowerCase().contains(title.toLowerCase())).collect(Collectors.toList());
		}
		if (year!=null) {
			result = result.stream().filter(x -> x.getYear()>=year).collect(Collectors.toList());
		}
		if (!(developerName == null || developerName.equals(""))) {
			result = result.stream().filter(x -> x.getDeveloper().getName().toLowerCase().contains(developerName.toLowerCase())
					|| x.getDeveloper().getName().toLowerCase().equals(developerName.toLowerCase())).collect(Collectors.toList());
		}
		if (!(score == null || score.equals(0.0))) {
			result = result.stream().filter(x -> x.getScore() >= score).collect(Collectors.toList());

		}
		if (!(genreName == null || genreName.equals(""))) {
			Set<Game> aux = new HashSet<Game>();
			for (Game g : result){
				for (Genre ge : g.getGenres()) {
					if (ge.getName().toLowerCase().contains(genreName.toLowerCase())
							|| ge.getName().toLowerCase().equals(genreName.toLowerCase())) {
						aux.add(g);
					}
				}
			}
		}
		if (mode != null) {
			result = result.stream().filter(g -> g.getModes().contains(mode)).collect(Collectors.toList());
		}

		if (!(order == null || order.equals(""))) {
			Boolean noValido = false;
			switch (order) {
			default:
				noValido = true;
				break;
			case "title":
				result = result.stream().sorted(Comparator.comparing(Game::getTitle)).collect(Collectors.toList());
				break;
			case "-title":
				result = result.stream().sorted(Comparator.comparing(Game::getTitle).reversed()).collect(Collectors.toList());
				break;
			case "year":
				result = result.stream().sorted(Comparator.comparing(Game::getYear)).collect(Collectors.toList());
				break;
			case "-year":
				result = result.stream().sorted(Comparator.comparing(Game::getYear).reversed()).collect(Collectors.toList());
				break;
			case "developerName":
				result = result.stream().sorted(new GameDeveloperSorter()).collect(Collectors.toList());
				break;
			case "-developerName":
				result = result.stream().sorted(new GameDeveloperSorter().reversed()).collect(Collectors.toList());
				break;
			case "score":
				result = result.stream().sorted(Comparator.comparing(Game::getScore)).collect(Collectors.toList());
				break;
			case "-score":
				result = result.stream().sorted(Comparator.comparing(Game::getScore).reversed()).collect(Collectors.toList());
				break;
			case "genreName":
				result = result.stream().sorted(new GameGenreSorter()).collect(Collectors.toList());
				break;
			case "-genreName":
				result = result.stream().sorted(new GameGenreSorter().reversed()).collect(Collectors.toList());
				break;
			}
			if (noValido) {
				throw new BadRequestException(
						"The format of the order parameter must be title, -title, year, -year, country, or -country");
			}
		}

		List<Game> res = result.stream().collect(Collectors.toList());

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
	public Game get(@PathParam("id") String id) {
		return repository.getGame(id);
	}

	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response add(Game g) {
		if (g.getTitle() == null || "".equals(g.getTitle())) {
			throw new BadRequestException("The name of the game must not be null");
		}
		if (g.getDescription() == null || "".equals(g.getDescription())) {
			throw new BadRequestException("The name of the game must not be null");
		}
		if (g.getScore() == null || g.getScore().equals(0.0)) {
			throw new BadRequestException("The score of the game must not be null");
		}
		if (g.getDeveloper().equals(null)) {
			throw new BadRequestException("The developer of the game must not be null");
		}
		if (g.getGenres().isEmpty()) {
			throw new BadRequestException("The genres of the game must not be empty");
		}
		if (g.getModes().isEmpty()) {
			throw new BadRequestException("The modes of the game must not be empty");
		}
		repository.addGame(g);
		return Response.noContent().build();
	}

	@PUT
	@Consumes("application/json")
	public Response update(Game g) {
		Game old = repository.getGame(g.getId());
		if (old.equals(null)) {
			throw new NotFoundException("The game with id=" + g.getId() + " was not found");
		}
		if (g.getTitle() != null && !g.getTitle().equals("")) {
			old.setTitle(g.getTitle());
		}
		if (old.getYear() != null) {
			old.setYear(g.getYear());
		}
		if (old.getScore() != null && !g.getScore().equals(0.0)) {
			old.setScore(g.getScore());
		}
		repository.updateGame(old);
		return Response.noContent().build();
	}

	@DELETE
	@Path("/{id}")
	public Response delete(@PathParam("id") String id) {
		Game toRemove = repository.getGame(id);

		if (toRemove == null) {
			throw new NotFoundException("The game with id=" + id + " was not found");
		} else {
			repository.deleteGame(id);
		}
		return Response.noContent().build();
	}

}
