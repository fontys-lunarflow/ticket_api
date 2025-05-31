package nl.lunarflow.controllers;

import java.util.*;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import nl.lunarflow.models.*;
import nl.lunarflow.GitlabService;

@Path("/api/labels")
public class LabelController {

    @Path("/create")
    @POST
    public Response newLabel(Taglabel label) {
        try {
            label = new GitlabService().createLabel(new Config(), label);
        } catch (Exception err) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(err.toString()).build();
        }
        return Response.ok(label).build();
    }

    @Path("/delete")
    @POST
    public Response delLabel(Taglabel label) {
        try {
            label = new GitlabService().deleteLabel(new Config(), label);
        } catch (Exception err) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(err.toString()).build();
        }
        return Response.ok(label).build();
    }

    @Path("/list")
    @GET
    public Response listLabel() {
        List<Taglabel> list = new ArrayList<Taglabel>();
        try {
             list = new GitlabService().listLabels(new Config());
        } catch (Exception err) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(err.toString()).build();
        }
        return Response.ok(list).build();
    }

    @Path("/listFiltered")
    @POST
    public Response listFilteredLabel(List<Taglabel> list) {
        try {
             list = new GitlabService().listFilteredLabels(new Config(), list);
        } catch (Exception err) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(err.toString()).build();
        }
        return Response.ok(list).build();
    }
}
