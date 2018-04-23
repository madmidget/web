package se.coredev.jaxrs.repository.memory;

import org.springframework.stereotype.Repository;
import se.coredev.jaxrs.model.Customer;
import se.coredev.jaxrs.repository.CustomerRepository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

@Repository
public final class InMemoryCustomerRepository implements CustomerRepository {

    private final Map<Long, Customer> customers = new ConcurrentHashMap<>();

    @Override
    public Customer add(Customer customer) {
        customers.put(customer.getId(), customer);
        return customer;
    }

    @Override
    public Optional<Customer> get(Long id) {
        return Optional.ofNullable(customers.get(id));
    }

    @Override
    public Stream<Customer> getAll(int limit) {
        return customers.values().stream().limit(limit);
    }

    @Override
    public Customer update(Customer customer) {
        if (customers.containsKey(customer.getId())) {
            customers.put(customer.getId(), customer);
        }
        return customer;
    }

    @Override
    public Optional<Customer> delete(Long id) {
        return Optional.ofNullable(customers.remove(id));
    }
}
