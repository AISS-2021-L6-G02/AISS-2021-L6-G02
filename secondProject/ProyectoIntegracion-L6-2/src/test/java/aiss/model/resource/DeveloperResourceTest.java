package aiss.model.resource;
import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import aiss.api.resources.DeveloperResource;
import aiss.model.Developer;

public class DeveloperResourceTest {
	static Developer d1, d2, d3;
	static DeveloperResource dr = DeveloperResource.getInstance();
	
	@BeforeClass
	public static void setUp() throws Exception {
		Developer d;
		
		Collection<Developer> test;
		
		d = new Developer();
		d.setName("Test name");
		d.setCountry("Test country");
		d.setYear(2000);
		dr.addDeveloper(d);
		
		test = dr.getAll("", d.getName(), d.getCountry(), d.getYear(), null, null);
		d1 = test.stream().findFirst().get();
		
		d = new Developer();
		d.setName("Test name2");
		d.setCountry("Test country2");
		d.setYear(2001);
		dr.addDeveloper(d);
		
		test = dr.getAll("", d.getName(), d.getCountry(), d.getYear(), null, null);
		d2 = test.stream().findFirst().get();
		
		d = new Developer();
		d.setName("Test name3");
		d.setCountry("Test country3");
		d.setYear(2002);
		dr.addDeveloper(d);
		
		test = dr.getAll("", d.getName(), d.getCountry(), d.getYear(), null, null);
		d3 = test.stream().findFirst().get();
	}
	@AfterClass
	public static void tearDown() {
		dr.deleteDeveloper(d1.getId());
		dr.deleteDeveloper(d2.getId());
		dr.deleteDeveloper(d3.getId());
	}
	
	@Test
	public void testGetAll() {
		Collection<Developer> devs = dr.getAll();
		Collection<Developer> devsPaginacion = dr.getAll(null, null, null, null, 5, 8);
		Collection<Developer> devsFiltro = dr.getAll(null, null, "Japan", null, null, null);
		
		assertNotNull("The collection of devs is null", devs);
		System.out.println("Listing all developers:");
		for(Developer dev : devs) {
			System.out.println(dev.toString());
		}
		
		
		assertNotNull("The collection of devs with pagination is null", devsPaginacion);
		System.out.println("Listing all developers with pagination:");
		for(Developer dev : devsPaginacion) {
			System.out.println(dev.toString());
		}
		
		
		assertNotNull("The collection of filtered devs is null", devsFiltro);
		System.out.println("Listing all developers filtered:");
		for(Developer dev : devsFiltro) {
			System.out.println(dev.toString());
		}
	}
	
	@Test
	public void testGetDeveloper() {
		Developer d = dr.get(d1.getId());
		
		assertEquals("The id of the developers do not match", d1.getId(), d.getId());
		assertEquals("The name of the developers do not match", d1.getName(), d.getName());
		assertEquals("The foundation year of the developers do not match", d1.getYear(), d.getYear());
		assertEquals("The country of the developers do not match", d1.getCountry(), d.getCountry());
		System.out.println("Developer id: " + d.getId());
		System.out.println("Developer Name: " + d.getId());
	}
	@Test
	public void testAddDeveloper() {
		Developer d = new Developer();
		d.setName("Add dev test name");
		d.setCountry("Add dev test country");
		d.setYear(2020);
		
		dr.addDeveloper(d);
		
		Developer dTest = dr.getAll("", d.getName(), d.getCountry(), d.getYear(), null, null).stream().findFirst().get();
		
		assertNotNull("Error when adding the developer", dTest);
		assertEquals("The developer's name has not been setted correctly", d.getName(), dTest.getName());
		assertEquals("The developer's year has not been setted correctly", d.getYear(), dTest.getYear());
		assertEquals("The developer's country has not been setted correctly", d.getCountry(), dTest.getCountry());
		
	}
	@Test
	public void testUpdateDeveloper() {
		String devName = "Update dev name test";
		String devCountry = "Update dev country test";
		Integer devYear = 1900;
		
		Developer d = d1;
		
		d.setName(devName);
		d.setCountry(devCountry);
		d.setYear(devYear);
		
		dr.updateDeveloper(d);
//		Developer d = d1;
		
		assertEquals("The developer's name has not been updated correctly", devName, d.getName());
		assertEquals("The developer's country has not been updated correctly", devCountry, d.getCountry());
		assertEquals("The developer's year has not been updated correctly", devYear, d.getYear());
		
	}
	@Test
	public void testDeleteDeveloper() {
		dr.deleteDeveloper(d1.getId());
		Developer dT = dr.get(d1.getId());
		assertNull("Error when deleting the developer", dT);
		if(dT==null) {
			dr.addDeveloper(d1);
			String newId = dr.getAll(null, d1.getName(), d1.getCountry(), d1.getYear(), null, null).stream().findFirst().get().getId();
			d1.setId(newId);
		}
	}
	
}
