package se.coredev.jaxrs.repository;

import se.coredev.jaxrs.model.Customer;

import java.util.Optional;

public interface CustomerRepository {

    Customer add(Customer customer);

    Optional<Customer> get(Long id);

    Customer update(Customer customer);

    Optional<Customer> delete(Long id);
}
