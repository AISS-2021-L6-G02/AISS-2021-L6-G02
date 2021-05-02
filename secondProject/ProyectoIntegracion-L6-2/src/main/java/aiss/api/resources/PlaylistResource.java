package aiss.api.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

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
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import org.jboss.resteasy.spi.BadRequestException;
import org.jboss.resteasy.spi.NotFoundException;

import aiss.model.Playlist;
import aiss.model.Song;
import aiss.model.repository.MapPlaylistRepository;
import aiss.model.repository.PlaylistRepository;





@Path("/lists")
public class PlaylistResource {
	
	/* Singleton */
	private static PlaylistResource _instance=null;
	PlaylistRepository repository;
	
	private PlaylistResource() {
		repository=MapPlaylistRepository.getInstance();

	}
	
	public static PlaylistResource getInstance()
	{
		if(_instance==null)
				_instance=new PlaylistResource();
		return _instance;
	}
	

	/*
	 * Modifique el método getAll() de la clase PlaylistResource.java para
implementar los mecanismos de filtrados y ordenación vistos. Es decir, que
permita filtrar Playlists por nombre y dependiendo de si están vacías o no. Además,
ordénelas alfabétiamente según su nombre (ascendente o descendente).
Probar la nueva implementación haciendo uso de alguna extensión de navegador.
 Liste todas las Playlist
 Cree cuatro nuevas Playlist:
1. Nombre: “Últimos éxitos”; Descripción: “Favoritas de los últimos tiempos”
2. Nombre: “Canciones infantiles”; Descripción: “Canciones para peques”
3. Nombre: “Recuerdos”; Descripción: “Recuerdos de la infancia”
4. Nombre: “AISSPlayList”; Descripción: “Nueva lista de prueba”
 Liste todas las Playlist ordenándolas de forma ascendente y descendente según
nombre de Playlist.
 Liste todas las Playlist sin canciones y ordenándolas descendentemente.
 Listae todas las Playlist según se indica:
o Con nombre: “Éxitos en inglés”
o Con nombre: “AISSPlayList”
o Con nombre: “AISSPlayList” que no tengan canciones asociadas.
 Modifique una de las Playlist con nombre “AISSPlayList” para asignarle otro nombre
y otra descripción. Verifique se aplican los cambios obteniendo los valores
únicamente de esa lista.
	 */
	@GET
	@Produces("application/json")
	public Collection<Playlist> getAll(@QueryParam("order") String order){
		ArrayList<Playlist> r = new ArrayList<>(repository.getAllPlaylists());

	{
		if(order!=null) {
			if(order.equals("name")) {
				Collections.sort(r, new ComparatorNamePlaylist());
			}else if(order.equals("-name")) {
				Collections.sort(r,  new ComparatorNamePlaylistReversed());
			}else {
				throw new BadRequestException("The order parameter must be name or -name");
			}
		}
		
		return r;
	}}
	
	
	
	@GET
	@Path("/{id}")
	@Produces("application/json")
	public Playlist get(@PathParam("id") String id)
	{
		Playlist list = repository.getPlaylist(id);
		
		if (list == null) {
			throw new NotFoundException("The playlist with id="+ id +" was not found");			
		}
		
		return list;
	}
	
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response addPlaylist(@Context UriInfo uriInfo, Playlist playlist) {
		if (playlist.getName() == null || "".equals(playlist.getName()))
			throw new BadRequestException("The name of the playlist must not be null");
		
		if (playlist.getSongs()!=null)
			throw new BadRequestException("The songs property is not editable.");

		repository.addPlaylist(playlist);

		// Builds the response. Returns the playlist the has just been added.
		UriBuilder ub = uriInfo.getAbsolutePathBuilder().path(this.getClass(), "get");
		URI uri = ub.build(playlist.getId());
		ResponseBuilder resp = Response.created(uri);
		resp.entity(playlist);			
		return resp.build();
	}

	
	@PUT
	@Consumes("application/json")
	public Response updatePlaylist(Playlist playlist) {
		Playlist oldplaylist = repository.getPlaylist(playlist.getId());
		if (oldplaylist == null) {
			throw new NotFoundException("The playlist with id="+ playlist.getId() +" was not found");			
		}
		
		if (playlist.getSongs()!=null)
			throw new BadRequestException("The songs property is not editable.");
		
		// Update name
		if (playlist.getName()!=null)
			oldplaylist.setName(playlist.getName());
		
		// Update description
		if (playlist.getDescription()!=null)
			oldplaylist.setDescription(playlist.getDescription());
		
		return Response.noContent().build();
	}
	
	@DELETE
	@Path("/{id}")
	public Response removePlaylist(@PathParam("id") String id) {
		Playlist toberemoved=repository.getPlaylist(id);
		if (toberemoved == null)
			throw new NotFoundException("The playlist with id="+ id +" was not found");
		else
			repository.deletePlaylist(id);
		
		return Response.noContent().build();
	}
	
	
	@POST	
	@Path("/{playlistId}/{songId}")
	@Consumes("text/plain")
	@Produces("application/json")
	public Response addSong(@Context UriInfo uriInfo,@PathParam("playlistId") String playlistId, @PathParam("songId") String songId)
	{				
		
		Playlist playlist = repository.getPlaylist(playlistId);
		Song song = repository.getSong(songId);
		
		if (playlist==null)
			throw new NotFoundException("The playlist with id=" + playlistId + " was not found");
		
		if (song == null)
			throw new NotFoundException("The song with id=" + songId + " was not found");
		
		if (playlist.getSong(songId)!=null)
			throw new BadRequestException("The song is already included in the playlist.");
			
		repository.addSong(playlistId, songId);		

		// Builds the response
		UriBuilder ub = uriInfo.getAbsolutePathBuilder().path(this.getClass(), "get");
		URI uri = ub.build(playlistId);
		ResponseBuilder resp = Response.created(uri);
		resp.entity(playlist);			
		return resp.build();
	}
	
	
	@DELETE
	@Path("/{playlistId}/{songId}")
	public Response removeSong(@PathParam("playlistId") String playlistId, @PathParam("songId") String songId) {
		Playlist playlist = repository.getPlaylist(playlistId);
		Song song = repository.getSong(songId);
		
		if (playlist==null)
			throw new NotFoundException("The playlist with id=" + playlistId + " was not found");
		
		if (song == null)
			throw new NotFoundException("The song with id=" + songId + " was not found");
		
		
		repository.removeSong(playlistId, songId);		
		
		return Response.noContent().build();
	}
}