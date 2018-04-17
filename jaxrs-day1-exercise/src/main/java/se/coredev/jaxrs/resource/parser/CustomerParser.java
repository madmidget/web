package se.coredev.jaxrs.resource.parser;

import se.coredev.jaxrs.model.Customer;

public final class CustomerParser {

    private CustomerParser() {
    }

    public static Customer fromTextString(String text) {
        String[] parts = text.split(",");
        Long id = Long.parseLong(parts[0]);
        String firstName = parts[1];
        String lastName = parts[2];
        String number = parts[3];

        return new Customer(id, firstName, lastName, number);
    }

    public static String toTextString(Customer customer) {
        return String.format("%s, %s, %s, %s", customer.getId(), customer.getFirstName(),
                customer.getLastName(), customer.getNumber());
    }
}
