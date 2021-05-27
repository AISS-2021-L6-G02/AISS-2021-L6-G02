package aiss.model.resource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Collection;
import java.util.stream.Collectors;

import org.junit.BeforeClass;
import org.junit.Test;

import aiss.api.resources.SerieResource;
import aiss.model.Serie;



public class TestSerieResource {
	public static SerieResource sr = new SerieResource();
	public static Collection<Serie> series = null;
	@BeforeClass
	public static void setUp() throws Exception {
		series = sr.getAllSeries();
	}
	@Test
	public void testGetAllSeries() {
		Collection<Serie> test = sr.getAllSeries();
		
		assertNotNull("The collection of series is null", test);
		System.out.println("===========\ntestGetAllSeries\n=========");

		// Show result
		System.out.println("Listing all series:");
		
		for (Serie s : test) {
			System.out.println(s);
		}
	}

	@Test
	public void testGetOneSerie() {
		System.out.println("============================");
		System.out.println("TEST GET ONE SERIE BY ID");
		System.out.println("============================");
		Serie serie = series.stream().collect(Collectors.toList()).get(0);
		String id = serie.getId();
		Serie serieGetOne = sr.getOneSerie(id);
		assertNotNull("The serie is null", serieGetOne);
		assertEquals("The serie doesnt match with the obtained by getAllSeries()", serie.getId(),
				serieGetOne.getId());
		System.out.println(serieGetOne);

	}

}
