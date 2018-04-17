package se.coredev.jaxrs.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import se.coredev.jaxrs.model.Customer;
import se.coredev.jaxrs.repository.CustomerRepository;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public final class CustomerService {

    private final CustomerRepository repository;
    private static final AtomicLong ids = new AtomicLong(1000);

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public Customer createCustomer(Customer customer) {
        return repository.add(new Customer(ids.incrementAndGet(),
                customer.getFirstName(), customer.getLastName(), customer.getNumber()));
    }

    public Optional<Customer> getCustomer(Long id) {
        return repository.get(id);
    }

    public Customer updateCustomer(Customer customer) {
        return repository.update(customer);
    }

    public Optional<Customer> deleteCustomer(Long id) {
        return repository.delete(id);
    }

}
