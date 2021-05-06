package aiss.model.resource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.net.URI;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;

import javax.ws.rs.core.UriInfo;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import aiss.api.resources.GameResource;
import aiss.api.resources.StoreResource;
import aiss.model.Game;
import aiss.model.ObjetoStore;
import aiss.model.Store;

public class StoreResourceTest {
	static Store s1,s2,s3;
	static ObjetoStore o;
	static StoreResource r = new StoreResource();
	
	private static GameResource games = GameResource.getInstance();
	
	@BeforeClass
	public static void setUp() throws Exception {
		
		s1 = new Store();
		o = new ObjetoStore();
		s1.setName("Test name");
		s1.setLocation("Test location");
		s1.setOpenHour(LocalTime.of(11, 0));
		s1.setCloseHour(LocalTime.of(20, 30));
		s1.setPhone("999999999");
		o.setGame(games.getAll().stream().findFirst().get());
		o.setPrice(10.0);
		o.setStock(1);
		/*
		UriInfo uriInfo = Mockito.mock(UriInfo.class);
		Mockito.when(uriInfo.getAbsolutePath()).thenReturn(URI.create("localhost:8080/stores"));
		*/
		r.addStore(s1);
		r.addObject(s1.getId(), o);
		
		s2 = new Store();
		s2.setName("Test name");
		s2.setLocation("Test Location");
		s2.setOpenHour(LocalTime.of(11, 0));
		s2.setCloseHour(LocalTime.of(20, 30));
		s2.setPhone("999999999");
		/*
		UriInfo uriInfo2 = Mockito.mock(UriInfo.class);
		Mockito.when(uriInfo2.getAbsolutePath()).thenReturn(URI.create("localhost:8080/stores"));
		*/
		r.addStore(s2);
		
		
	}
	
	@AfterClass
	public static void tearDown() throws Exception {
		System.out.println("tearDown");
		r.deleteStore(s1.getId());
		r.deleteStore(s2.getId());
	}
	
	
	@Test
	public void testGetAll() {
		Collection<Store> stores = r.getAll(null, null, null, null, null);
		Collection<Store> storesFiltroLocation = r.getAll("Sevilla", null, null, null, null);
		Collection<Store> storesFiltroName = r.getAll("GAME", null, null, null, null);
		
		Collection<Store> storesFiltroNoGames = r.getAll(null, true, null, null, null);
		Collection<Store> storesFiltroHaveGames = r.getAll(null, false, null, null, null);
		
		
		Collection<Store> storesOrderName = r.getAll(null, null, "name", null, null);
		Collection<Store> storesOrderNameReversed = r.getAll(null, null, "-name", null, null);
		Collection<Store> storesOrderLocation = r.getAll(null, null, "location", null, null);
		Collection<Store> storesOrderLocationReversed = r.getAll(null, null, "-location", null, null);
		Collection<Store> storesOrderGames = r.getAll(null, null, "games", null, null);
		Collection<Store> storesOrderGamesReversed = r.getAll(null, null, "-games", null, null);
		
		Collection<Store> paginacionStores = r.getAll(null, null, null, 1, 2);
		
		
		assertNotNull("The collection of stores is null",stores);
		assertNotNull("The collection of stores filtered by location is null", storesFiltroLocation);
		assertNotNull("The collection of stores filtered by name is null", storesFiltroName);
		
		assertNotNull("Listing all stores sorted by name:", storesOrderName);
		assertNotNull("Listing all stores sorted reverse by name:", storesOrderNameReversed);
		assertNotNull("Listing all stores sorted by location:", storesOrderLocation);
		assertNotNull("Listing all stores sorted reverse by location:", storesOrderLocationReversed);
		assertNotNull("Listing all stores sorted by number of games:", storesOrderGames);
		assertNotNull("Listing all stores sorted reverse by number of games:", storesOrderGamesReversed);
		
		System.out.println("Get All Stores");
		for(Store s:stores) {
			System.out.println(s);
		}
		System.out.println("Get All Stores filtered by location");
		for(Store s: storesFiltroLocation) {
			System.out.println(s);
		}
		System.out.println("Get All Stores filtered by name");
		for(Store s: storesFiltroName) {
			System.out.println(s);
		}
	}
	
	@Test
	public void testGetStore() {
		Store s = r.get(s1.getId());
		System.out.println("========================================");
		System.out.println("Test get one Store:");
		System.out.println("========================================");
		assertEquals("The id of the store do not match", s1.getId(), s.getId());
		assertEquals("The name of the store do not match", s1.getName(), s.getName());
		assertEquals("The location of the store do not match", s1.getLocation(), s.getLocation());
		assertEquals("The Open Hour of the store do not match", s1.getOpenHour(), s.getOpenHour());
		assertEquals("The Close Hour of the store do not match", s1.getCloseHour(), s.getCloseHour());
		assertEquals("The phone of the store do not match", s1.getPhone(), s.getPhone());
		assertEquals("The games of the store do not match", s1.getGames(), s.getGames());
		
		System.out.println("Store id: " + s.getId());
		System.out.println("Store name: " + s.getName());
	}
	
	@Test 
	public void testAddStore() {
		s3 = new Store();
		s3.setName("Test name");
		s3.setLocation("Test Location");
		s3.setOpenHour(LocalTime.of(11, 0));
		s3.setCloseHour(LocalTime.of(20, 30));
		s3.setPhone("999999999");
		r.addStore(s3);
		Store s = r.get(s3.getId());
		assertNotNull("Error when adding the store", s);
		assertEquals("The id of the store do not match", s3.getId(), s.getId());
		assertEquals("The name of the store do not match", s3.getName(), s.getName());
		assertEquals("The location of the store do not match", s3.getLocation(), s.getLocation());
		assertEquals("The Open Hour of the store do not match", s3.getOpenHour(), s.getOpenHour());
		assertEquals("The Close Hour of the store do not match", s3.getCloseHour(), s.getCloseHour());
		assertEquals("The phone of the store do not match", s3.getPhone(), s.getPhone());
		assertEquals("The games of the store do not match", s3.getGames(), s.getGames());
	}
	
	@Test
	public void testUpdateStore() {
		
	}
	
	@Test
	public void testDeleteStore() {
		
	}

}
