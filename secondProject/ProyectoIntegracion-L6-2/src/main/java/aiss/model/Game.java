package aiss.model;

import java.util.ArrayList;
import java.util.List;

public class Game {
	private String id;
	private String title;
	private String description;
	private Integer year;
	private Developer developer;
	private Double score;
	private List<Platform> platforms;
	private List<Genre> genres;
	private List<Mode> modes;
	
	//Getters
	public String getId() {
		return id;
	}
	public String getTitle() {
		return title;
	}
	public String getDescription() {
		return description;
	}
	public Integer getYear() {
		return year;
	}
	public Developer getDeveloper() {
		return developer;
	}
	public Double getScore() {
		return score;
	}
	public List<Platform> getPlatforms() {
		return platforms;
	}
	public List<Genre> getGenres() {
		return genres;
	}
	public List<Mode> getModes() {
		return modes;
	}
	
	//Setters
	public void setId(String id) {
		this.id=id;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public void setDeveloper(Developer developer) {
		this.developer = developer;
	}
	public void setScore(Double score) {
		this.score = score;
	}
	public void setPlatforms(List<Platform> platforms) {
		this.platforms = platforms;
	}
	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}
	public void setModes(List<Mode> modes) {
		this.modes = modes;
	}
	
	//Other methods
	public void addPlatform(Platform platform) {
		if(platforms==null) platforms = new ArrayList<Platform>();
		platforms.add(platform);
	}
	
	public void deletePlatform(Platform platform) {
		platforms.remove(platform);
	}
	
	public void addGenre(Genre genre) {
		if(genres==null) genres = new ArrayList<Genre>();
		genres.add(genre);
	}
	
	public void deleteGenre(Genre genre) {
		genres.remove(genre);
	}
	
	public void addMode(Mode mode) {
		if(modes==null) modes = new ArrayList<Mode>();
		modes.add(mode);
	}
	
	public void deleteMode(Mode mode) {
		modes.remove(mode);
	}
	

}
