package se.coredev.jaxrs.resource;

import org.springframework.stereotype.Component;
import se.coredev.jaxrs.model.Customer;
import se.coredev.jaxrs.service.CustomerService;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.CREATED;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;
import static javax.ws.rs.core.Response.Status.NO_CONTENT;

@Component
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
@Path("customers")
public final class CustomerResource {

    private final CustomerService service;

    public CustomerResource(CustomerService service) {
        this.service = service;
    }

    @POST
    public Response createCustomer(Customer customer) {
        Customer result = service.createCustomer(customer);
        return Response.status(CREATED).header("Location", "customers/" + result.getId()).build();
    }

    @GET
    public List<Customer> getAll(@QueryParam("limit") @DefaultValue("5") int limit,
                                 @QueryParam("sort") @DefaultValue("asc") String sort) {
        return service.getAllCustomers(limit, sort.equals("desc"));
    }

    @GET
    @Path("{id}")
    public Response getCustomer(@PathParam("id") Long id) {
        return service.getCustomer(id)
                .map(Response::ok)
                .orElse(Response.status(NOT_FOUND))
                .build();
    }

    @PUT
    @Path("{id}")
    public void updateCustomer(@PathParam("id") Long id,  Customer customer) {
        // if (id == customer.getId()) ... else BAD_REQUEST
        service.updateCustomer(customer);
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
