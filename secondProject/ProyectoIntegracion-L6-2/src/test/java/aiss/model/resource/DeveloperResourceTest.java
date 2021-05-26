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
		
		Collection<Developer> devsSortedName = dr.getAll("name", null, null, null, null, null);
		Collection<Developer> devsSortedNameReverse = dr.getAll("-name", null, null, null, null, null);
		Collection<Developer> devsSortedYear = dr.getAll("year", null, null, null, null, null);
		Collection<Developer> devsSortedYearReverse = dr.getAll("-year", null, null, null, null, null);
		Collection<Developer> devsSortedCountry = dr.getAll("country", null, null, null, null, null);
		Collection<Developer> devsSortedCountryReverse = dr.getAll("-country", null, null, null, null, null);
		
		Collection<Developer> devsFiltroName = dr.getAll(null, "Nintendo", null, null, null, null);
		Collection<Developer> devsFiltroCountry = dr.getAll(null, null, "Japan", null, null, null);
		Collection<Developer> devsFiltroYear = dr.getAll(null, null, null, 1889, null, null);
		
		Collection<Developer> devsPaginacion = dr.getAll(null, null, null, null, 5, 8);
		
		
		assertNotNull("The collection of devs is null", devs);
		System.out.println("Listing all developers:");
		for(Developer dev : devs) {
			System.out.println(dev.toString());
		}
		
		
		assertNotNull("The collection of sorted devs by name is null", devsSortedName);
		System.out.println("Listing all developers filtered:");
		for(Developer dev : devsSortedName) {
			System.out.println(dev.toString());
		}
		assertNotNull("The collection of sorted devs by name reversed is null", devsSortedNameReverse);
		System.out.println("Listing all developers filtered:");
		for(Developer dev : devsSortedNameReverse) {
			System.out.println(dev.toString());
		}
		
		assertNotNull("The collection of sorted devs by country is null", devsSortedCountry);
		System.out.println("Listing all developers filtered:");
		for(Developer dev : devsSortedCountry) {
			System.out.println(dev.toString());
		}
		assertNotNull("The collection of sorted devs by country reversed is null", devsSortedCountryReverse);
		System.out.println("Listing all developers filtered:");
		for(Developer dev : devsSortedCountryReverse) {
			System.out.println(dev.toString());
		}
		
		assertNotNull("The collection of sorted devs by year is null", devsSortedYear);
		System.out.println("Listing all developers filtered:");
		for(Developer dev : devsSortedYear) {
			System.out.println(dev.toString());
		}
		assertNotNull("The collection of sorted devs by year reversed is null", devsSortedYearReverse);
		System.out.println("Listing all developers filtered:");
		for(Developer dev : devsSortedYearReverse) {
			System.out.println(dev.toString());
		}
		
		
		assertNotNull("The collection of filtered devs by name is null", devsFiltroName);
		System.out.println("Listing all developers filtered:");
		for(Developer dev : devsFiltroName) {
			System.out.println(dev.toString());
		}
		
		assertNotNull("The collection of filtered devs by country is null", devsFiltroCountry);
		System.out.println("Listing all developers filtered:");
		for(Developer dev : devsFiltroCountry) {
			System.out.println(dev.toString());
		}
		
		assertNotNull("The collection of filtered devs by year is null", devsFiltroYear);
		System.out.println("Listing all developers filtered:");
		for(Developer dev : devsFiltroYear) {
			System.out.println(dev.toString());
		}
		
		assertNotNull("The collection of devs with pagination is null", devsPaginacion);
		System.out.println("Listing all developers with pagination:");
		for(Developer dev : devsPaginacion) {
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
		boolean deleted = dr.deleteDeveloper(d1.getId()).equals(null);
		assertFalse("Error when deleting the developer", deleted);
		dr.addDeveloper(d1);
		String newId = dr.getAll(null, d1.getName(), d1.getCountry(), d1.getYear(), null, null).stream().findFirst().get().getId();
		d1.setId(newId);
		
	}
	
}
