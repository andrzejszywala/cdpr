package pl.as.cdpr.gshop.business.control;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import pl.as.cdpr.gshop.business.entity.CartItem;
import pl.as.cdpr.gshop.business.entity.Game;
import pl.as.cdpr.gshop.business.entity.Order;
import pl.as.cdpr.gshop.business.entity.OrderedGame;

@ApplicationScoped
public class GshopService {

	@Inject
	@RestClient
	GamesResourceClient gamesResource;

	@Inject
	@RestClient
	CartResourceClient cartResource;

	public List<Game> games() {
		return gamesResource.allGames();
	}

	public void addToCart(Order order) {
		cartResource.addToCart(order);
	}

	public List<OrderedGame> cart() {
		var cartItems = cartResource.cart();
		var gameIds = cartItems.stream().map(CartItem::getGameId).collect(Collectors.toList());
		var gamesById = gamesResource.gamesByIds(gameIds).stream()
				.collect(Collectors.toMap(Game::getId, Function.identity()));

		return cartItems.stream().map(ci -> new OrderedGame(gamesById.get(ci.getGameId()).getName(), ci.getQuantity()))
				.collect(Collectors.toList());
	}
}
