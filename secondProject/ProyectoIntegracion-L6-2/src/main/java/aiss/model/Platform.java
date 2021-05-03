package aiss.model;

public class Platform {
	private String id;
	private String name;
	
	
	
	public Platform() {}
	
	public Platform(String name) {
		this.name = name;
	}
	
	public Platform(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	
}
