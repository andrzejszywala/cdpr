package pl.as.cdpr.cart.business.entity;

public class Order {

	private int gameId;
	private int quantity;

	public Order() {
	}

	public Order(int gameId, int quantity) {
		super();
		this.gameId = gameId;
		this.quantity = quantity;
	}

	public int getGameId() {
		return gameId;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
