package utils;

import org.restlet.resource.ClientResource;

public class GeneratorClient {
	
	public static ClientResource generate(String uri) {
		ClientResource r = new ClientResource(uri);
		return r;
	}
}
