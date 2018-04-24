package se.coredev.jaxrs.resource;

import org.springframework.stereotype.Component;
import se.coredev.jaxrs.model.Customer;
import se.coredev.jaxrs.resource.filter.ApiKey;
import se.coredev.jaxrs.service.CustomerService;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.stream.Collectors;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.*;

@Component
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
@Path("customers")
public final class CustomerResource {

    @Context
    private UriInfo uriInfo;

    @Context
    private HttpHeaders headers;

    private final CustomerService service;

    public CustomerResource(CustomerService service) {
        this.service = service;
        // Adds dummy data
        for (int i = 0; i < 10; i++) {
            service.createCustomer(new Customer(0L, "Luke", "Skywalker", String.valueOf(i + 1000)));
        }
    }

    @POST
    @ApiKey
    public Response createCustomer(Customer customer) {
        Customer result = service.createCustomer(customer);
        return Response.status(CREATED).header("Location", "customers/" + result.getId()).build();
    }


    @GET
    public Response getAll(@QueryParam("limit") @DefaultValue("5") int limit,
                           @QueryParam("sort") @DefaultValue("asc") String sort,
                           @QueryParam("links") @DefaultValue("false") boolean links) {
        List<Customer> customers = service.getAllCustomers(limit, sort.equals("desc"));

        if (links) {
            List<String> customerLinks = customers.stream().map(c ->
                    uriInfo.getAbsolutePathBuilder()
                            .path(c.getId().toString())
                            .build().toString())
                    .collect(Collectors.toList());

            return Response.ok(customerLinks).build();
        }

        return Response.ok(customers).build();
    }

    @GET
    @Path("{id}")
    public Response getCustomer(@PathParam("id") Long id) {
        return service.getCustomer(id)
                .map(Response::ok)
                .orElse(Response.status(NOT_FOUND))
                .build();
    }

    @ApiKey
    @PUT
    @Path("{id}")
    public void updateCustomer(@PathParam("id") Long id, Customer customer) {
        // if (id == customer.getId()) ... else BAD_REQUEST
        service.updateCustomer(customer);
    }

    @ApiKey
    @DELETE
    @Path("{id}")
    public Response deleteCustomer(@PathParam("id") Long id) {
        return service.deleteCustomer(id)
                .map(c -> Response.status(NO_CONTENT))
                .orElse(Response.status(NOT_FOUND))
                .build();
    }

}
