package aiss.model.resource;


import static org.junit.Assert.*;

import java.util.Collection;


import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;


import aiss.api.resources.PlatformResource;
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
		System.out.println("test get all platforms");
		//basic getAll
		Collection<Platform> platforms = r.getAll(null, null, null, null);
		assertNotNull("The collection of platforms is null", platforms);
		
		// Show result
		System.out.println("getAllPlatforms basic:");
		int i=1;
		for (Platform pl : platforms) {
			System.out.println("Platforms " + i++ + " : " + pl.getName() + " (ID=" + pl.getId() + ")");
			assertNotNull("The id of the platform is null"+p1.getId());
			assertNotNull("The name of the platform is null"+p1.getName());

		}
		
	}

	@Test
	public void testGetPlatform() {
		Platform p = r.get(p1.getId());
		
		assertEquals("The id of the platform do not match", p1.getId(), p.getId());
		assertEquals("The name of the platform do not match", p1.getName(), p.getName());
		
		// Show result
		System.out.println("Platform id: " +  p.getId());
		System.out.println("Platform name: " +  p.getName());

	}

	@Test
	public void testAddPlatform() {
		String name = "Add platform test title";
		r.addPlatform(new Platform(name)).getEntity();
		Platform p2 = r.getAll(null, null, null, null).stream().findFirst().get();
		assertNotNull("Error when adding the playlist", p2);
		assertEquals("The platform's name has not been setted correctly", name, p2.getName());
		
		
		

	}
//
//	@Test
//	public void testUpdatePlaylist() {
//		String playlistName = "Updated playlist name";
//
//		// Update playlist
//		playlist.setName(playlistName);
//
//		boolean success = plr.updatePlaylist(playlist);
//		
//		assertTrue("Error when updating the playlist", success);
//		
//		Playlist pl  = plr.getPlaylist(playlist.getId());
//		assertEquals("The playlist's name has not been updated correctly", playlistName, pl.getName());
//
//	}
//
//	@Test
//	public void testDeletePlaylist() {
//		boolean success = plr.deletePlaylist(playlist2.getId());
//		assertTrue("Error when deleting the playlist", success);
//		
//		Playlist pl = plr.getPlaylist(playlist2.getId());
//		assertNull("The playlist has not been deleted correctly", pl);
//	}
//
//	@Test
//	public void testAddSong() {
//		if(song!=null) {
//			boolean success = plr.addSong(playlist3.getId(), song.getId());
//			assertTrue("Error when adding the song", success);
//		}
//	}
//
//	@Test
//	public void testRemoveSong() {
//		//TODO
//	}

}
