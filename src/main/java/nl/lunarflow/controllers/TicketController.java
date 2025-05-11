package nl.lunarflow.controllers;

import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import nl.lunarflow.models.Config;
import nl.lunarflow.models.Ticket;
import nl.lunarflow.services.GitlabService;

public class TicketController {
    @Path("/api/ticket/new")
    @POST
    public Response newTicket(Ticket ticket) {
        try {
            ticket = Handler.newTicket(new Config(), ticket, new GitlabService());
        } catch (Exception err) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(err.toString()).build();
        }
        return Response.ok(ticket).build();
    }

    @Path("/api/ticket/done")
    @POST
    public Response doneTicket(Ticket ticket) {
        try {
            ticket = Handler.doneTicket(new Config(), ticket, new GitlabService());
        } catch (Exception err) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(err.toString()).build();
        }
        return Response.ok(ticket).build();
    }

    @Path("/api/ticket/request")
    @GET
    public Response requestTicket(Ticket ticket) {
        try {
            ticket = Handler.reqTicket(new Config(), ticket, new GitlabService());
        } catch (Exception err) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(err.toString()).build();
        }
        return Response.ok(ticket).build();
    }
}
