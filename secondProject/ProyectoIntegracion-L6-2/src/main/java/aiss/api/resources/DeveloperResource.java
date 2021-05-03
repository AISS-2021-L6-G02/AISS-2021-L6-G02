package aiss.api.resources;

import java.net.URI;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.jboss.resteasy.spi.BadRequestException;
import org.jboss.resteasy.spi.NotFoundException;

import aiss.model.Developer;
import aiss.model.repository.DeveloperRepository;
import aiss.model.repository.MapDeveloperRepository;

@Path("/developers")
public class DeveloperResource {
	private static DeveloperResource instance = null;
	DeveloperRepository repository;
	
	private DeveloperResource() {
		repository = MapDeveloperRepository.getInstance();
	}
	public static DeveloperResource getInstance() {
		if(instance==null) {
			instance = new DeveloperResource();
		}
		return instance;
	}
	@GET
	@Produces("application/json")
	public Collection<Developer> getAll(@QueryParam("order") String order, @QueryParam("name") String name, @QueryParam("country") String country, @QueryParam("year") Integer year, @QueryParam("limit") Integer limit, @QueryParam("offset") Integer offset){
		Stream<Developer> result = repository.getAllDevelopers().stream();
		if(name!=null) {
			result = result.filter(x->x.getName().toLowerCase().contains(name.toLowerCase()));
		}
		if(country!=null) {
			result = result.filter(x-> x.getCountry().toLowerCase().equals(country.toLowerCase()));
		}
		if(year!=null) {
			result = result.filter(x-> x.getYear()>=year);
		}
		if(order!=null) {
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
	public Response addDeveloper(@Context UriInfo uriInfo, Developer dev) {
		if(dev.getName()==null || "".equals(dev.getName())) {
			throw new BadRequestException("The name of the developer must not be null");
		}
		repository.addDeveloper(dev);
		UriBuilder ub = uriInfo.getAbsolutePathBuilder().path(this.getClass(), "get");
		URI uri = ub.build(dev.getId());
		ResponseBuilder resp = Response.created(uri);
		resp.entity(dev);			
		return resp.build();
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
			throw new NotFoundException("The playlist with id="+ id +" was not found");
		}
		else {
			repository.deleteDeveloper(id);
		}
		return Response.noContent().build();
	}
}