package pl.as.cdpr.cart.business.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@Entity
@Table(name = "CART_ITEM")
public class CartItem extends PanacheEntityBase {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
    public int id;
	
	@Column(name = "GAME_ID")
	public int gameId;
		
	@NotNull
	@Min(value = 0)
	@Column(name = "QUANTITY")
	public int quantity;
	
	public CartItem() {
	}

	public CartItem(int gameId, @NotNull @Min(0) int quantity) {
		super();
		this.gameId = gameId;
		this.quantity = quantity;
	}
	
		
}
