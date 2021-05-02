package aiss.api.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import org.jboss.resteasy.spi.*;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.UriBuilder;

import aiss.model.Song;
import aiss.model.repository.MapPlaylistRepository;
import aiss.model.repository.PlaylistRepository;

import java.net.URI;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;



@Path("/songs")
public class SongResource {

	public static SongResource _instance=null;
	PlaylistRepository repository;

	private SongResource(){
		repository=MapPlaylistRepository.getInstance();
	}

	public static SongResource getInstance()
	{
		if(_instance==null)
			_instance=new SongResource();
		return _instance; 
	}

	@GET
	@Produces("application/json")
	public Collection<Song> getAll(@QueryParam("order") String order, @QueryParam("q") String q, @QueryParam("limit") Integer limit, @QueryParam("offset") Integer offset)
	{
		Stream<Song> data = repository.getAllSongs().stream();
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


	@GET
	@Path("/{id}")
	@Produces("application/json")
	public Song get(@PathParam("id") String songId)
	{
		Song result = repository.getSong(songId);
		if(result==null) {
			throw new NotFoundException("The song with id=" + songId + "was not found!");
		}
		return result;
	}

	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response addSong(@Context UriInfo uriInfo, Song song) {
		if(song.getTitle()==null||song.getTitle().equals("")) {
			throw new BadRequestException("The title of the song must not be null");
		}
		repository.addSong(song);

		UriBuilder ub = uriInfo.getAbsolutePathBuilder().path(this.getClass(), "get");
		URI uri = ub.build(song.getId());
		ResponseBuilder resp = Response.created(uri);
		resp.entity(song);
		return resp.build();
	}


	@PUT
	@Consumes("application/json")
	public Response updateSong(Song song) {
		Song oldSong = repository.getSong(song.getId());
		if(oldSong==null) {
			throw new NotFoundException("The song with id=" + song.getId() + "was not found");
		}
		if(song.getTitle()!=null) {
			oldSong.setTitle(song.getTitle());
		}
		if(song.getAlbum()!=null) {
			oldSong.setAlbum(song.getAlbum());
		}
		if(song.getArtist()!=null) {
			oldSong.setArtist(song.getArtist());
		}
		if(song.getYear()!=null) {
			oldSong.setYear(song.getYear());
		}
		return Response.noContent().build();
	}

	@DELETE
	@Path("/{id}")
	public Response removeSong(@PathParam("id") String songId) {
		Song toRemove =repository.getSong(songId);
		if(toRemove==null) {
			throw new NotFoundException("The song with id=" + songId + "was not found");
		}
		else {
			repository.deleteSong(songId);
		}
		return Response.noContent().build();
	}

}
