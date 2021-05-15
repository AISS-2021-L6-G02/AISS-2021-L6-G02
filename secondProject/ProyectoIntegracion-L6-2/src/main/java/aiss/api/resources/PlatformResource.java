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

import aiss.model.Platform;
import aiss.model.repository.DatabaseRepository;
import aiss.model.repository.MapDatabaseRepository;


@Path("/platforms")
public class PlatformResource {
	private static PlatformResource _instance = null;
	DatabaseRepository repository;

	public PlatformResource() {
		repository = MapDatabaseRepository.getInstance();
	}

	public static PlatformResource getInstance() {
		if (_instance == null)
			_instance = new PlatformResource();
		return _instance;
	}
	
	
	public Collection<Platform> getAll(){
		return getAll(null, null, null, null);
	}
	
	
	
	@GET
	@Produces("application/json")
	public Collection<Platform> getAll(@QueryParam("order") String order, @QueryParam("name") String name,  
			@QueryParam("limit") Integer limit, @QueryParam("offset") Integer offset){
		Stream<Platform> result = repository.getAllPlatforms().stream();
		if(!(name==null || name.equals(""))) {
			result = result.filter(x->x.getName().toLowerCase().contains(name.toLowerCase()) || x.getName().toLowerCase().equals(name.toLowerCase()));
		}
		
		if(!(order==null || order.equals(""))) {
			Boolean noValido = false;
			switch(order) {
			default:
				noValido = true;
				break;
			case "name":
				result = result.sorted(Comparator.comparing(Platform::getName));
				break;
			case "-name":
				result = result.sorted(Comparator.comparing(Platform::getName).reversed());
				break;
			
			}
			if(noValido) {
				throw new BadRequestException("The format of the order parameter must be name, -name, year, -year, country, or -country");
			}
		}
			List<Platform> res = result.collect(Collectors.toList());


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
	public Platform get(@PathParam("id") String id)
	{
		Platform list = repository.getPlatform(id);
		
		if (list == null) {
			throw new NotFoundException("The playlist with id="+ id +" was not found");			
		}
		
		return list;
	}
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response addPlatform(Platform p) {
		
		if (p.getName() == null || "".equals(p.getName()))
			throw new BadRequestException("The name of the platform must not be null");
		

		repository.addPlatform(p);

		return Response.noContent().build();
	}
	@PUT
	@Consumes("application/json")
	public Response updatePlatform(Platform p) {
		Platform oldPlatform = repository.getPlatform(p.getId());
		if (oldPlatform == null) {
			throw new NotFoundException("The platform with id="+ p.getId() +" was not found");			
		}
		
		
		// Update name
		if (p.getName()!=null)
			oldPlatform.setName(p.getName());
		
		
		return Response.noContent().build();
	}
	
	@DELETE
	@Path("/{id}")
	public Response removePlatform(@PathParam("id") String id) {
		Platform toRemove =repository.getPlatform(id);
		if(toRemove==null) {
			throw new NotFoundException("The platform with id=" + id + "was not found");
		}
		else {
			repository.getPlatform(id);
		}
		return Response.noContent().build();
	}
	
	
	
	

	
	
}
