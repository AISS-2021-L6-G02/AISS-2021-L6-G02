package aiss.model.resource;

import static org.junit.Assert.assertNotNull;

import java.util.Collection;

import org.junit.Test;

import aiss.api.resources.SerieResource;
import aiss.model.Serie;



public class TestSerieResource {
	public static SerieResource sr = new SerieResource();
	@Test
	public void testGetAllSeries() {
		Collection<Serie> series = sr.getAllSeries();
		
		assertNotNull("The collection of series is null", series);
		System.out.println("===========\ntestGetAllSeries\n=========");

		// Show result
		System.out.println("Listing all series:");
		int i=1;
		for (Serie s : series) {
			System.out.println("Serie " + i++ + " : " + s.getTitle() + " (ID=" + s.getId() + ")");
		}
	}

}
