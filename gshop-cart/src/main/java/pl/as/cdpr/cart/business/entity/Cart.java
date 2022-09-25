package pl.as.cdpr.cart.business.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@Entity
@Table(name = "CART")
public class Cart extends PanacheEntityBase {
	
	@Id
	@Column(name = "ID")
	public String id;
	
	@OneToMany(orphanRemoval = true)
	@JoinColumn(name = "CART_ID")
	public List<CartItem> items = new ArrayList<>();

	public Cart() {
	}
	
	public Cart(String id) {
		this.id = id;
	}
}
