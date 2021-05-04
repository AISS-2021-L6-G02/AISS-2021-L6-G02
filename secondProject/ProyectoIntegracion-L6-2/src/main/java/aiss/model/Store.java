package aiss.model;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;


import org.jboss.resteasy.spi.BadRequestException;

import com.google.api.client.util.DateTime;

public class Store {
	//TODO NURIA Y ALBERTO
	private String id;
	private String name;
	private String location;
	private DateTime openHour;
	private DateTime closeHour;
	private List<ObjetoStore> games;
	private String phone;
	
	
	//Constructors
	public Store() {
		
	}
	public Store(String id, String name, String location, DateTime openHour, DateTime closeHour,
			List<ObjetoStore> games, String phone) {
		super();
		this.id = id;
		this.name = name;
		this.location = location;
		this.openHour = openHour;
		this.closeHour = closeHour;
		this.games = games;
		setPhone(phone);
	}
	public Store(String name, String location, DateTime openHour, DateTime closeHour, List<ObjetoStore> games,
			String phone) {
		super();
		this.name = name;
		this.location = location;
		this.openHour = openHour;
		this.closeHour = closeHour;
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
	public DateTime getOpenHour() {
		return openHour;
	}
	public DateTime getCloseHour() {
		return closeHour;
	}
	public List<ObjetoStore> getGames() {
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
	public void setOpenHour(DateTime openHour) {
		this.openHour = openHour;
	}
	public void setCloseHour(DateTime closeHour) {
		this.closeHour = closeHour;
	}
	public void setGames(List<ObjetoStore> games) {
		this.games = games;
	}
	public void setPhone(String phone) {
		//Aplicar regex
		String patterns = "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3}[- .]?\\d{3}[- .]?\\d{4}$"+
		"|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?){2}\\d{3}$"+
				"|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?)(\\d{2}[ ]?){2}\\d{2}$";
		
		Pattern regex = Pattern.compile(patterns);
		Matcher match = regex.matcher(phone);
		
		if(match.matches()) this.phone = phone;
		else throw new BadRequestException("Phone number not suported");
	}
	
	//Other methods
	
	public void addGame(ObjetoStore game) {
		if(games==null) games = new ArrayList<ObjetoStore>();
		games.add(game);
	}
	
	public void deleteGame(ObjetoStore game) {
		games.remove(game);
	}
	
	public Integer getGamesSize() {
		return this.games.size();
	}
	
	
	
	

}
