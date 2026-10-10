package com.libratrack.dao;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

/** Base class for the in-memory (HashMap-backed) data access objects. */
public abstract class InMemoryDao<T> {
    private final Map<Long, T> store = new LinkedHashMap<>();
    private final AtomicLong sequence = new AtomicLong(1);

    protected abstract long idOf(T entity);

    public long nextId() {
        return sequence.getAndIncrement();
    }

    public synchronized void save(T entity) {
        store.put(idOf(entity), entity);
    }

    public synchronized Optional<T> findById(long id) {
        return Optional.ofNullable(store.get(id));
    }

    public synchronized List<T> findAll() {
        return new ArrayList<>(store.values());
    }

    public synchronized void clear() {
        store.clear();
    }
}
