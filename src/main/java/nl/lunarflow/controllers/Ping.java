package nl.lunarflow.controllers;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/ping")
public class Ping {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String ping() {
        return "\"pong\"";
    }
}
