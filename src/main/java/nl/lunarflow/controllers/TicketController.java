package nl.lunarflow.controllers;

import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.POST;
import nl.lunarflow.models.Config;
import nl.lunarflow.models.Form;
import nl.lunarflow.models.Ticket;
import nl.lunarflow.GitlabService;

@Path("/api/ticket")
public class TicketController {

    @Path("/create")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response newTicket(Ticket ticket) {
        try {
            ticket = new GitlabService().createIssue(new Config(), ticket);
        } catch (Exception err) {
            return Response.ok(new Form<Ticket>(true, err.getMessage(), null)).build();
        }
        return Response.ok(new Form<Ticket>(false, null, ticket)).build();
    }

    @Path("/close")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response doneTicket(Ticket ticket) {
        try {
            ticket = new GitlabService().closeIssue(new Config(), ticket);
        } catch (Exception err) {
            return Response.ok(new Form<Ticket>(true, err.getMessage(), null)).build();
        }
        return Response.ok(new Form<Ticket>(false, null, ticket)).build();
    }

    @Path("/read")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response requestTicket(Ticket ticket) {
        try {
            ticket = new GitlabService().readIssue(new Config(), ticket);
        } catch (Exception err) {
            return Response.ok(new Form<Ticket>(true, err.getMessage(), null)).build();
        }
        return Response.ok(new Form<Ticket>(false, null, ticket)).build();
    }

    @Path("/setlabels")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response setLabels(Ticket ticket) {
        try {
            ticket = new GitlabService().setLabels(new Config(), ticket);
        } catch (Exception err) {
            return Response.ok(new Form<Ticket>(true, err.getMessage(), null)).build();
        }
        return Response.ok(new Form<Ticket>(false, null, ticket)).build();
    }
}
