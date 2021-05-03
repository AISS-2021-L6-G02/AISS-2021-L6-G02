package aiss.model.repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import aiss.model.Platform;
import aiss.model.Playlist;


public class MapPlatformRepository implements PlatformRepository{
	Map<String, Platform> mapPlatformRepository;
	
	private static MapPlatformRepository instance=null;
	private int index=0;			// Index to create playlists and songs' identifiers.
	
	
	public static MapPlatformRepository getInstance() {
		
		if (instance==null) {
			instance = new MapPlatformRepository();
			instance.init();
		}
		
		return instance;
	}
	
	public void init() {
		
		mapPlatformRepository = new HashMap<String,Platform>();
		
		// Create songs
		Platform pc=new Platform();
		pc.setId(String.valueOf(index));
		pc.setName("Program computer");
		
	
		
	}

	@Override
	public void addPlatform(Platform p) {
		String id = "P"+index++;
		p.setId(id);
		mapPlatformRepository.put(id, p);
		
	}

	@Override
	public Collection<Platform> getAllPlatforms() {
		// TODO Auto-generated method stub
		return this.mapPlatformRepository.values();
	}

	
	@Override
	public Platform getPlatform(String platformId) {
		return this.mapPlatformRepository.get(platformId);
	}

	@Override
	public void updatePlatform(Platform p) {
		// TODO Auto-generated method stub
		this.mapPlatformRepository.put(p.getId(), p);
		
	}

	@Override
	public void deleteGame(String gameId) {
		this.mapPlatformRepository.remove(gameId);
		
	}
}
