package nl.lunarflow.controllers;

import java.util.*;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import nl.lunarflow.models.Taglabel;
import nl.lunarflow.models.Config;
import nl.lunarflow.models.Form;
import nl.lunarflow.GitlabService;

@Path("/api/labels")
public class LabelController {

    @Path("/create")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response newLabel(Taglabel label) {
        try {
            label = new GitlabService().createLabel(new Config(), label);
        } catch (Exception err) {
            return Response.ok(new Form<Taglabel>(true, err.getMessage(), null)).build();
        }
        return Response.ok(new Form<Taglabel>(false, null, label)).build();
    }

    @Path("/delete")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response delLabel(Taglabel label) {
        try {
            label = new GitlabService().deleteLabel(new Config(), label);
        } catch (Exception err) {
            return Response.ok(new Form<Taglabel>(true, err.getMessage(), null)).build();
        }
        return Response.ok(new Form<Taglabel>(false, null, label)).build();
    }

    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listLabel() {
        List<Taglabel> list = new ArrayList<Taglabel>();
        try {
            list = new GitlabService().listLabels(new Config());
        } catch (Exception err) {
            return Response.ok(new Form<Taglabel>(true, err.getMessage(), null)).build();
        }
        return Response.ok(new Form<List<Taglabel>>(false, null, list)).build();
    }

    @Path("/list/filtered")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response listFilteredLabel(List<Taglabel> list) {
        try {
             list = new GitlabService().listFilteredLabels(new Config(), list);
        } catch (Exception err) {
            return Response.ok(new Form<Taglabel>(true, err.getMessage(), null)).build();
        }
        return Response.ok(new Form<List<Taglabel>>(false, null, list)).build();
    }
}
