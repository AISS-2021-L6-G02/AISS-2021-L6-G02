package aiss.api.resources;

import java.util.Arrays;
import java.util.Collection;

import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

import aiss.model.Film;

public class FilmResource {

private String uri = "https://proyecto-integracion-l3-g6.appspot.com/api/films";

private static FilmResource instance = null;

	
	public static FilmResource getInstance() {
		if(instance==null) {
			instance = new FilmResource();
		}
		return instance;
	}
	
	public Collection<Film> getAllFilms(){
		ClientResource cr = null;
		Film[] films = null;
		try {
			cr = new ClientResource(uri);
			films = cr.get(Film[].class);
		}catch(ResourceException e) {
			System.err.println("Error when retrieven all films: "+ cr.getResponse().getStatus());
		}
		for(int i=0; i<films.length;i++) {
			System.out.println((i+1)+": "+films[i].getTitle());
		}
		return Arrays.asList(films);
	}
	
	public Film getFilm(String id) {
		ClientResource cr = null;
		Film f = null;
		try {
			cr = new ClientResource(uri + "/" + id);
			f = cr.get(Film.class);
		} catch(ResourceException re) {
			System.err.println("Error when retrieving a film: " + cr.getResponse().getStatus());
			throw re;
		}
		return f;
	}
	
	public Film addFilm(Film f) {
		ClientResource cr = null;
		Film film = null;
		try {
			cr = new ClientResource(uri);
			cr.setEntityBuffering(true);
			film = cr.post(f, Film.class);
		} catch(ResourceException re) {
			System.err.println("Error when adding the film: " + cr.getResponse().getStatus());
			throw re;
		}
		
		return film;
	}
	
	public boolean updateFilm(Film f) {
		return false;
		
	}
	
	public boolean deleteFilm(String id) {
		ClientResource cr = null;
		boolean success = true;
		try {
			cr = new ClientResource(uri + "/" + id);
			cr.setEntityBuffering(true);		// Needed for using RESTlet from JUnit tests
			cr.delete();
			
		} catch (ResourceException re) {
			System.err.println("Error when deleting the book: " + cr.getResponse().getStatus());
			success = false;
		}
		return success;
	}
}
