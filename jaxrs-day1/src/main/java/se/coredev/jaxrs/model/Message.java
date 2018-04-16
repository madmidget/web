package se.coredev.jaxrs.model;

public final class Message {

    private final Long id;
    private final String text;

    public Message(Long id, String text) {
        this.id = id;
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return String.format("Id:%s, Text:%s", id, text);
    }
}
