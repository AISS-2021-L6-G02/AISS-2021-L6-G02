package aiss.model.repository;

import java.util.Collection;

import aiss.model.Developer;

public interface DeveloperRepository {

	public void addDeveloper(Developer d);
	public Collection<Developer> getAllDevelopers();
	public Developer getDeveloper(String developerId);
	public void updateDeveloper(Developer d);
	public void deleteDeveloper(String developerId);
	
	
}
