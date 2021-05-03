package aiss.model.resource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.net.URI;
import java.util.Collection;

import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import org.jboss.resteasy.specimpl.UriInfoImpl;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import aiss.api.resources.PlatformResource;
import aiss.api.resources.TestResource;
import aiss.model.Platform;



public class PlatformResourceTest {
	static Platform p1, p2, p3, p4;

	static PlatformResource r = new PlatformResource();
	static UriInfo uriInfo = null;
	
	@BeforeClass
	public static void setUp() throws Exception {
		UriInfo uriInfo = Mockito.mock(UriInfo.class);
	    Mockito.when(uriInfo.getAbsolutePath())
	        .thenReturn(URI.create("http://localhost:8080/tests"));
	    TestResource resource = new TestResource();
	    uriInfo= resource.doSomthing(uriInfo);
	   
		p1 = new Platform("Test name1");
		p2 = new Platform("Test name2");
		p3 = new Platform("Test name3");
		p4 = new Platform("Test name4");
		r.addPlatform(uriInfo, p1);
		r.addPlatform(uriInfo, p2);
		r.addPlatform(uriInfo, p3);
		r.addPlatform(uriInfo, p4);
	}

	@AfterClass
	public static void tearDown() throws Exception {
		r.removePlatform(p1.getId());
		r.removePlatform(p2.getId());
		r.removePlatform(p3.getId());
		r.removePlatform(p4.getId());
		
	}

	@Test
	public void testGetAll() {
		Collection<Platform> platforms = r.getAll(null, null, null, null);
		
		assertNotNull("The collection of platforms is null", platforms);
		
		// Show result
		System.out.println("Listing all platforms:");
		int i=1;
		for (Platform pl : platforms) {
			System.out.println("Platforms " + i++ + " : " + pl.getName() + " (ID=" + pl.getId() + ")");
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
		String platformName = "Add platform test title";
		r.addPlatform(uriInfo, p)
		assertNotNull("Error when adding the playlist", playlist4);
		assertEquals("The playlist's name has not been setted correctly", playlistName, playlist4.getName());
		assertEquals("The playlist's description has not been setted correctly", playlistDescription, playlist4.getDescription());
	}

	@Test
	public void testUpdatePlaylist() {
		String playlistName = "Updated playlist name";

		// Update playlist
		playlist.setName(playlistName);

		boolean success = plr.updatePlaylist(playlist);
		
		assertTrue("Error when updating the playlist", success);
		
		Playlist pl  = plr.getPlaylist(playlist.getId());
		assertEquals("The playlist's name has not been updated correctly", playlistName, pl.getName());

	}

	@Test
	public void testDeletePlaylist() {
		boolean success = plr.deletePlaylist(playlist2.getId());
		assertTrue("Error when deleting the playlist", success);
		
		Playlist pl = plr.getPlaylist(playlist2.getId());
		assertNull("The playlist has not been deleted correctly", pl);
	}

	@Test
	public void testAddSong() {
		if(song!=null) {
			boolean success = plr.addSong(playlist3.getId(), song.getId());
			assertTrue("Error when adding the song", success);
		}
	}

	@Test
	public void testRemoveSong() {
		//TODO
	}

}
