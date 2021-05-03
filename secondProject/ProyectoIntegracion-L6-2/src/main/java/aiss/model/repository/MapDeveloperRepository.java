package aiss.model.repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import aiss.model.Developer;


public class MapDeveloperRepository implements DeveloperRepository {

	Map<String, Developer> developerMap;
	private static MapDeveloperRepository instance = null;
	private int index = 0;
	
	public static MapDeveloperRepository getInstance() {
		if (instance==null) {
			instance = new MapDeveloperRepository();
			instance.iniData();
		}
		
		return instance;
	}
	
	public void iniData() {
		developerMap = new HashMap<String, Developer>();
		Developer nintendo = new Developer();
		nintendo.setName("Nintendo");
		nintendo.setYear(1889);
		nintendo.setCountry("Japan");
		addDeveloper(nintendo);
		
		Developer ea = new Developer();
		ea.setName("Electronic Arts");
		ea.setYear(1991);
		ea.setCountry("USA");
		addDeveloper(ea);
		
		Developer activision = new Developer();
		activision.setName("Activision");
		activision.setYear(1978);
		activision.setCountry("USA");
		addDeveloper(activision);
		
		Developer capcom = new Developer();
		capcom.setName("Capcom Co., Ltd.");
		capcom.setYear(1971);
		capcom.setCountry("Japan");
		addDeveloper(capcom);
		
		Developer rockStar = new Developer();
		rockStar.setName("Rockstar Games");
		rockStar.setYear(1998);
		rockStar.setCountry("USA");
		addDeveloper(rockStar);
		
		Developer konami = new Developer();
		konami.setName("Konami");
		konami.setYear(1969);
		konami.setCountry("Japan");
		addDeveloper(konami);
		
		Developer valve = new Developer();
		valve.setName("Valve Corporation");
		valve.setYear(1996);
		valve.setCountry("USA");
		addDeveloper(valve);
		
		Developer bioware = new Developer();
		bioware.setName("BioWare");
		bioware.setYear(1995);
		bioware.setCountry("Canada");
		addDeveloper(bioware);
		
	}
	
	@Override
	public Developer addDeveloper(Developer d) {
		String id = "d" + index++;
		d.setId(id);
		developerMap.put(id, d);
		return d;
	}

	@Override
	public Collection<Developer> getAllDevelopers() {
		return developerMap.values();
	}

	@Override
	public Developer getDeveloper(String developerId) {
		return developerMap.get(developerId);
	}

	@Override
	public void updateDeveloper(Developer d) {
		developerMap.put(d.getId(), d);
	}

	@Override
	public void deleteDeveloper(String developerId) {
		developerMap.remove(developerId);
	}

	
}
