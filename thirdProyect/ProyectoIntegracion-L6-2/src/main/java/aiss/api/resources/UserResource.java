
package aiss.api.resources;


import java.util.Arrays;
import java.util.Collection;

import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

import aiss.model.User;
import utils.GeneratorClient;


public class UserResource {
	private static UserResource instance = null;
	private String uri = "http://aisseries.appspot.com/api/users";

	
	
	public static UserResource getInstance() {
		if(instance==null) {
			instance = new UserResource();
		}
		return instance;
	}
	
	public Collection<User> getAllUsers(){
		System.out.println("==================");
		System.out.println("getAllUsers in UserResource");
		System.out.println("==================");
		ClientResource cr = null;
		User [] users = null;
		try {
			cr = GeneratorClient.generate(uri);
			users = cr.get(User[].class);
			
			
		} catch (ResourceException re) {
			System.err.println("Error when retrieving all series: " + cr.getResponse().getStatus());
			throw re;
		}
		for (int i = 0; i < users.length; i++) {
			System.out.println((i+1)+users[i].getUsername());
		}
		return Arrays.asList(users);
	}
	public User getOneUser(String id) {
		System.out.println("==================");
		System.out.println("getOneUser in UserResource");
		System.out.println("==================");
		ClientResource cr = null;
		User  serie = null;
		
		try {
			cr = new ClientResource(uri+"/"+id);
			serie = cr.get(User.class);
			
			
		} catch (ResourceException re) {
			System.err.println("Error when retrieving one series: " + cr.getResponse().getStatus());
			throw re;
		}
		return serie;
	}
	

	public User addUser (User s) {
		ClientResource cr = null;
		User resultUser = null;
		try {
			cr = GeneratorClient.generate(uri);
			cr.setEntityBuffering(true);
			resultUser = cr.post(s, User.class);
		} catch (ResourceException e) {
			System.err.println("Error adding a user."+cr.getResponse().getStatus());
		}
		return resultUser;
	}

	

	public boolean updateUser(User s) {
		ClientResource cr = null;
		boolean success = true;
		try {
			cr = GeneratorClient.generate(uri);
			cr.setEntityBuffering(true);		// Needed for using RESTlet from JUnit tests
			cr.put(s);
			
			
		} catch (ResourceException re) {
			System.err.println("Error when updating the user: " + cr.getResponse().getStatus());
			success = false;
		}
		
		return success;
	}
	
	
	public boolean deleteUser(String id) {
		ClientResource cr = null;
		boolean success = true;
		try {
			cr = new ClientResource(uri + "/" + id);
			cr.setEntityBuffering(true);		// Needed for using RESTlet from JUnit tests
			cr.delete();
			
		} catch (ResourceException re) {
			System.err.println("Error when deleting the user: " + cr.getResponse().getStatus());
			success = false;
		}
		
		return success;
	}
}
