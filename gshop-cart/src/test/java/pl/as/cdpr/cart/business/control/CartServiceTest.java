package pl.as.cdpr.cart.business.control;

import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;
import pl.as.cdpr.cart.business.entity.Cart;
import pl.as.cdpr.cart.business.entity.Order;

@QuarkusTest
@QuarkusTestResource(H2DatabaseTestResource.class)
class CartServiceTest {

	@Inject
	CartService cartService;
	
	@Test
	void addToNewCart() {
		cartService.addToCart("cdpr", new Order(1, 2));
		Cart cart = Cart.findById("cdpr");
		assertTrue(cart.items.size() == 1);
	}

	@Test
	void updateExistingCart() {
		Cart cart = Cart.findById("andrzej");
		
		cartService.addToCart("andrzej", new Order(1, 100));
		cart = Cart.findById("andrzej");
		assertTrue(cart.items.size() == 2);
		assertTrue(cart.items.stream().anyMatch(ci -> ci.gameId == 1 && ci.quantity == 100));
	}
}
