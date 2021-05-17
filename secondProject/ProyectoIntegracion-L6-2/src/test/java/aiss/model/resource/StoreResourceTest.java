package aiss.model.resource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;


import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import aiss.api.resources.GameResource;
import aiss.api.resources.StoreResource;
import aiss.model.StoreGame;
import aiss.model.Store;

public class StoreResourceTest {
	static Store s1,s2,s3;
	static StoreGame o1, o2, o3;
	static StoreResource r = new StoreResource();
	
	private static GameResource games = GameResource.getInstance();
	
	@BeforeClass
	public static void setUp() throws Exception {
		List<StoreGame> gamesList;
		s1 = new Store();
		s2 = new Store();
		s3 = new Store();
		
		o1 = new StoreGame();
		o2 = new StoreGame();
		o3 = new StoreGame();
		
		s1.setName("Test name");
		s1.setLocation("Test location");
		s1.setOpenHour(LocalTime.of(11, 0));
		s1.setCloseHour(LocalTime.of(20, 30));
		s1.setPhone("999999999");
		
		s2.setName("Test name 2");
		s2.setLocation("Test Location 2");
		s2.setOpenHour(LocalTime.of(11, 0));
		s2.setCloseHour(LocalTime.of(20, 30));
		s2.setPhone("999999999");
		
		s3.setName("Test name 3");
		s3.setLocation("Test Location 3");
		s3.setOpenHour(LocalTime.of(8, 0));
		s3.setCloseHour(LocalTime.of(19, 30));
		s3.setPhone("999999999");
		
		o1.setGame(games.getAll().stream().findFirst().get());
		o1.setPrice(10.0);
		o1.setStock(1);
		
		o2.setGame(games.getAll().stream().findAny().get());
		o2.setPrice(20.0);
		o2.setStock(5);
		
		o3.setGame(games.getAll().stream().collect(Collectors.toList()).get(2));
		o3.setPrice(5.0);
		o3.setStock(4);
		
		
		gamesList = new ArrayList<StoreGame>();
		gamesList.add(o1);
		s1.setGames(gamesList);
		
		
		r.addStore(s1);
		r.addStore(s2);
		r.addStore(s3);
		
		s1.setId(r.getAll().stream().filter(x->x.getName()==s1.getName()).findFirst().get().getId());
		s2.setId(r.getAll().stream().filter(x->x.getName()==s2.getName()).findFirst().get().getId());
		s3.setId(r.getAll().stream().filter(x->x.getName()==s3.getName()).findFirst().get().getId());
		
		r.addStoreGame(s1.getId(), o1);
		r.addStoreGame(s2.getId(), o2);
		r.addStoreGame(s3.getId(), o3);
		
		o1.setId(r.getGamesFromStore(s1.getId()).stream().filter(x->x.getGame().equals(o1.getGame()) && x.getPrice()==o1.getPrice() && x.getStock()==o1.getStock()).findFirst().get().getId());
		o2.setId(r.getGamesFromStore(s2.getId()).stream().filter(x->x.getGame().equals(o2.getGame()) && x.getPrice()==o2.getPrice() && x.getStock()==o2.getStock()).findFirst().get().getId());
		o3.setId(r.getGamesFromStore(s3.getId()).stream().filter(x->x.getGame().equals(o3.getGame()) && x.getPrice()==o3.getPrice() && x.getStock()==o3.getStock()).findFirst().get().getId());
		
		/*
		UriInfo uriInfo = Mockito.mock(UriInfo.class);
		Mockito.when(uriInfo.getAbsolutePath()).thenReturn(URI.create("localhost:8080/stores"));
		*/
		
		
		
		/*
		UriInfo uriInfo2 = Mockito.mock(UriInfo.class);
		Mockito.when(uriInfo2.getAbsolutePath()).thenReturn(URI.create("localhost:8080/stores"));
		*/
	}
	
