package aiss.model;

public class StoreGame {
	private String storeId;
	private String id;
	private Game game;
	private Double price;
	private Integer stock;

	
	//Constructors
	public StoreGame() {
		
	}
	
	public StoreGame(String id, Game game, Double price, Integer stock, String storeId) {
		super();
		this.id = id;
		this.game = game;
		this.price = price;
		this.stock = stock;
		this.storeId = storeId;
	}
	
	public StoreGame(Game game, Double price, Integer stock, String storeId) {
		super();
		this.game = game;
		this.price = price;
		this.stock = stock;
		this.storeId = storeId;
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
	public String getStoreId() {
		return storeId;
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
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((game == null) ? 0 : game.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((stock == null) ? 0 : stock.hashCode());
		result = prime * result + ((storeId == null) ? 0 : storeId.hashCode());
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
		if (storeId == null) {
			if (other.storeId != null)
				return false;
		} else if (!storeId.equals(other.storeId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "StoreGame [storeId=" + storeId + ", id=" + id + ", game=" + game + ", price=" + price + ", stock=" + stock
				+ "]";
	}
}
