package aiss.model.resource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.time.LocalTime;
import java.util.Collection;
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
		Collection<Store> stores = r.getAll(null, null, null, null, null, null, null, null);
		
		Collection<Store> storesFiltroName = r.getAll(null, null, null, null, null, null, null, null);
		Collection<Store> storesFiltroLocation = r.getAll(null, null, null, null, null, null, null, null);
		Collection<Store> storesFiltroTitleGame = r.getAll(null, null, null, null, null, null, null, null);
		Collection<Store> storesFiltroOpenHour = r.getAll(null, null, null, null, null, null, null, null);
		Collection<Store> storesFiltroCloseHour = r.getAll(null, null, null, null, null, null, null, null);
		
		Collection<Store> storesOrderName = r.getAll(null, null, null, null, null, null, null, null);
		Collection<Store> storesOrderNameReverse = r.getAll(null, null, null, null, null, null, null, null);
		Collection<Store> storesOrderLocation = r.getAll(null, null, null, null, null, null, null, null);
		Collection<Store> storesOrderLocationReverse = r.getAll(null, null, null, null, null, null, null, null);
		Collection<Store> storesOrderOpenHour = r.getAll(null, null, null, null, null, null, null, null);
		Collection<Store> storesOrderOpenHourReverse = r.getAll(null, null, null, null, null, null, null, null);
		Collection<Store> storesOrderCloseHour = r.getAll(null, null, null, null, null, null, null, null);
		Collection<Store> storesOrderCloseHourReverse = r.getAll(null, null, null, null, null, null, null, null);
		
		
		Collection<Store> paginacionStores = r.getAll(null, null, null, null, null, null, 1, 2);
		
		
		assertNotNull("The collection of stores is null",stores);
		assertNotNull("The collection of stores filtered by location is null", storesFiltroLocation);
		assertNotNull("The collection of stores filtered by name is null", storesFiltroName);
		
		assertNotNull("The collection of stores sorted by name is null", storesOrderName);
		assertNotNull("The collection of stores sorted reverse by name is null", storesOrderNameReversed);
		assertNotNull("The collection of stores sorted by location is null", storesOrderLocation);
		assertNotNull("The collection of stores sorted reverse by location is null", storesOrderLocationReversed);
		assertNotNull("The collection of stores sorted by number of games is null", storesOrderGames);
		assertNotNull("The collection of stores sorted reverse by number of games is null", storesOrderGamesReversed);
		assertNotNull("The collection of stores paginated is null", paginacionStores);
		
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
		System.out.println("Get All Stores filtered by having games");
		for(Store s: storesFiltroHaveGames) {
			System.out.println(s);
		}
		System.out.println("Get All Stores filtered by having no games");
		for(Store s: storesFiltroNoGames) {
			System.out.println(s);
		}
		System.out.println("Get All Stores sorted by name");
		for(Store s: storesOrderName) {
			System.out.println(s);
		}
		System.out.println("Get All Stores sorted reverse by name");
		for(Store s: storesOrderNameReversed) {
			System.out.println(s);
		}
		System.out.println("Get All Stores sorted by location");
		for(Store s: storesOrderLocation) {
			System.out.println(s);
		}
		System.out.println("Get All Stores sorted reverse by location");
		for(Store s: storesOrderLocationReversed) {
			System.out.println(s);
		}
		System.out.println("Get All Stores sorted by number of games");
		for(Store s: storesOrderGames) {
			System.out.println(s);
		}
		System.out.println("Get All Stores sorted reverse by number of games");
		for(Store s: storesOrderGamesReversed) {
			System.out.println(s);
		}
		System.out.println("Get All Stores paginated");
		for(Store s: paginacionStores) {
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
		Store sTest = new Store();
		sTest.setName("Test add name");
		sTest.setLocation("Test add Location");
		sTest.setOpenHour(LocalTime.of(11, 0));
		sTest.setCloseHour(LocalTime.of(20, 30));
		sTest.setPhone("999999999");
		r.addStore(sTest);
		sTest.setId(r.getAll().stream().filter(x->x.getName()==sTest.getName()).findFirst().get().getId());
		Store s = r.get(sTest.getId());
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
		Store s = r.get(s2.getId());
		s.setName("Other test name");
		s.setLocation("Other test location");
		r.updateStore(s);
		s2 = r.get(s2.getId());
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
	public void testGetItems() {
		Collection<StoreGame> items = r.getAllObjects(s1.getId(), null, null, null, null, null, null);
		assertNotNull("The collection of games in store with id "+s1.getId()+" is null", items);
		System.out.println("Get All items on Store:");
		for(StoreGame i:items) {
			System.out.println(i);
		}
	}
	
	@Test
	public void testGetItem() {
		StoreGame item = r.getObject(s1.getId(), s1.getGames().get(0).getId());
		assertEquals("The Id of the item given does not match", item.getId(),s1.getGames().get(0).getId());
		assertEquals("The game of the item given does not match", item.getGame(),s1.getGames().get(0).getGame());
		assertEquals("The price of the item given does not match", item.getPrice(),s1.getGames().get(0).getPrice());
		assertEquals("The stock of the item given does not match", item.getStock(),s1.getGames().get(0).getStock());
		
		System.out.println("Item Store id: " + item.getId());
		System.out.println("Store id: " + s1.getId());
		System.out.println("Store name: " + s1.getName());
	}
	
	@Test
	public void testGetCheapest() {
		Collection<StoreGame> games = r.getCheapestGamesInArea("Sevilla",50., null);
		assertNotNull("The hashmap is null", games);
		System.out.println("Showing the stores with the cheapest games in your area");
		for(StoreGame store:games) {
			System.out.println(store);
		}
	}
	
	@Test
	public void testAddItem() {
		
		
		StoreGame oTest = new StoreGame();
		oTest.setGame(games.getAll().stream().findFirst().get());
		oTest.setPrice(23.75);
		oTest.setStock(128);
		r.addObject(s2.getId(), oTest);
		
		
		
		
		Collection<StoreGame> items = r.getAllObjects(s2.getId(), null, null, null, null, null, null);
		assertNotNull("The collection of items is null", items);
		assertFalse("The collection of items is empty", items.isEmpty());
		
		System.out.println("Item Store id: " + oTest.getId());
		System.out.println("Store id: " + s2.getId());
		System.out.println("Store name: " + s2.getName());
	}
	
	@Test
	public void testDeleteItem() {
		Store sTest = new Store();
		
		sTest.setName(s3.getName());
		sTest.setLocation(s3.getLocation());
		sTest.setOpenHour(s3.getOpenHour());
		sTest.setCloseHour(s3.getCloseHour());
		sTest.setGames(s3.getGames());
		sTest.setPhone(s3.getPhone());
		
		r.addStore(sTest);
		sTest.setId(r.getAll().stream().filter(x->x.getName()==sTest.getName()&&x.getId()!=s3.getId()).findFirst().get().getId());
		
		StoreGame oTest = new StoreGame();
		StoreGame ref = sTest.getGames().stream().findAny().get();
		
		oTest.setGame(ref.getGame());
		oTest.setPrice(ref.getPrice());
		oTest.setStock(ref.getStock());
		
		r.addObject(sTest.getId(), oTest);
		
		oTest.setId(r.getAllObjects(sTest.getId(), null, null, null, null, null, null).stream().filter(x->x.getGame()==oTest.getGame()&&x.getStock()==oTest.getStock()&&x.getPrice()==oTest.getPrice()).findFirst().get().getId());
		
		Boolean deleted = r.deleteObject(sTest.getId(), oTest.getId()).equals(null);
		assertFalse("The Item was not deleted ",deleted);
		System.out.println("Success deleting item");
	}
	
	@Test
	public void testUpdateItem() {
		StoreGame oTest = s1.getGames().get(0);
		
		oTest.setStock(0);
		oTest.setPrice(99.99);
		r.updateObject(s1.getId(), oTest);
		
		Store sTest2 = r.get(s1.getId());
		StoreGame item = sTest2.getGames().stream().filter(x->x.getGame().equals(oTest.getGame())&&x.getPrice()==oTest.getPrice()&&x.getStock()==oTest.getStock()).findFirst().get();
		assertEquals("The game of the item given does not match", item.getGame(),oTest.getGame());
		assertEquals("The price of the item given does not match", item.getPrice(),oTest.getPrice());
		assertEquals("The stock of the item given does not match", item.getStock(),oTest.getStock());
		
		System.out.println("Item Store id: " + item.getId());
		System.out.println("Store id: " + sTest2.getId());
		System.out.println("Store name: " + sTest2.getName());
	}
	
	

}
