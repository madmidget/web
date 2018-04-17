package se.coredev.jaxrs.model;

public final class Customer {

    private final Long id;
    private final String firstName;
    private final String lastName;
    private final String number;

    public Customer(Long id, String firstName, String lastName, String number) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.number = number;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getNumber() {
        return number;
    }
}