	@AfterClass
	public static void tearDown() throws Exception {
		System.out.println("tearDown");
		r.deleteStore(s1.getId());
		r.deleteStore(s2.getId());
		r.deleteStore(s3.getId());
	}
	
	
	@Test
	public void testGetAll() {
		Collection<Store> stores = r.getAll();
		
		Collection<Store> storesFiltroName = r.getAll(null, "Game", null, null, null, null, null, null);
		Collection<Store> storesFiltroLocation = r.getAll(null, null, "Sevilla", null, null, null, null, null);
		Collection<Store> storesFiltroTitleGame = r.getAll(null, null, null, "Animal Crossing", null, null, null, null);
		Collection<Store> storesFiltroOpenHour = r.getAll(null, null, null, null, LocalTime.of(8, 0), null, null, null);
		Collection<Store> storesFiltroCloseHour = r.getAll(null, null, null, null, null, LocalTime.of(20, 30), null, null);
		
		Collection<Store> storesOrderName = r.getAll("name", null, null, null, null, null, null, null);
		Collection<Store> storesOrderNameReverse = r.getAll("-name", null, null, null, null, null, null, null);
		Collection<Store> storesOrderLocation = r.getAll("location", null, null, null, null, null, null, null);
		Collection<Store> storesOrderLocationReverse = r.getAll("-location", null, null, null, null, null, null, null);
		Collection<Store> storesOrderOpenHour = r.getAll("openHour", null, null, null, null, null, null, null);
		Collection<Store> storesOrderOpenHourReverse = r.getAll("-openHour", null, null, null, null, null, null, null);
		Collection<Store> storesOrderCloseHour = r.getAll("closeHour", null, null, null, null, null, null, null);
		Collection<Store> storesOrderCloseHourReverse = r.getAll("-closeHour", null, null, null, null, null, null, null);
		
		
		Collection<Store> paginacionStores = r.getAll(null, null, null, null, null, null, 1, 2);
		
		
		assertNotNull("The collection of stores is null",stores);
		assertNotNull("The collection of stores filtered by location is null", storesFiltroLocation);
		assertNotNull("The collection of stores filtered by name is null", storesFiltroName);
		
		assertNotNull("The collection of stores sorted by name is null", storesOrderName);
		assertNotNull("The collection of stores sorted reverse by name is null", storesOrderNameReverse);
		assertNotNull("The collection of stores sorted by location is null", storesOrderLocation);
		assertNotNull("The collection of stores sorted reverse by location is null", storesOrderLocationReverse);
		assertNotNull("The collection of stores sorted by opening hour is null", storesOrderOpenHour);
		assertNotNull("The collection of stores sorted reverse by opening hour is null", storesOrderOpenHourReverse);
		assertNotNull("The collection of stores sorted by closing hour is null", storesOrderCloseHour);
		assertNotNull("The collection of stores sorted reverse by closing hour is null", storesOrderCloseHourReverse);
		
		assertNotNull("The collection of stores paginated is null", paginacionStores);
		
		System.out.println("Get All Stores");
		for(Store s:stores) {
			System.out.println(s.toString());
		}
		
		System.out.println("Get All Stores filtered by name");
		for(Store s: storesFiltroName) {
			System.out.println(s.toString());
		}
		
		System.out.println("Get All Stores filtered by location");
		for(Store s: storesFiltroLocation) {
			System.out.println(s.toString());
		}
		
		System.out.println("Get All Stores filtered by a game title");
		for(Store s : storesFiltroTitleGame) {
			System.out.println(s.toString());
		}
		
		System.out.println("Get All Stores filtered by opening hour");
		for(Store s : storesFiltroOpenHour) {
			System.out.println(s.toString());
		}
		
		System.out.println("Get All Stores filtered by a closing hour");
		for(Store s : storesFiltroCloseHour) {
			System.out.println(s.toString());
		}
		
		System.out.println("Get All Stores sorted by name");
		for(Store s: storesOrderName) {
			System.out.println(s.toString());
		}
		
		System.out.println("Get All Stores sorted reverse by name");
		for(Store s: storesOrderNameReverse) {
			System.out.println(s.toString());
		}
		
		System.out.println("Get All Stores sorted by location");
		for(Store s: storesOrderLocation) {
			System.out.println(s.toString());
		}
		
		System.out.println("Get All Stores sorted reverse by location");
		for(Store s: storesOrderLocationReverse) {
			System.out.println(s.toString());
		}
		
		System.out.println("Get All Stores sorted by opening hour");
		for(Store s: storesOrderOpenHour) {
			System.out.println(s.toString());
		}
		
		System.out.println("Get All Stores sorted reverse by opening hour");
		for(Store s: storesOrderOpenHourReverse) {
			System.out.println(s.toString());
		}
		
		System.out.println("Get All Stores sorted by closing hour");
		for(Store s: storesOrderCloseHour) {
			System.out.println(s.toString());
		}
		
		System.out.println("Get All Stores sorted reverse by closing hour");
		for(Store s: storesOrderCloseHourReverse) {
			System.out.println(s.toString());
		}
		
		System.out.println("Get All Stores with pagination");
		for(Store s: paginacionStores) {
			System.out.println(s.toString());
		}
	}
	
