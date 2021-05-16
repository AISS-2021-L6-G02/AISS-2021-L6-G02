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
import javax.ws.rs.core.Response;

import org.jboss.resteasy.spi.BadRequestException;
import org.jboss.resteasy.spi.NotFoundException;
import org.restlet.resource.Delete;

import aiss.model.Genre;
import aiss.model.repository.DatabaseRepository;
import aiss.model.repository.MapDatabaseRepository;


@Path("/genres")
public class GenreResource {
	/* Singleton */
	private static GenreResource _instance=null;
	DatabaseRepository repository;

	public GenreResource(){
		repository=MapDatabaseRepository.getInstance();
	}

	public static GenreResource getInstance()
	{
		if(_instance==null)
			_instance=new GenreResource();
		return _instance; 
	}

	
	public Collection<Genre> getAll(){
		return getAll(null, null, null, null);
	}
	
	
	
	@GET
	@Produces("application/json")
	public Collection<Genre> getAll(@QueryParam("order") String order, @QueryParam("q") String q, @QueryParam("limit") Integer limit, @QueryParam("offset") Integer offset)
	{
		Stream<Genre> data = repository.getAllGenre().stream();
		boolean formatoValido;
		if(order!=null) {
			formatoValido = false;
			switch(order) {
			case "name":
				data = data.sorted(Comparator.comparing(Genre::getName));
				formatoValido = true;
				break;
			case "-name":
				data = data.sorted(Comparator.comparing(Genre::getName).reversed());
				formatoValido = true;
				break;
			case "description":
				data = data.sorted(Comparator.comparing(Genre::getDescription));
				formatoValido = true;
				break;
			case "-description":
				data = data.sorted(Comparator.comparing(Genre::getDescription).reversed());
				formatoValido = true;
				break;
			}
			if(!formatoValido) {
				throw new BadRequestException("The format of the order parameter is incorrect");
			}
		}
		if(q!=null) {
			final String q2 = q.toLowerCase();
			data = data.filter(x -> x.getName().toLowerCase().contains(q2) || x.getDescription().toLowerCase().contains(q2));
		}
		List<Genre> res = data.collect(Collectors.toList());

		if(offset==null) {
			offset = 0;
		}


		if(limit==null || limit+offset>res.size()) {
			limit = res.size();
		}
		else{
			limit += offset;
		}


		Collection<Genre> result = res.subList(offset, limit);

		return result;
	}


	@GET
	@Path("/{id}")
	@Produces("application/json")
	public Genre get(@PathParam("id") String Id)
	{
		Genre result = repository.getGenre(Id);
		if(result==null) {
			throw new NotFoundException("The genre with id=" + Id + "was not found!");
		}
		return result;
	}

	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response addGenre(Genre genre) {
		if(genre.getName()==null||genre.getDescription().equals("")) {
			throw new BadRequestException("The title of the genre must not be null");
		}
		repository.addGenre(genre);
		return Response.noContent().build();
	}


	@PUT
	@Consumes("application/json")
	public Response updateGenre(Genre genre) {
		Genre oldGenre = repository.getGenre(genre.getId());
		if(oldGenre==null) {
			throw new NotFoundException("The genre with id=" + genre.getId() + " was not found");
		}
		if(genre.getName()!=null) {
			oldGenre.setName(genre.getName());
		}
		if(genre.getDescription()!=null) {
			oldGenre.setDescription(genre.getDescription());
		}
		return Response.noContent().build();
	}

	@DELETE
	@Path("/{id}")
	public Response removeGenre(@PathParam("id") String Id) {
		Genre toRemove =repository.getGenre(Id);
		if(toRemove==null) {
			throw new NotFoundException("The genre with id=" + Id + " was not found");
		}
		else {
			repository.deleteGenre(Id);
		}
		return Response.noContent().build();
	}

}
