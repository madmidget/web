package se.coredev.jaxrs.resource;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("messages")
public final class MessageResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getAsText(){
        return "Hello!";
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public String getAsHtml() {
        return "<b>Hello!</b>";
    }

    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    public void addPlainText(String text) {
        System.out.println(text);
    }

    @POST
    @Consumes(MediaType.TEXT_HTML)
    public void addHtmlText(String text) {
        System.out.println(text);
    }
}
