package se.coredev.web.data;

public final class Message {

    private final Integer id;
    private final String content;

    public Message(Integer id, String content) {
        this.id = id;
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return String.format("Id:%s, Content:%s", id, content);
    }
}
