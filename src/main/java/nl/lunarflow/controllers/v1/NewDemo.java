package nl.lunarflow.controllers.v1;

import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.POST;
import nl.lunarflow.controllers.Handler;
import nl.lunarflow.models.Config;
import nl.lunarflow.models.Ticket;
import nl.lunarflow.services.TestService;

@Path("/demo/v1/issue/new")
public class NewDemo {
    @POST
    public Response newTicket(Ticket ticket) {
        try {
            ticket = Handler.newTicket(new Config(), ticket, new TestService());
        } catch (Exception err) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(err.toString()).build();
        }
        return Response.ok(ticket).build();
    }
}
