package aiss.api.resources;

import java.net.URI;
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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.jboss.resteasy.spi.BadRequestException;
import org.jboss.resteasy.spi.NotFoundException;

import aiss.model.Platform;
import aiss.model.Playlist;
import aiss.model.repository.MapPlatformRepository;
import aiss.model.repository.PlatformRepository;

@Path("/platforms")
public class PlatformResource {
	private static PlatformResource _instance = null;
	PlatformRepository repository;

	private PlatformResource() {
		repository = MapPlatformRepository.getInstance();
	}

	public static PlatformResource getInstance() {
		if (_instance == null)
			_instance = new PlatformResource();
		return _instance;
	}
	@GET
	@Produces("application/json")
	public Collection<Platform> getAll(@QueryParam("order") String order, @QueryParam("q") String q, @QueryParam("limit") Integer limit, @QueryParam("offset") Integer offset)
	{
		Stream<Platform> data = repository.getAllPlatforms().stream();
		boolean validFormat;
		if(order!=null) {
			validFormat = false;
			if(order.toLowerCase().equals("name")) {
				data.sorted(Comparator.comparing(Platform::getName));
				validFormat=true;
			}else if(order.toLowerCase().equals("-name")) {
				data.sorted(Comparator.comparing(Platform::getName).reversed());
				validFormat=true;
			}
			if(!validFormat) {
				throw new BadRequestException("The format of the order parameter is incorrect");
			}
		}
		if(q!=null) {
			final String q2 = q.toLowerCase();
			data = data.filter(x -> x.getName().toLowerCase().contains(q2) );
		}
		List<Platform> res = data.collect(Collectors.toList());

		if(offset==null) {
			offset = 0;
		}


		if(limit==null || limit+offset>res.size()) {
			limit = res.size();
		}
		else{
			limit += offset;
		}


		Collection<Platform> result = res.subList(offset, limit);

		return result;
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
	public Response addPlatform(@Context UriInfo uriInfo, Platform p) {
		if (p.getName() == null || "".equals(p.getName()))
			throw new BadRequestException("The name of the platform must not be null");
		

		repository.addPlatform(p);

		UriBuilder ub = uriInfo.getAbsolutePathBuilder().path(this.getClass(), "get");
		URI uri = ub.build(p.getId());
		ResponseBuilder resp = Response.created(uri);
		resp.entity(p);			
		return resp.build();
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
