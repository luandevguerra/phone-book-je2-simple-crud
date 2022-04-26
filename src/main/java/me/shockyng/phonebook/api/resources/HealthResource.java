package me.shockyng.phonebook.api.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/health")
public class HealthResource {

    @GET
    public String getHealth() {
        return "Everything is working well!";
    }
}
