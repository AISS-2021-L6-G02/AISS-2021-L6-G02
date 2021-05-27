package aiss.model.resources;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.junit.Test;

import aiss.model.omdb.MovieSearch;
import aiss.model.omdb.Search;

public class OMDbTest {

	@Test
	public void getMoviesTest() throws UnsupportedEncodingException {
		String title = "star wars";
		OMDbResource omdbR = new OMDbResource();
		MovieSearch omdbResults = omdbR.getMovies(title);
		List<Search> movies = omdbResults.getSearch();
		
		
		assertNotNull("The search returned null", omdbResults);
		assertNotNull("The search returned null", movies);
		assertFalse("The number of albums of " + title + " is zero", movies.size()==0);
		
		System.out.println("The search for " + title + "'s albums returned " + omdbResults.getSearch().size() + " movies.");
		
		//Print movies data
		for(Search movie : movies) {
			System.out.println("Movie title: " + movie.getTitle());
		}
	
	}

}
