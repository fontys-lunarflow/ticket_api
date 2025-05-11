package nl.lunarflow.controllers;

import java.util.*;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import nl.lunarflow.models.Config;
import nl.lunarflow.models.Label;
import nl.lunarflow.services.GitlabService;

public class LabelController {
    @Path("/api/label/new")
    @POST
    public Response newLabel(Label label) {
        try {
            label = Handler.newLabel(new Config(), label, new GitlabService());
        } catch (Exception err) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(err.toString()).build();
        }
        return Response.ok(label).build();
    }

    @Path("/api/label/delete")
    @POST
    public Response delLabel(Label label) {
        try {
            label = Handler.delLabel(new Config(), label, new GitlabService());
        } catch (Exception err) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(err.toString()).build();
        }
        return Response.ok(label).build();
    }

    @Path("/api/label/list")
    @GET
    public Response listLabel() {
        List<Label> list = new ArrayList<Label>();
        try {
             list = Handler.listLabel(new Config(), new GitlabService());
        } catch (Exception err) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(err.toString()).build();
        }
        return Response.ok(list).build();
    }
}
