package se.coredev.jaxrs.repository;

import se.coredev.jaxrs.model.Customer;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public interface CustomerRepository {

    Customer add(Customer customer);

    Optional<Customer> get(Long id);

    Stream<Customer> getAll(int limit);

    Customer update(Customer customer);

    Optional<Customer> delete(Long id);
}
