package se.coredev.jaxrs.resource.filter;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

import static java.util.Collections.singletonMap;
import static javax.ws.rs.Priorities.*;
import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;
import static javax.ws.rs.core.Response.status;

@ApiKey
@Provider
@Priority(AUTHENTICATION)
public final class ApiKeyFilter implements ContainerRequestFilter {
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String apiKey = requestContext.getHeaderString("api-key");
        // if (!key.equals("secret")) { <-- NullPointerException!
        if (!"secret".equals(apiKey)) {
            requestContext.abortWith(status(UNAUTHORIZED)
                    .entity(singletonMap("error", "Missing/Invalid api key"))
                    .build());
        }
    }
}
