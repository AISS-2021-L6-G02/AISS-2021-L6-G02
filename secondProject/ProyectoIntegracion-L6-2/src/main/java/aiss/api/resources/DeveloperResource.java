package aiss.api.resources;

import java.util.Collection;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import aiss.model.Developer;
import aiss.model.repository.DeveloperRepository;
import aiss.model.repository.MapDeveloperRepository;

@Path("/developers")
public class DeveloperResource {
	private static DeveloperResource instance = null;
	DeveloperRepository repository;
	
	private DeveloperResource() {
		repository = MapDeveloperRepository.getInstance();
	}
	public static DeveloperResource getInstance() {
		if(instance==null) {
			instance = new DeveloperResource();
		}
		return instance;
	}
	@GET
	@Produces("application/json")
	public Collection<Developer> getAll(@QueryParam("name") String name){
		return repository.getAllDevelopers();
	}
}