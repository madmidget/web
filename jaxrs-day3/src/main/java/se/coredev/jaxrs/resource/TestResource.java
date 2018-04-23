package se.coredev.jaxrs.resource;

import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.UriInfo;

@Component
@Path("tests")
public final class TestResource {

    @Context
    private HttpHeaders headers;

    @Context
    private UriInfo uriInfo;

    @GET
    @Path("builder")
    public String testUriBuilder() {
        return new StringBuilder().append("Path:").append(uriInfo.getPath())
                .append("\nAbsoulute Path:").append(uriInfo.getAbsolutePath())
                .append("\nBaseUri:").append(uriInfo.getBaseUri())
                .append("\nRequestUri:").append(uriInfo.getRequestUri())
                .append("\nPath segments:").append(uriInfo.getPathSegments())
                .toString();
    }

    @GET
    @Path("parameters")
    public String testParameters() {
        StringBuilder result = new StringBuilder();
        uriInfo.getQueryParameters().forEach((key, value) ->
                result.append("Name:").append(key).append(" Value:").append(value)
        );
        return result.toString();
    }

    @GET
    @Path("headers")
    public String testHeader(@QueryParam("header-name") String name) {
        return String.format("Value for header %s = %s", name, headers.getRequestHeader(name));
    }
}
