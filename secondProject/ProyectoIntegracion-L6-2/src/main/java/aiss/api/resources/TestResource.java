package aiss.api.resources;

import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

@Path("/tests")
public class TestResource {
	private static TestResource instance=null;
	
	public UriInfo doSomthing(@Context UriInfo uriInfo){
        return uriInfo;
    }
	public static TestResource getInstance() {
		if(instance==null) {
			instance = new TestResource();
		}
		return instance;
	}
}
