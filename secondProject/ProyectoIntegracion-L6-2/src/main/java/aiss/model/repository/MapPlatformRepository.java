package aiss.model.repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import aiss.model.Platform;

public class MapPlatformRepository implements PlatformRepository {
	Map<String, Platform> mapPlatformRepository;

	private static MapPlatformRepository instance = null;
	private int index = 0; // Index to create playlists and songs' identifiers.

	public static MapPlatformRepository getInstance() {

		if (instance == null) {
			instance = new MapPlatformRepository();
			instance.init();
		}

		return instance;
	}

	public void init() {

		mapPlatformRepository = new HashMap<String, Platform>();

		// Create songs
		Platform p1 = new Platform();

		p1.setName("Program computer");
		Platform p2 = new Platform();

		p2.setName("Nintendo switch");
		Platform p3 = new Platform();

		p3.setName("Android");
		Platform p4 = new Platform();

		p4.setName("Playstation 5");
		Platform p5 = new Platform();

		p5.setName("Xbox series s");

		addPlatform(p1);
		addPlatform(p2);
		addPlatform(p3);
		addPlatform(p4);
		addPlatform(p5);
	}

	@Override
	public void addPlatform(Platform p) {
		String id = "P" + index++;
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
	public void deletePlatform(String id) {
		this.mapPlatformRepository.remove(id);

	}
}
