package aiss.model;

public class Developer {
	private String id;
	private String name;
	private Integer year;
	private String country;
	
	public Developer() {}
	
	public Developer(String name, Integer year, String country) {
		this.name = name;
		this.year = year;
		this.country = country;
	}
	
	public Developer(String id, String name, Integer year, String country) {
		this.id = id;
		this.name = name;
		this.year = year;
		this.country = country;
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

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	
}
