package se.coredev.jaxrs.resource.mapper;

import se.coredev.jaxrs.model.BadUserInputException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.Collections;

import static javax.ws.rs.core.Response.Status.BAD_REQUEST;


@Provider
public class BadUserInputMapper implements ExceptionMapper<BadUserInputException> {

    @Override
    public Response toResponse(BadUserInputException exception) {
        return Response.status(BAD_REQUEST)
                .entity(Collections.singletonMap("error", exception.getMessage()))
                .build();
    }
}
