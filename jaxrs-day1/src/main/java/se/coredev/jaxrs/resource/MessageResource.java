package se.coredev.jaxrs.resource;

import se.coredev.jaxrs.model.Message;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import static javax.ws.rs.core.Response.Status.*;

@Path("messages")
public class MessageResource {

    private static final Map<Long, Message> messages = new ConcurrentHashMap<>();
    private static final AtomicLong ids = new AtomicLong(1000);

    // CREATE
    @POST
    public Response createMessage(String text) {
        Long id = ids.incrementAndGet();
        Message message = new Message(id, text);
        messages.put(id, message);

        return Response.status(CREATED).header("Location", "messages/" + id).build();
    }

    // READ
    @GET
    @Path("{id}")
    public Response getMessage(@PathParam("id") Long id) {
        Message message = messages.get(id);

        if(message == null) {
            return Response.status(NOT_FOUND).build();
        }
        return Response.ok(message.toString()).build();
    }

    // UPDATE
    @PUT
    @Path("{id}")
    public Response updateMessage(@PathParam("id") Long id, String text) {
        if(messages.containsKey(id)) {
            messages.put(id, new Message(id, text));
            return Response.status(NO_CONTENT).build();
        }
        return Response.status(NOT_FOUND).build();
    }

    // DELETE
    @DELETE
    @Path("{id}")
    public Response deleteMessage(@PathParam("id") Long id){
        if(messages.containsKey(id)){
            messages.remove(id);
            return Response.status(NO_CONTENT).build();
        }
        return Response.status(NOT_FOUND).build();
    }
}