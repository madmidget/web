package se.coredev.web.jpa.service;

import org.springframework.stereotype.Service;
import se.coredev.web.jpa.data.Message;
import se.coredev.web.jpa.repository.MessageRepository;

import java.util.Optional;

@Service
public final class MessageService {

    private final MessageRepository repository;

    public MessageService(MessageRepository repository) {
        this.repository = repository;
    }

    public Optional<Message> getMessage(Long id) {
        return repository.findById(id);
    }

    public Message addMessage(String text) {
        return repository.save(new Message(text));
    }


}
