package aiss.api;


import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import aiss.api.resources.DeveloperResource;
import aiss.api.resources.GameResource;
import aiss.api.resources.GenreResource;
import aiss.api.resources.PlatformResource;
import aiss.api.resources.StoreResource;

public class RastreGamesApplication extends Application{
	private Set<Object> singletons = new HashSet<Object>();
	private Set<Class<?>> classes = new HashSet<Class<?>>();

	// Loads all resources that are implemented in the application
	// so that they can be found by RESTEasy.
	public RastreGamesApplication() {
		singletons.add(GenreResource.getInstance());
		singletons.add(PlatformResource.getInstance());
		singletons.add(DeveloperResource.getInstance());
		singletons.add(GameResource.getInstance());
		singletons.add(StoreResource.getInstance());
	}

	@Override
	public Set<Class<?>> getClasses() {
		return classes;
	}

	@Override
	public Set<Object> getSingletons() {
		return singletons;
	}
}
