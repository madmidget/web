package se.coredev.jaxrs.resource.mapper;

import se.coredev.jaxrs.service.InvalidCustomerException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import static java.util.Collections.singletonMap;
import static javax.ws.rs.core.Response.Status.BAD_REQUEST;

@Provider
public final class InvalidCustomerMapper implements ExceptionMapper<InvalidCustomerException> {

    @Override
    public Response toResponse(InvalidCustomerException exception) {
        return Response.status(BAD_REQUEST).entity(singletonMap("error", exception.getMessage())).build();
    }
}
