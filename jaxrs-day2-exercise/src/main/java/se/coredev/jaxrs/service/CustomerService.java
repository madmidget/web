package se.coredev.jaxrs.service;

import org.springframework.stereotype.Service;
import se.coredev.jaxrs.model.Customer;
import se.coredev.jaxrs.repository.CustomerRepository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import static java.util.stream.Collectors.toList;
import static se.coredev.jaxrs.model.Customer.*;

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

    public List<Customer> getAllCustomers(int limit, boolean sortDescending) {
        return repository.getAll(limit).sorted(
                sortDescending ? BY_CUSTOMER_ID_DESC : BY_CUSTOMER_ID_ASC
        ).collect(toList());
    }

    public Customer updateCustomer(Customer customer) {
        return repository.update(customer);
    }

    public Optional<Customer> deleteCustomer(Long id) {
        return repository.delete(id);
    }

}
