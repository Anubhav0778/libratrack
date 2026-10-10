package com.libratrack.dao;

import com.libratrack.model.Book;

import java.util.List;
import java.util.Locale;

/** In-memory storage of books. */
public class BookDao extends InMemoryDao<Book> {

    @Override
    protected long idOf(Book book) {
        return book.getId();
    }

    public List<Book> search(String query) {
        if (query == null || query.isBlank()) {
            return findAll();
        }
        String q = query.toLowerCase(Locale.ROOT).trim();
        return findAll().stream()
                .filter(b -> b.getTitle().toLowerCase(Locale.ROOT).contains(q)
                        || b.getAuthor().toLowerCase(Locale.ROOT).contains(q)
                        || b.getCategory().name().toLowerCase(Locale.ROOT).contains(q))
                .toList();
    }
}
