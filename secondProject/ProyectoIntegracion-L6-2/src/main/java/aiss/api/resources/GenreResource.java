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

import aiss.model.Genre;
import aiss.model.repository.GenreRepository;
import aiss.model.repository.MapGenreRepository;

public class GenreResource {
	/* Singleton */
	private static GenreResource _instance=null;
	GenreRepository repository;

	private GenreResource(){
		repository=MapGenreRepository.getInstance();
	}

	public static GenreResource getInstance()
	{
		if(_instance==null)
			_instance=new GenreResource();
		return _instance; 
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
			throw new NotFoundException("The song with id=" + Id + "was not found!");
		}
		return result;
	}

	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response addGenre(@Context UriInfo uriInfo, Genre genre) {
		if(genre.getName()==null||genre.getDescription().equals("")) {
			throw new BadRequestException("The title of the song must not be null");
		}
		repository.addGenre(genre);
		return Response.noContent().build();
	}


	@PUT
	@Consumes("application/json")
	public Response updateGenre(Genre genre) {
		Genre oldGenre = repository.getGenre(genre.getId());
		if(oldGenre==null) {
			throw new NotFoundException("The song with id=" + genre.getId() + "was not found");
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
			throw new NotFoundException("The song with id=" + Id + "was not found");
		}
		else {
			repository.deleteGenre(Id);
		}
		return Response.noContent().build();
	}

}
