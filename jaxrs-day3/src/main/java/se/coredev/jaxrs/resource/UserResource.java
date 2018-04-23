package se.coredev.jaxrs.resource;

import org.springframework.stereotype.Component;
import se.coredev.jaxrs.model.BadUserInputException;
import se.coredev.jaxrs.model.User;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Path("users")
@Produces(MediaType.APPLICATION_JSON)
@Component
public class UserResource {

    @Context
    private UriInfo uriInfo;

    private final Map<String, User> users = new ConcurrentHashMap<>();

    @POST
    public Response addUser(User user) {
        if(user.getUsername().contains("master")) {
            throw new BadUserInputException("Bad username");
        }

        User result = new User(UUID.randomUUID().toString(), user.getUsername(), user.getPassword());
        users.put(result.getId(), result);

        // 1
        URI location = uriInfo.getAbsolutePathBuilder().path(result.getId()).build();
        // 2
        // URI location = uriInfo.getAbsolutePathBuilder().path(UserResource.class, "getUser").build(result.getId());

        return Response.created(location).build();
    }

    @GET
    @Path("{id}")
    public User getUser(@PathParam("id") String id) {
        if (users.containsKey(id)) {
            return users.get(id);
        }
//        throw new WebApplicationException(NOT_FOUND);
        throw new NotFoundException();
    }

    // users/1001/
    // note/{noteId}
    //@POST
//    @Path("{id}")
//    public User addNote(Note note) {
//    }


}
