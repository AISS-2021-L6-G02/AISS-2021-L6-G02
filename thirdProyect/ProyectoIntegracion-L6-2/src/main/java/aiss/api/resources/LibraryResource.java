package aiss.api.resources;

import java.util.Arrays;
import java.util.Collection;

import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

import aiss.model.Film;
import aiss.model.Library;

public class LibraryResource {
	private static LibraryResource instance = null;
	private static String uri = "https://proyecto-integracion-l3-g6.appspot.com/api/libraries";
	
	public static LibraryResource getInstance() {
		if(instance==null) {
			instance = new LibraryResource();
		}
		return instance;
	}

	public Collection<Library> getAllLibraries(){
		ClientResource cr = null;
		Library[] libs = null;
		try {
			cr = new ClientResource(uri);
			libs = cr.get(Library[].class);
		}catch(ResourceException e) {
			System.err.println("Error when retrieving all books: " + cr.getResponse().getStatus());
			throw e;
		}
		for(int i=0; i<libs.length;i++) {
			System.out.println((i+1)+": "+libs[i].getName());
		}
		return Arrays.asList(libs);
	}
	
	public Library getOneLibrary(String id) {
		ClientResource cr = null;
		Library lib = null;
		try {
			cr = new ClientResource(uri+"/"+id);
			lib = cr.get(Library.class);
		}catch(ResourceException e){
			System.err.println("Error when retrieving a library "+ cr.getResponse().getStatus());
			throw e;
		}
		return lib;
	}
	
	public Library addLibrary(Library lib) {
		ClientResource cr = null;
		Library l = null;
		try {
			cr = new ClientResource(uri);
			cr.setEntityBuffering(true);
			l = cr.post(lib, Library.class);
		} catch(ResourceException re) {
			System.err.println("Error when adding the lib: " + cr.getResponse().getStatus());
			throw re;
		}
		
		return l;
	}
	public boolean updateLibrary(Library lib) {
		return false;
	}
	
	public boolean deleteLibrary(String id) {
		ClientResource cr = null;
		boolean success = true;
		try {
			cr = new ClientResource(uri + "/" + id);
			cr.setEntityBuffering(true);		// Needed for using RESTlet from JUnit tests
			cr.delete();
			
		} catch (ResourceException re) {
			System.err.println("Error when deleting the library: " + cr.getResponse().getStatus());
			success = false;
		}
		return success;
	}
	
}
