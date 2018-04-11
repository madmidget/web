package se.coredev.web.jpa.repository;

import org.springframework.data.repository.CrudRepository;
import se.coredev.web.jpa.data.Message;

public interface MessageRepository extends CrudRepository<Message, Long> {
}
