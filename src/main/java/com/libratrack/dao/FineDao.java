package com.libratrack.dao;

import com.libratrack.model.Fine;

import java.util.List;

/** In-memory storage of fines. */
public class FineDao extends InMemoryDao<Fine> {

    @Override
    protected long idOf(Fine fine) {
        return fine.getId();
    }

    /** Assigns an id and stores the fine. */
    public void add(Fine fine) {
        fine.setId(nextId());
        save(fine);
    }

    public List<Fine> findByMember(long memberId) {
        return findAll().stream().filter(f -> f.getMemberId() == memberId).toList();
    }

    public double unpaidTotal(long memberId) {
        return findByMember(memberId).stream()
                .filter(f -> !f.isPaid())
                .mapToDouble(Fine::getNetAmount)
                .sum();
    }
}
