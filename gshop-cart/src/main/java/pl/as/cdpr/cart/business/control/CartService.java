package pl.as.cdpr.cart.business.control;

import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

import pl.as.cdpr.cart.business.entity.Cart;
import pl.as.cdpr.cart.business.entity.CartItem;
import pl.as.cdpr.cart.business.entity.Order;

@ApplicationScoped
public class CartService {

	@Transactional
	public Cart addToCart(String user, Order order) {
		Cart cart = getUserCart(user);
		addOrderToCart(cart, order);
		return cart;
	}
	
	public List<CartItem> cart(String user) {
		return getUserCart(user).items;
	}
	
	private void addOrderToCart(Cart cart, Order order) {
		Optional<CartItem> existingGame = cart.items.stream().filter(i -> i.gameId == order.getGameId()).findFirst();
		existingGame.ifPresentOrElse(item -> updateExistingCartItem(item, order), () -> addNewCartItem(cart, order));
	}
	
	private void addNewCartItem(Cart cart, Order order) {
		CartItem cartItem = new CartItem(order.getGameId(), order.getQuantity());
		cartItem.persist();
		cart.items.add(cartItem);
	}

	private void updateExistingCartItem(CartItem item, Order order) {
		item.quantity = order.getQuantity();
	}
	
	private Cart getUserCart(String user) {
		return Cart.<Cart>findByIdOptional(user)
				.orElseGet(() -> newCart(user));
	}

	private Cart newCart(String user) {
		Cart cart = new Cart(user);
		cart.persist();
		return cart;
	}
}
