package aiss.model.resource;

import static org.junit.Assert.assertNotNull;

import java.util.Collection;

import org.junit.Test;

import aiss.api.resources.StoreResource;
import aiss.model.Store;

public class StoreResourceTest {
	static Store s1,s2,s3;
	static StoreResource r = new StoreResource();
	
	@Test
	public void testGetAll() {
		Collection<Store> stores = r.getAll(null, null, null, null, null);
		assertNotNull("The collection of stores is null",stores);
		System.out.println("Get All Stores");
		for(Store s:stores) {
			System.out.println(s);
		}
	}

}
