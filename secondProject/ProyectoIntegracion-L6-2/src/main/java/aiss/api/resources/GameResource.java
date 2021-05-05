package aiss.api.resources;

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

import aiss.model.Developer;
import aiss.model.Game;
import aiss.model.Genre;
import aiss.model.Mode;
import aiss.model.Platform;
import aiss.model.repository.GameRepository;
import aiss.model.repository.MapGameRepository;

@Path("/games")
public class GameResource {
	private static GameResource instance = null;
	GameRepository repository;
	
	private GameResource() {
		repository = MapGameRepository.getInstance();
	}
	public static GameResource getInstance() {
		if(instance==null) {
			instance = new GameResource();
		}
		return instance;
	}
	
	@GET
	@Produces("application/json")
	public Collection<Game> getAll(){
		return getAll(null, null, null, null, null, null);
	}
	
	
	/*
	 * private String id;
	private String title;
	private String description;
	private Integer year;
	private Developer developer;
	private Double score;
	private List<Platform> platforms;
	private List<Genre> genres;
	private List<Mode> modes;
	 */
	@GET
	@Produces("application/json")
	public Collection<Game> getAll(@QueryParam("order") String order, 
			@QueryParam("title") String title,@QueryParam("year") String year,
			@QueryParam("developerName") String developerName, @QueryParam("score") Double score,
			@QueryParam("platformName") Double platformName,
			@QueryParam("genreName") Double genreName,
			@QueryParam("mode") String mode,
			@QueryParam("limit") Integer limit, @QueryParam("offset") Integer offset){
		Stream<Game> result = repository.getAllGames().stream();
		if(!(title==null || !title.equals(""))) {
			result = result.filter(x->x.getTitle().toLowerCase().contains(title.toLowerCase()) 
					|| x.getTitle().toLowerCase().equals(title.toLowerCase()));
		}
		if(!(year.equals(null)|| !year.equals(0))) {
			result = result.filter(x-> x.getYear().equals(year.toLowerCase()));
		}
		if(!(developerName==null || developerName.equals(""))) {
			result = result.filter(x->x.getTitle().toLowerCase().contains(title.toLowerCase()) 
					|| x.getTitle().toLowerCase().equals(title.toLowerCase()));
		}
		if(!(title==null || title.equals(""))) {
			result = result.filter(x->x.getTitle().toLowerCase().contains(title.toLowerCase()) 
					|| x.getTitle().toLowerCase().equals(title.toLowerCase()));
		}
		if(!(title==null || title.equals(""))) {
			result = result.filter(x->x.getTitle().toLowerCase().contains(title.toLowerCase()) 
					|| x.getTitle().toLowerCase().equals(title.toLowerCase()));
		}
		
		if(!(order==null || order.equals(""))) {
			Boolean noValido = false;
			switch(order) {
			default:
				noValido = true;
				break;
			case "name":
				result = result.sorted(Comparator.comparing(Developer::getName));
				break;
			case "-name":
				result = result.sorted(Comparator.comparing(Developer::getName).reversed());
				break;
			case "year":
				result = result.sorted(Comparator.comparing(Developer::getYear));
				break;
			case "-year":
				result = result.sorted(Comparator.comparing(Developer::getYear).reversed());
				break;
			case "country":
				result = result.sorted(Comparator.comparing(Developer::getCountry));
				break;
			case "-country":
				result = result.sorted(Comparator.comparing(Developer::getCountry).reversed());
				break;
			}
			if(noValido) {
				throw new BadRequestException("The format of the order parameter must be name, -name, year, -year, country, or -country");
			}
		}
		
		List<Developer> res = result.collect(Collectors.toList());
		
		if(offset==null) {
			offset = 0;
		}

		if(limit==null || limit+offset>res.size()) {
			limit = res.size();
		}
		
		else{
			limit += offset;
		}
		return res.subList(offset, limit);
	}
	
	@GET
	@Path("/{id}")
	@Produces("application/json")
	public Developer get(@PathParam("id") String id) {
		return repository.getDeveloper(id);
	}
	
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response addDeveloper(Developer dev) {
		if(dev.getName()==null || "".equals(dev.getName())) {
			throw new BadRequestException("The name of the developer must not be null");
		}
		repository.addDeveloper(dev);
		return Response.noContent().build();
	}
	
	@PUT
	@Consumes("application/json")
	public Response updateDeveloper(Developer dev) {
		Developer oldDev = repository.getDeveloper(dev.getId());
		if(oldDev==null) {
			throw new NotFoundException("The developer with id="+ dev.getId() +" was not found");
		}
		if(dev.getName()!=null && !dev.getName().equals("")) {
			oldDev.setName(dev.getName());
		}
		if(dev.getYear()!=null) {
			oldDev.setYear(dev.getYear());
		}
		if(dev.getCountry()!=null && !dev.getCountry().equals("")) {
			oldDev.setCountry(dev.getCountry());
		}
		repository.updateDeveloper(oldDev);
		return Response.noContent().build();
	}
	@DELETE
	@Path("/{id}")
	public Response deleteDeveloper(@PathParam("id") String id) {
		Developer toRemove = repository.getDeveloper(id);
		
		if (toRemove == null) {
			throw new NotFoundException("The developer with id="+ id +" was not found");
		}
		else {
			repository.deleteDeveloper(id);
		}
		return Response.noContent().build();
	}

}
