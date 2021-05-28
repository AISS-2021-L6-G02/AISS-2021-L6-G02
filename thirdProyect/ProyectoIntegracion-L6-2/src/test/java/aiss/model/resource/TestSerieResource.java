package aiss.model.resource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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
		Serie serie = sr.getAllSeries().stream().collect(Collectors.toList()).get(0);
		String id = serie.getId();
		Serie serieGetOne = sr.getOneSerie(id);
		assertNotNull("The serie is null", serieGetOne);
		assertEquals("The serie doesnt match with the obtained by getAllSeries()", serie.getId(),
				serieGetOne.getId());
		System.out.println(serieGetOne);

	}
	
	
	@Test
	public void testUpdateSerie() {
		System.out.println("============================");
		System.out.println("TEST UPDATE SERIE");
		System.out.println("============================");
		Serie s = new Serie();
		Serie old = sr.getAllSeries().stream().collect(Collectors.toList()).get(0);
		s.setGenre(old.getGenre()+" test");
		s.setNumEpisodes(old.getNumEpisodes()+"100");
		s.setStartYear(old.getStartYear()+"0");
		s.setTitle(old.getTitle()+" test title");
		s.setId(old.getId());
		boolean result = sr.updateSerie(s);
		Serie updated = new Serie();
		for(Serie sx: sr.getAllSeries()) {
			if(sx.getId().equals(s.getId())) {
				updated = sx;
				break;
			}
		}
		assertNotEquals("The name of the serie is equals", old.getTitle(), updated.getTitle());
		assertNotEquals("The year of the serie is equals", old.getStartYear(), updated.getStartYear());
		assertNotEquals("The episodes of the serie is equals", old.getNumEpisodes(), updated.getNumEpisodes());
		assertNotEquals("The genre of the serie is equals", old.getGenre(), updated.getGenre());



		System.out.println("Serie updated--> "+updated);

	}
	@Test
	public void testDeleteSerie() {
		System.out.println("============================");
		System.out.println("TEST DELETE SERIE");
		System.out.println("============================");
		Serie old = sr.getAllSeries().stream().collect(Collectors.toList()).get(0);
		
		boolean result = sr.deleteSerie(old.getId());
		sr.addSerie(old);
		assertFalse("The serie hasn't been deleted correctly", !result);



		System.out.println("Serie deleted--> "+result);
		

	}


}
