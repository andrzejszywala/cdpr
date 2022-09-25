package pl.as.cdpr.cart.business.boundary;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import pl.as.cdpr.cart.business.control.CartService;
import pl.as.cdpr.cart.business.entity.CartItem;
import pl.as.cdpr.cart.business.entity.Order;

@Tag(name = "CartItem entpoint", description = "Operations to manage user cart")
@Path("carts")
public class CartResource {

	@Inject
	CartService cartService;
	
	@Context 
	SecurityContext securityContext;
	
	@Operation(description = "Adds new game to user cart. If game alredy exists in cart it updates quantity")
    @APIResponse(responseCode = "200", 
                 description = "Game successfully added to the user cart")
	@RolesAllowed("USER")
	@POST
	@Path("add_order")
	public Response addToCart(Order order) {
		var userPrincipal = securityContext.getUserPrincipal();
		String user = userPrincipal == null ? "anonymous" : userPrincipal.getName();
		cartService.addToCart(user, order);
		return Response.ok().build();
	}
	
	@Operation(description = "Returns content of user cart")
    @APIResponse(responseCode = "200", 
                 description = "All cart items of user cart")
	@GET
	@RolesAllowed("USER")
	public List<CartItem> cart() {
		var userPrincipal = securityContext.getUserPrincipal();
		String user = userPrincipal == null ? "anonymous" : userPrincipal.getName();
		return cartService.cart(user);
	}
	
}
