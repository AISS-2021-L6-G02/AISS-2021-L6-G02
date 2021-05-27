package aiss.api.resources;

public class BookResource {
	private static BookResource instance = null;
	private String uri = "https://proyecto-integracion-l3-g6.appspot.com/api/books";

	
	
	public static BookResource getInstance() {
		if(instance==null) {
			instance = new BookResource();
		}
		return instance;
	}

}
