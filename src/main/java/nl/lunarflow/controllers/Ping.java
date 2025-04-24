package nl.lunarflow.controllers;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import nl.lunarflow.models.Status;

@Path("/ping")
public class Ping {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Status ping() {
        // A status object is nothing more than a wrapper object around Config.
        return new Status();
    }
}
