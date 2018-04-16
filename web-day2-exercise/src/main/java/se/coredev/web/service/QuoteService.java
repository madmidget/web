package se.coredev.web.service;

import org.springframework.stereotype.Service;
import se.coredev.web.model.Quote;
import se.coredev.web.repository.QuoteRepository;

import java.util.concurrent.atomic.AtomicLong;

@Service
public final class QuoteService {

    private final QuoteRepository repository;
    private final AtomicLong ids = new AtomicLong(1000);

    public QuoteService(QuoteRepository repository) {
        this.repository = repository;
    }

    public Quote addQuote(String text) {
        return repository.add(new Quote(ids.incrementAndGet(), text));
    }

    public Quote getQuote(Long id) {
        return repository.get(id);
    }

    public Quote updateQuote(Quote quote) {
        return repository.update(quote);
    }

    public Quote deleteQuote(Long id) {
        return repository.delete(id);
    }
}
