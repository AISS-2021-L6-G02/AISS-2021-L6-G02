package aiss.model;

public class ObjetoStore {
	private String id;
	private Game game;
	private Double price;
	private Integer stock;

	
	//Constructors
	public ObjetoStore() {
		
	}
	
	public ObjetoStore(String id, Game game, Double price, Integer stock) {
		super();
		this.id = id;
		this.game = game;
		this.price = price;
		this.stock = stock;
	}
	
	public ObjetoStore(Game game, Double price, Integer stock) {
		super();
		this.game = game;
		this.price = price;
		this.stock = stock;
	}

	//Getters
	public String getId() {
		return id;
	}
	public Game getGame() {
		return game;
	}
	public Double getPrice() {
		return price;
	}
	public Integer getStock() {
		return stock;
	}
	
	//Setters
	public void setId(String id) {
		this.id=id;
	}
	public void setGame(Game game) {
		this.game = game;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	

}
