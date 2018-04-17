package se.coredev.jaxrs.resource;

import org.springframework.stereotype.Component;
import se.coredev.jaxrs.model.Customer;
import se.coredev.jaxrs.service.CustomerService;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.Status.*;
import static se.coredev.jaxrs.resource.parser.CustomerParser.fromTextString;
import static se.coredev.jaxrs.resource.parser.CustomerParser.toTextString;

@Path("customers")
@Component
public final class CustomerResource {

    private final CustomerService service;

    public CustomerResource(CustomerService service) {
        this.service = service;
    }

    @POST
    public Response createCustomer(String text) {
        Customer customer = service.createCustomer(fromTextString(text));
        return Response.status(CREATED).header("Location", "customers/" + customer.getId()).build();
    }

    @GET
    @Path("{id}")
    public Response getCustomer(@PathParam("id") Long id) {
        return service.getCustomer(id)
                .map(c -> Response.ok(toTextString(c)))
                .orElse(Response.status(NOT_FOUND))
                .build();

//        Optional<Customer> customer = service.getCustomer(id);
//
//        if(customer.isPresent()) {
//            return Response.ok(toTextString(customer.get())).build();
//        }
//        return Response.status(NOT_FOUND).build();
    }

    @PUT
    @Path("{id}")
    public Response updateCustomer(@PathParam("id") Long id, String text) {
        service.updateCustomer(fromTextString(text));
        return Response.status(NO_CONTENT).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteCustomer(@PathParam("id") Long id) {
        return service.deleteCustomer(id)
                .map(c -> Response.status(NO_CONTENT))
                .orElse(Response.status(NOT_FOUND))
                .build();
    }
}
