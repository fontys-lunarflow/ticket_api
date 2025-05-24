package nl.lunarflow.controllers;

import java.util.*;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import nl.lunarflow.models.*;
import nl.lunarflow.GitlabService;

public class LabelController {
    @Path("/api/label/create")
    @POST
    public Response newLabel(Taglabel label) {
        try {
            label = new GitlabService().createLabel(new Config(), label);
        } catch (Exception err) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(err.toString()).build();
        }
        return Response.ok(label).build();
    }

    @Path("/api/label/delete")
    @POST
    public Response delLabel(Taglabel label) {
        try {
            label = new GitlabService().deleteLabel(new Config(), label);
        } catch (Exception err) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(err.toString()).build();
        }
        return Response.ok(label).build();
    }

    @Path("/api/label/list")
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

    @Path("/api/label/listFiltered")
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
