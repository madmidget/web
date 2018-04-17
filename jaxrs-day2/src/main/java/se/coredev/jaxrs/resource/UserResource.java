package se.coredev.jaxrs.resource;

import se.coredev.jaxrs.model.Role;
import se.coredev.jaxrs.model.User;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Arrays;

@Path("users")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class UserResource {

    @GET
    @Path("{id}")
    public User getUser(@PathParam("id") String id) {
        return new User(id, "master", "secret", Arrays.asList(new Role("Admin"), new Role("Domain-Admin")));
    }
}
