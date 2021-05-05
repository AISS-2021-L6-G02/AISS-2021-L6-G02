package aiss.model.resource;

import static org.junit.Assert.*;

import java.security.cert.CollectionCertStoreParameters;
import java.util.Collection;

import javax.jdo.annotations.Order;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import aiss.api.resources.PlatformResource;
import aiss.model.Developer;
import aiss.model.Platform;

public class PlatformResourceTest {
	static Platform p1, p2, p3, p4;

	static PlatformResource r = new PlatformResource();

	@BeforeClass
	public static void setUp() throws Exception {

		p1 = new Platform("Test name1");
		p2 = new Platform("Test name2");
		p3 = new Platform("Test name3");
		p4 = new Platform("Test name4");
		r.addPlatform(p1);
		r.addPlatform(p2);
		r.addPlatform(p3);
		r.addPlatform(p4);
	}

	@AfterClass
	public static void tearDown() throws Exception {
		System.out.println("tearDown");
		r.removePlatform(p1.getId());
		r.removePlatform(p2.getId());
		r.removePlatform(p3.getId());
		r.removePlatform(p4.getId());

	}

	@Test
	public void testGetAll() {

		// basic getAll
		Collection<Platform> platforms = r.getAll(null, null, null, null);
		Collection<Platform> ordered = r.getAll("name", null, null, null);
		Collection<Platform> reversedOrdered = r.getAll("-name", null, null, null);
		Collection<Platform> pagination = r.getAll(null, null, 2, 2);
		assertNotNull("The collection of platforms is null", platforms);

		// Show result
		System.out.println("========================================");
		System.out.println("Test getAllPlatforms basic:");
		System.out.println("========================================");

		int i = 1;
		for (Platform pl : platforms) {
			System.out.println("Platforms " + i++ + " : " + pl.getName() + " (ID=" + pl.getId() + ")");
			assertNotNull("The id of the platform is null" + p1.getId());
			assertNotNull("The name of the platform is null" + p1.getName());

		}
		// Show result
		System.out.println("========================================");
		System.out.println("Test getAllPlatforms order=name:");
		System.out.println("========================================");
		assertNotNull("The collection of platforms ordered by name is null", ordered);

		i = 1;
		for (Platform p2 : ordered) {
			System.out.println("Platforms " + i++ + " : " + p2.getName() + " (ID=" + p2.getId() + ")");
			assertNotNull("The id of the platform is null" + p2.getId());
			assertNotNull("The name of the platform is null" + p2.getName());

		}
		System.out.println("========================================");
		System.out.println("Test getAllPlatforms order=-name:");
		System.out.println("========================================");
		assertNotNull("The collection of ordered by -name platforms is null", ordered);

		i = 1;
		for (Platform p2 : reversedOrdered) {
			System.out.println("Platforms " + i++ + " : " + p2.getName() + " (ID=" + p2.getId() + ")");
			assertNotNull("The id of the platform is null" + p2.getId());
			assertNotNull("The name of the platform is null" + p2.getName());

		}

		System.out.println("========================================");
		System.out.println("Test getAllPlatforms with pagination");
		System.out.println("========================================");
		assertNotNull("The collection with pagination is null", ordered);

		i = 1;
		for (Platform p2 : pagination) {
			System.out.println("Platforms " + i++ + " : " + p2.getName() + " (ID=" + p2.getId() + ")");
			assertNotNull("The id of the platform is null" + p2.getId());
			assertNotNull("The name of the platform is null" + p2.getName());

		}

	}

	@Test
	public void testGetPlatform() {
		Platform p = r.get(p1.getId());
		System.out.println("========================================");
		System.out.println("Test get one Platform:");
		System.out.println("========================================");
		assertEquals("The id of the platform do not match", p1.getId(), p.getId());
		assertEquals("The name of the platform do not match", p1.getName(), p.getName());

		// Show result
		System.out.println("Platform id: " + p.getId());
		System.out.println("Platform name: " + p.getName());

	}

	@Test
	public void testAddPlatform() {
		System.out.println("========================================");
		System.out.println("Test add Platform:");
		System.out.println("========================================");
		String name = "Add platform test title";
		r.addPlatform(new Platform(name)).getEntity();
		Platform p2 = new Platform();
		p2 = r.getAll(null, null, null, null).stream().filter(x -> x.getName().equals(name)).findFirst().get();
		System.out.println("Platform added --> " + p2);
		assertNotNull("Error when adding the playlist", p2);
		assertEquals("The platform's name has not been setted correctly", name, p2.getName());

	}
	@Test
	public void testUpdatePlatform() {
		System.out.println("========================================");
		System.out.println("Test update Platform:");
		System.out.println("========================================");
		String name = "Update platform name test";
		
		Platform p = new Platform();
		p.setName(name);
		p.setId(p1.getId());
		r.updatePlatform(p);		
		
		assertEquals("The platform's name has not been updated correctly", name, p.getName());
		System.out.println("Updated platform: "+r.get(p1.getId()));
	}
	@Test
	public void testDeletePlatform() {
		System.out.println("========================================");
		System.out.println("Test delete Platform:");
		System.out.println("========================================");
		r.removePlatform(p1.getId());
		assertNull("Error when deleting the platform", r.get(p1.getId()));
		if (r.get(p1.getId())==null)
				System.out.println("Success deleting platform");

	}

}
