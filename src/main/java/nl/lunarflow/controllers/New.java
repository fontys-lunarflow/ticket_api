package nl.lunarflow.controllers;

import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.POST;
import nl.lunarflow.models.Config;
import nl.lunarflow.models.Ticket;
import nl.lunarflow.services.*;

/*
 * Try to create a ticket with GitLab
 * If it fails, return an internal server error
 * If it succeeds, return ticket payload
*/

@Path("/api/new")
public class New {
    @POST
    public Response request(Ticket ticket) {
        try {
            ticket = BaseService.newTicket(new Config(), ticket, new GitlabService());
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.toString()).build();
        }
        return Response.ok(ticket).build();
    }
}
