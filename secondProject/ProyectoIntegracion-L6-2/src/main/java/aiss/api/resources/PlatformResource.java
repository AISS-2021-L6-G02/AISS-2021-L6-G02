package aiss.api.resources;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.jboss.resteasy.spi.BadRequestException;

import aiss.model.Platform;
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
		boolean formatoValido;
		if(order!=null) {
			formatoValido = false;
			switch(order) {
			case "album":
				data = data.sorted(Comparator.comparing(Song::getAlbum));
				formatoValido = true;
				break;
			case "-album":
				data = data.sorted(Comparator.comparing(Song::getAlbum).reversed());
				formatoValido = true;
				break;
			case "artist":
				data = data.sorted(Comparator.comparing(Song::getArtist));
				formatoValido = true;
				break;
			case "-artist":
				data = data.sorted(Comparator.comparing(Song::getArtist).reversed());
				formatoValido = true;
				break;
			case "year":
				data = data.sorted(Comparator.comparing(Song::getYear));
				formatoValido = true;
				break;
			case "-year":
				data = data.sorted(Comparator.comparing(Song::getYear).reversed());
				formatoValido = true;
				break;
			}
			if(!formatoValido) {
				throw new BadRequestException("The format of the order parameter is incorrect");
			}
		}
		if(q!=null) {
			final String q2 = q.toLowerCase();
			data = data.filter(x -> x.getTitle().toLowerCase().contains(q2) || x.getAlbum().toLowerCase().contains(q2) || x.getArtist().toLowerCase().contains(q2));
		}
		List<Song> res = data.collect(Collectors.toList());

		if(offset==null) {
			offset = 0;
		}


		if(limit==null || limit+offset>res.size()) {
			limit = res.size();
		}
		else{
			limit += offset;
		}


		Collection<Song> result = res.subList(offset, limit);

		return result;
	}

	
	
}
