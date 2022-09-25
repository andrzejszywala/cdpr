package pl.as.cdpr.gshop.business.control;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.rest.client.annotation.RegisterClientHeaders;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import pl.as.cdpr.gshop.business.entity.CartItem;
import pl.as.cdpr.gshop.business.entity.Order;

@RegisterClientHeaders
@RegisterRestClient(configKey = "gshop.cart")
@Path("carts")
public interface CartResourceClient {

	@POST
	@Path("add_order")
	public Response addToCart(Order order);

	@GET
	public List<CartItem> cart();
}
