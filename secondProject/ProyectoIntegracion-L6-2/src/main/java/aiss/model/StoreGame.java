package aiss.model;

public class StoreGame {
	private String id;
	private Game game;
	private Double price;
	private Integer stock;

	
	//Constructors
	public StoreGame() {
		
	}
	
	public StoreGame(String id, Game game, Double price, Integer stock) {
		super();
		this.id = id;
		this.game = game;
		this.price = price;
		this.stock = stock;
	}
	
	public StoreGame(Game game, Double price, Integer stock) {
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
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((game == null) ? 0 : game.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((stock == null) ? 0 : stock.hashCode());
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
		StoreGame other = (StoreGame) obj;
		if (game == null) {
			if (other.game != null)
				return false;
		} else if (!game.equals(other.game))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (stock == null) {
			if (other.stock != null)
				return false;
		} else if (!stock.equals(other.stock))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ObjetoStore [id=" + id + ", game=" + game + ", price=" + price + ", stock=" + stock + "]";
	}
	
	
	

}
