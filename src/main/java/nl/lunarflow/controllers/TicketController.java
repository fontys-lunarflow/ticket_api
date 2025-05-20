package nl.lunarflow.controllers;

import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import nl.lunarflow.models.Config;
import nl.lunarflow.models.Ticket;
import nl.lunarflow.GitlabService;

public class TicketController {
    @Path("/api/issue/create")
    @POST
    public Response newTicket(Ticket ticket) {
        try {
            ticket = new GitlabService().createIssue(new Config(), ticket);
        } catch (Exception err) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(err.toString()).build();
        }
        return Response.ok(ticket).build();
    }

    @Path("/api/issue/close")
    @POST
    public Response doneTicket(Ticket ticket) {
        try {
            ticket = new GitlabService().closeIssue(new Config(), ticket);
        } catch (Exception err) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(err.toString()).build();
        }
        return Response.ok(ticket).build();
    }

    @Path("/api/issue/read")
    @GET
    public Response requestTicket(Ticket ticket) {
        try {
            ticket = new GitlabService().readIssue(new Config(), ticket);
        } catch (Exception err) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(err.toString()).build();
        }
        return Response.ok(ticket).build();
    }

    @Path("/api/ticket/setlabels")
    @GET
    public Response setLabels(Ticket ticket) {
        try {
            ticket = new GitlabService().setLabels(new Config(), ticket);
        } catch (Exception err) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(err.toString()).build();
        }
        return Response.ok(ticket).build();
    }
}
