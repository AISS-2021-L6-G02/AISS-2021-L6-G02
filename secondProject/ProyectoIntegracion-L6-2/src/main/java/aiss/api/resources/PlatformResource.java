package aiss.api.resources;

import javax.ws.rs.Path;

import aiss.model.repository.MapPlatformRepository;
import aiss.model.repository.PlatformRepository;

@Path("/platforms")
public class PlatformResource {
	private static PlatformResource _instance = null;
	PlatformRepository repository;

	private PlatformResource() {
		repository = MapPlatformRepository.getInstance();
	}

	public static PlatformResource getInstance() {
		if (_instance == null)
			_instance = new PlatformResource();
		return _instance;
	}
	
	
}
