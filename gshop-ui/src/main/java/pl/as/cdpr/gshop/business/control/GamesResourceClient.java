package pl.as.cdpr.gshop.business.control;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.rest.client.annotation.RegisterClientHeaders;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import pl.as.cdpr.gshop.business.entity.Game;

// this annotation propagates authorization header with jwt token
@RegisterClientHeaders
@RegisterRestClient(configKey = "gshop.games")
@Path("games")
public interface GamesResourceClient {

	@GET
	public List<Game> allGames();

	@GET
	@Path("{id}")
	public Game gameById(@PathParam("id") int id);

	@POST
	public Response save(Game game);

	@PUT
	public Response update(Game game);

	@DELETE
	@Path("{id}")
	public Response delete(@PathParam("id") int id);

	@POST
	@Path("search")
	public List<Game> gamesByIds(List<Integer> games);
}
