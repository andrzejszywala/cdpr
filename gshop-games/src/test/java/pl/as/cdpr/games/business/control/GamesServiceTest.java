package pl.as.cdpr.games.business.control;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;
import pl.as.cdpr.games.business.entity.Game;

@QuarkusTest
@QuarkusTestResource(H2DatabaseTestResource.class)
class GamesServiceTest {

	@Inject
	GamesService gamesService;
	@Inject
	EntityManager em;
	
	@Test
	void allGames() {
		assertTrue(gamesService.allGames().size() > 0);
	}
	
	@Test
	void save() {
		Game newGame = new Game();
		newGame.name = "New game";
		newGame.publisher = "Andrzej Szywała";
		newGame.quantity = 0;
		newGame.price = 1000;
		
		gamesService.save(newGame);
		
		assertTrue(gamesService.allGames()
				.stream()
				.anyMatch(g -> "New game".equals(g.name)));
		
	}
	
	@Test
	void update() {
		Game cyberpunk = gamesService.gameById(1);
		cyberpunk.quantity = 500;
		
		gamesService.update(cyberpunk);
		
		assertEquals(500, gamesService.gameById(1).quantity);
	}
	
	@Test
	void delete() {
		gamesService.delete(3);
		
		assertTrue(gamesService.allGames()
				.stream()
				.noneMatch(g -> g.id == 3));
	}


}
