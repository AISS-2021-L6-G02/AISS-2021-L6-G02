
package aiss.model.resource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import aiss.api.resources.SerieResource;
import aiss.api.resources.UserResource;
import aiss.model.Serie;
import aiss.model.User;
import aiss.model.WatchedSeries;

public class TestUserResource {
	public static UserResource sr = new UserResource();
	public static SerieResource serieR = new SerieResource();
	
	
	@Test
	public Collection<User> testGetAllSeries() {
		Collection<User> test = sr.getAllUsers();
		
		assertNotNull("The collection of users is null", test);
		System.out.println("===========\ntestGetAllUsers\n=========");

		// Show result
		System.out.println("Listing all users:");
		
		for (User s : test) {
			System.out.println(s);
		}
		return test;
	}

	@Test
	public void testGetOneUser() {
		System.out.println("============================");
		System.out.println("TEST GET ONE USERS BY ID");
		System.out.println("============================");
		User user = sr.getAllUsers().stream().collect(Collectors.toList()).get(0);
		String id = user.getId();
		User userGetOne = sr.getOneUser(id);
		assertNotNull("The user is null", userGetOne);
		assertEquals("The user doesnt match with the obtained by getAllUsers()", user.getId(),
				userGetOne.getId());
		System.out.println(userGetOne);

	}
	@Test
	public void testUpdateUser() {
		System.out.println("============================");
		System.out.println("TEST UPDATE USER");
		System.out.println("============================");
		User s = new User();
		User old = sr.getAllUsers().stream().collect(Collectors.toList()).get(0);
		s.setUsername(old.getUsername()+" test");
		s.setFirstName(old.getFirstName()+" test firstName");
		s.setLastName(old.getLastName()+" test lastname");
		List<WatchedSeries> aux = old.getWatchedSeries();
		Serie nueva = new Serie();
		nueva.setGenre("Horror");
		nueva.setNumEpisodes("20");
		nueva.setStartYear("2020");
		nueva.setTitle("Resident evil 2.0");
		Serie creada = serieR.addSerie(nueva);
		
		aux.add(new WatchedSeries(creada.getId(), creada.getTitle(), creada.getGenre(), creada.getNumEpisodes(), creada.getStartYear()));
		s.setWatchedSeries(aux);
		boolean result = sr.updateUser(s);
		User updated = new User();
		for(User sx: sr.getAllUsers()) {
			if(sx.getId().equals(s.getId())) {
				updated = sx;
				break;
			}
		}
		assertNotEquals("The username of the user is equals", old.getUsername(), updated.getUsername());
		assertNotEquals("The firstName of the user is equals", old.getFirstName(), updated.getFirstName());
		assertNotEquals("The lastName of the user is equals", old.getLastName(), updated.getLastName());
		assertNotEquals("The watchedSeries of the user is equals", old.getWatchedSeries(), updated.getWatchedSeries());



		System.out.println("User updated--> "+updated);

	}
	@Test
	public void testDeleteUser() {
		System.out.println("============================");
		System.out.println("TEST DELETE USER");
		System.out.println("============================");
		User old = sr.getAllUsers().stream().collect(Collectors.toList()).get(0);
		
		boolean result = sr.deleteUser(old.getId());
		sr.addUser(old);
		assertFalse("The user hasn't been deleted correctly", !result);



		System.out.println("user deleted--> "+result);
		

	}


}
