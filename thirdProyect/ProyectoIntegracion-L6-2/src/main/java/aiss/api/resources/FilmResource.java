package aiss.api.resources;

public class FilmResource {

private String uri = "https://proyecto-integracion-l3-g6.appspot.com/api/films";

private static FilmResource instance = null;

	
	public static FilmResource getInstance() {
		if(instance==null) {
			instance = new FilmResource();
		}
		return instance;
	}
}
