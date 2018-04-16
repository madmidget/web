package se.coredev.web.repository;

import se.coredev.web.model.Quote;

public interface QuoteRepository {

    Quote add(Quote quote);

    Quote get(Long id);

    Quote update(Quote quote);

    Quote delete(Long id);
}
