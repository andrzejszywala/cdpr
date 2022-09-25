package pl.as.cdpr.gshop.business.entity;

public class OrderedGame {
	private String gameName;

	private int quantity;

	public OrderedGame() {
	}

	public OrderedGame(String gameName, int quantity) {
		super();
		this.gameName = gameName;
		this.quantity = quantity;
	}

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
