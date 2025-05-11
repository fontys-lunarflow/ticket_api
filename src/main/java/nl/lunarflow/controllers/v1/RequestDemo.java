package nl.lunarflow.controllers.v1;

import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.GET;
import nl.lunarflow.controllers.Handler;
import nl.lunarflow.models.Ticket;
import nl.lunarflow.services.*;

// Request a ticket: Either return a filled out ticket, or crash

@Path("/demo/v1/ticket/request")
public class RequestDemo {
    @GET
    public Response requestTicket(Ticket ticket) {

        try {
            return Response.ok(Handler.reqTicket(null, ticket, new TestService())).build();
        } catch (Exception err) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(err.toString()).build();
        }
    }
}
