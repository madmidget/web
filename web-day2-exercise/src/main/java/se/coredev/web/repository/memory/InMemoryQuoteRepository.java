package se.coredev.web.repository.memory;

import org.springframework.stereotype.Repository;
import se.coredev.web.model.Quote;
import se.coredev.web.repository.QuoteRepository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public final class InMemoryQuoteRepository implements QuoteRepository {

    private final Map<Long, Quote> quotes = new ConcurrentHashMap<>();

    @Override
    public Quote add(Quote quote) {
        quotes.put(quote.getId(), quote);
        return quote;
    }

    @Override
    public Quote get(Long id) {
        return quotes.get(id);
    }

    @Override
    public Quote update(Quote quote) {
        quotes.put(quote.getId(), quote);
        return quote;
    }

    @Override
    public Quote delete(Long id) {
        return quotes.remove(id);
    }
}
