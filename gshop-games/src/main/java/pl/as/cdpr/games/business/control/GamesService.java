package pl.as.cdpr.games.business.control;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.PathParam;

import pl.as.cdpr.games.business.entity.Game;

@ApplicationScoped
public class GamesService {

	public List<Game> allGames() {
		return Game.listAll();
	}
	
	public Game gameById(@PathParam("id") int id) {
		return Game.findById(id);
	}

	@Transactional
	public Game save(Game game) {
		game.persist();
		return game;
	}

	@Transactional
	public Game update(Game game) {		
		return Game.getEntityManager().merge(game);
	}

	@Transactional
	public void delete(int id) {
		Game.findByIdOptional(id)
			.ifPresent(g -> g.delete());
	}

	public List<Game> gamesByIds(List<Integer> games) {
		return Game.findByIds(games);
	}
	
}
