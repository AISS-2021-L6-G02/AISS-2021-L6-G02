package aiss.model.repository;

import java.util.Collection;

import aiss.model.Platform;

public interface PlatformRepository {

	
		public void addPlatform(Platform p);
		public Collection<Platform> getAllPlatforms();
		public Platform getPlatform(String platformId);
		public void updatePlatform(Platform p);
		public void deletePlatform(String id);
		
		
		
}
