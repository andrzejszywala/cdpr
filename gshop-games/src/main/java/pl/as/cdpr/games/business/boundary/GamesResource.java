package pl.as.cdpr.games.business.boundary;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import pl.as.cdpr.games.business.control.GamesService;
import pl.as.cdpr.games.business.entity.Game;

@Tag(name = "Games endpoint", description = "Operations to manage games catalog")
@Path("games")
public class GamesResource {

	@Inject
	GamesService gamesService;
	
	@Context 
	SecurityContext securityContext;

	@Context
	UriInfo uriInfo;
	
	@Operation(description = "Fetch all games in database. This operation is for logged and not logged users")
    @APIResponse(responseCode = "200", 
                 description = "Entities from GAME table")
	@GET
	@PermitAll
	public List<Game> allGames() {
		return gamesService.allGames();
	}
	
	@Operation(description = "Fetch games by ids. This operation is for all users")
    @APIResponse(responseCode = "200", 
                 description = "Entities from GAME table")
	@POST
	@PermitAll
	@Path("search")
	public List<Game> gamesByIds(List<Integer> games) {
		return gamesService.gamesByIds(games);
	}

	@Operation(description = "Finds game by id")
    @APIResponse(responseCode = "200", 
                 description = "Entity from GAME table")
	@GET
	@PermitAll
	@Path("{id}")
	public Game gameById(@PathParam("id") int id) {
		return gamesService.gameById(id);
	}

	@Operation(description = "Persists new game to database. Allowed only for ADMIN role and game must pass validation.")
    @APIResponse(responseCode = "201", 
                 description = "In location header is link to persisted game")
	@POST
	@RolesAllowed("ADMIN")
	public Response save(@Valid Game game) {
		return Response.created(uriInfo.getBaseUriBuilder().path(GamesResource.class)
				.path(GamesResource.class, "gameById").build(gamesService.save(game).id)).build();
	}

	@Operation(description = "Persists new game to database. Allowed only for ADMIN role and game must pass validation.")
    @APIResponse(responseCode = "200", 
                 description = "Update successful")
	@PUT
	@RolesAllowed("ADMIN")
	public Response update(@Valid  Game game) {
		gamesService.update(game);
		return Response.ok().build();
	}

	@Operation(description = "Deletes game from database. Allowed only for ADMIN role.")
    @APIResponse(responseCode = "204", 
                 description = "Successful delete")
	@DELETE
	@Path("{id}")
	@RolesAllowed("ADMIN")
	public Response delete(@PathParam("id") int id) {
		gamesService.delete(id);
		return Response.status(Status.NO_CONTENT).build();
	}
}
