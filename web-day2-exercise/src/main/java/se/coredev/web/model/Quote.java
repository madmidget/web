package se.coredev.web.model;

public final class Quote {

    private final Long id;
    private final String text;

    public Quote(Long id, String text) {
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
