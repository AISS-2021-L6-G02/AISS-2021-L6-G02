package aiss.api.resources;

import java.util.Arrays;
import java.util.Collection;

import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

import aiss.model.Serie;
import utils.GeneratorClient;


public class SerieResource {
	private static SerieResource instance = null;
	private String uri = "http://aisseries.appspot.com/api/series";

	
	
	public static SerieResource getInstance() {
		if(instance==null) {
			instance = new SerieResource();
		}
		return instance;
	}
	
	public Collection<Serie> getAllSeries(){
		System.out.println("==================");
		System.out.println("getAllSeries in serieResource");
		System.out.println("==================");
		ClientResource cr = null;
		Serie [] series = null;
		try {
			cr = GeneratorClient.generate(uri);
			series = cr.get(Serie[].class);
			
			
		} catch (ResourceException re) {
			System.err.println("Error when retrieving all series: " + cr.getResponse().getStatus());
			throw re;
		}
		for (int i = 0; i < series.length; i++) {
			System.out.println((i+1)+series[i].getTitle());
		}
		return Arrays.asList(series);
	}
	public Serie getOneSerie(String id) {
		System.out.println("==================");
		System.out.println("getOneSerie in serieResource");
		System.out.println("==================");
		ClientResource cr = null;
		Serie  serie = null;
		
		try {
			cr = new ClientResource(uri+"/"+id);
			serie = cr.get(Serie.class);
			
			
		} catch (ResourceException re) {
			System.err.println("Error when retrieving one series: " + cr.getResponse().getStatus());
			throw re;
		}
		return serie;
	}
	public Serie addSerie(Serie s) {
		ClientResource cr = null;
		Serie resultSerie = null;
		try {
			
		} catch (ResourceException e) {
			System.err.println("Error adding a serie."+cr.getResponse().getStatus());
		}
		return resultSerie;
	}
	/*public Playlist addPlaylist(Playlist pl) {
		
		ClientResource cr = null;
		Playlist resultPlaylist = null;
		try {
			cr = new ClientResource(uri);
			cr.setEntityBuffering(true);		// Needed for using RESTlet from JUnit tests
			resultPlaylist = cr.post(pl,Playlist.class);
			
		} catch (ResourceException re) {
			System.err.println("Error when adding the playlist: " + cr.getResponse().getStatus());
		}
		
		return resultPlaylist;
	}
	

	public boolean updatePlaylist(Playlist pl) {
		ClientResource cr = null;
		boolean success = true;
		try {
			cr = new ClientResource(uri);
			cr.setEntityBuffering(true);		// Needed for using RESTlet from JUnit tests
			cr.put(pl);
			
			
		} catch (ResourceException re) {
			System.err.println("Error when updating the playlist: " + cr.getResponse().getStatus());
			success = false;
		}
		
		return success;
	}
	
	
	public boolean deletePlaylist(String playlistId) {
		ClientResource cr = null;
		boolean success = true;
		try {
			cr = new ClientResource(uri + "/" + playlistId);
			cr.setEntityBuffering(true);		// Needed for using RESTlet from JUnit tests
			cr.delete();
			
		} catch (ResourceException re) {
			System.err.println("Error when deleting the playlist: " + cr.getResponse().getStatus());
			success = false;
		}
		
		return success;
	}*/
}
