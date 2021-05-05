package aiss.model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;


import org.jboss.resteasy.spi.BadRequestException;


public class Store {
	//TODO NURIA Y ALBERTO
	private String id;
	private String name;
	private String location;
	private LocalTime openHour;
	private LocalTime closeHour;
	private List<ObjetoStore> games;
	private String phone;
	
	
	//Constructors
	public Store() {
		
	}
	public Store(String id, String name, String location, LocalTime openHour, LocalTime closeHour,
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
	public Store(String name, String location, LocalTime openHour, LocalTime closeHour, List<ObjetoStore> games,
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
	public LocalTime getOpenHour() {
		return openHour;
	}
	public LocalTime getCloseHour() {
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
	public void setOpenHour(LocalTime openHour) {
		this.openHour = openHour;
	}
	public void setCloseHour(LocalTime closeHour) {
		this.closeHour = closeHour;
	}
	public void setGames(List<ObjetoStore> games) {
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
	@Override
	public String toString() {
		return "Store [id=" + id + ", name=" + name + ", location=" + location + ", openHour=" + openHour
				+ ", closeHour=" + closeHour + ", games=" + games + ", phone=" + phone + "]";
	}
	
	
	
	

}
