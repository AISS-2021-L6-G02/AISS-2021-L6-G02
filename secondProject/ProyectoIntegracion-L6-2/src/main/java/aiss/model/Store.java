package aiss.model;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;


import org.jboss.resteasy.spi.BadRequestException;


public class Store {
	private String id;
	private String name;
	private String location;
	private List<StoreGame> games;
	private String phone;
	
	
	//Constructors
	public Store() {
		super();
		
	}
	public Store(String id, String name, String location, List<StoreGame> games, String phone) {
		super();
		this.id = id;
		this.name = name;
		this.location = location;
		this.games = games;
		setPhone(phone);
	}
	public Store(String name, String location, List<StoreGame> games,
			String phone) {
		super();
		this.name = name;
		this.location = location;
		this.games = games;
		setPhone(phone);
	}
	//Getters
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getLocation() {
		return location;
	}

	public List<StoreGame> getGames() {
		return games;
	}
	public String getPhone() {
		return phone;
	}
	
	//Setters
	public void setId(String id) {
		this.id=id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setLocation(String location) {
		this.location = location;
	}

	public void setGames(List<StoreGame> games) {
		
		if(games != null)
		{
			games.forEach(g -> { if(g != null) g.setStoreId(this.id); });
		}
		
		this.games = games;
	}
	public void setPhone(String phone) {
		//Aplicar regex
		String patterns = "^(\\+34|0034|34)?[6789]\\d{8}$";
		
		Pattern regex = Pattern.compile(patterns);
		Matcher match = regex.matcher(phone);
		
		if(match.matches()) this.phone = phone;
		else throw new BadRequestException("Phone number not suported");
	}
	
	//Other methods
	
	public void addGame(StoreGame game) {
		
		if(game != null)
		{
			game.setStoreId(this.id);
		}
		
		if(games==null) games = new ArrayList<StoreGame>();
		games.add(game);
	}
	
	public void deleteGame(StoreGame game) {
		games.remove(game);
	}
	
	public Integer getGamesSize() {
		return this.games!=null? this.games.size():0;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((games == null) ? 0 : games.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Store other = (Store) obj;
		if (games == null) {
			if (other.games != null)
				return false;
		} else if (!games.equals(other.games))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Store [id=" + id + ", name=" + name + ", location=" + location +
				", games=" + games + ", phone=" + phone + "]";
	}
	
	
	
	
	

}
