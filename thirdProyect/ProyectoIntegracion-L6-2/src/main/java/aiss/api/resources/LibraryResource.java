package aiss.api.resources;

public class LibraryResource {
private static LibraryResource instance = null;

	
	public static LibraryResource getInstance() {
		if(instance==null) {
			instance = new LibraryResource();
		}
		return instance;
	}

}
