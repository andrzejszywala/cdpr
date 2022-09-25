package pl.as.cdpr.gshop.business.boundary;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import pl.as.cdpr.gshop.business.control.GshopService;
import pl.as.cdpr.gshop.business.entity.Game;
import pl.as.cdpr.gshop.business.entity.Order;
import pl.as.cdpr.gshop.business.entity.OrderedGame;

@Tag(name = "Gateway", description = "Api gateway for the frontend application")
@Path("gshop")
public class GshopResource {

	@Inject
	GshopService gshopService;

	@Operation(description = "Fetch all games in database. This operation is for logged and not logged users")
    @APIResponse(responseCode = "200", 
                 description = "Entities from GAME table")
	@Path("games")
	@GET
	@PermitAll
	public List<Game> games() {
		return gshopService.games();
	}

	@Operation(description = "Adds game to user cart")
    @APIResponse(responseCode = "200", 
                 description = "Operation successful")
	@Path("add_to_cart")
	@POST
	@RolesAllowed("USER")
	public Response addToCart(Order order) {
		gshopService.addToCart(order);
		return Response.ok().build();
	}

	@Operation(description = "Content of user cart")
    @APIResponse(responseCode = "200", 
                 description = "List of cart items")
	@Path("cart")
	@GET
	@RolesAllowed("USER")
	public List<OrderedGame> cart() {
		return gshopService.cart();
	}

}