	@Test
	public void testGetStore() {
		Store s = r.getStore(s1.getId());
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
		Store sTest = new Store();
		sTest.setName("Test add name");
		sTest.setLocation("Test add Location");
		sTest.setOpenHour(LocalTime.of(11, 0));
		sTest.setCloseHour(LocalTime.of(20, 30));
		sTest.setPhone("999999999");
		r.addStore(sTest);
		sTest.setId(r.getAll().stream().filter(x->x.getName()==sTest.getName()).findFirst().get().getId());
		Store s = r.getStore(sTest.getId());
		assertNotNull("Error when adding the store", s);
		assertEquals("The id of the store do not match", sTest.getId(), s.getId());
		assertEquals("The name of the store do not match", sTest.getName(), s.getName());
		assertEquals("The location of the store do not match", sTest.getLocation(), s.getLocation());
		assertEquals("The Open Hour of the store do not match", sTest.getOpenHour(), s.getOpenHour());
		assertEquals("The Close Hour of the store do not match", sTest.getCloseHour(), s.getCloseHour());
		assertEquals("The phone of the store do not match", sTest.getPhone(), s.getPhone());
		assertEquals("The games of the store do not match", sTest.getGames(), s.getGames());
		
		System.out.println("Store id: " + s.getId());
		System.out.println("Store name: " + s.getName());
	}
	
	@Test
	public void testUpdateStore() {
		Store s = r.getStore(s2.getId());
		s.setName("Other test name");
		s.setLocation("Other test location");
		r.updateStore(s);
		s2 = r.getStore(s2.getId());
		assertEquals("The id of the store do not match", s2.getId(), s.getId());
		assertEquals("The name of the store do not match", s2.getName(), s.getName());
		assertEquals("The location of the store do not match", s2.getLocation(), s.getLocation());
		assertEquals("The Open Hour of the store do not match", s2.getOpenHour(), s.getOpenHour());
		assertEquals("The Close Hour of the store do not match", s2.getCloseHour(), s.getCloseHour());
		assertEquals("The phone of the store do not match", s2.getPhone(), s.getPhone());
		assertEquals("The games of the store do not match", s2.getGames(), s.getGames());
		
		System.out.println("Store id: " + s.getId());
		System.out.println("Store name: " + s.getName());
		
		
		
	}
	
	@Test
	public void testDeleteStore() {
		Store sTest = new Store();
		sTest.setName("Test name");
		sTest.setLocation("Test Location");
		sTest.setOpenHour(LocalTime.of(11, 0));
		sTest.setCloseHour(LocalTime.of(20, 30));
		sTest.setPhone("999999999");
		r.addStore(sTest);
		
		Store sTest2 = r.getAll().stream().filter(x->x.getName()==sTest.getName() && x.getLocation()==sTest.getLocation() && x.getId()!=s1.getId() && x.getId()!=s2.getId() && x.getId()!=s3.getId()).findFirst().get();
		
		assertNotNull("The store was not added correctly", sTest2);
		Boolean deleted = r.deleteStore(sTest2.getId()).equals(null);
		assertFalse("The store is not deleted",deleted);
		System.out.println("Success deleting store");
		
	}
	
	@Test
	public void testGetCheapest() {
		Collection<Store> stores = r.getCheapestGamesInArea("Animal Crossing", "Sevilla");
		assertNotNull("The store collection is null", stores);
		System.out.println("Showing the stores that sell the specified game the cheapest in your area");
		for(Store store : stores) {
			System.out.println(store);
		}
	}
}
