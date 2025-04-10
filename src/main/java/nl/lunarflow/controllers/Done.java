package nl.lunarflow.controllers;

import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.GET;
import nl.lunarflow.models.Config;
import nl.lunarflow.models.Ticket;
import nl.lunarflow.services.*;

// Move ticket state to done, or fail

@Path("/api/done")
public class Done {
    @GET
    public Response doneTicket(Ticket ticket) {
        try {
            ticket = BaseService.doneTicket(new Config(), ticket, new GitlabService());
        } catch (Exception err) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(err.toString()).build();
        }
        return Response.ok(ticket).build();
    }
}
