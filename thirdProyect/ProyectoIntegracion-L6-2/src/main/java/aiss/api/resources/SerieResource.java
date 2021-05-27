package aiss.api.resources;

import java.util.Arrays;
import java.util.Collection;

import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

import aiss.model.Serie;


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
			cr = new ClientResource(uri);
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
}
