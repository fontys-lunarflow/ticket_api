package nl.lunarflow.controllers;

import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.GET;
import nl.lunarflow.models.Config;
import nl.lunarflow.models.Ticket;
import nl.lunarflow.services.*;

// Request a ticket: Either return a filled out ticket, or crash

@Path("/api/request")
public class Request {
    @GET
    public Response requestTicket(Ticket ticket) {
        try {
            ticket = BaseService.reqTicket(new Config(), ticket, new GitlabService());
        } catch (Exception err) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(err.toString()).build();
        }
        return Response.ok(ticket).build();
    }
}
