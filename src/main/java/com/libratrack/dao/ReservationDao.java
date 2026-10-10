package com.libratrack.dao;

import com.libratrack.model.Reservation;

import java.util.List;

/** In-memory storage of reservations. */
public class ReservationDao extends InMemoryDao<Reservation> {

    @Override
    protected long idOf(Reservation reservation) {
        return reservation.getId();
    }

    public List<Reservation> findActiveByBook(long bookId) {
        return findAll().stream().filter(r -> r.isActive() && r.getBookId() == bookId).toList();
    }

    public List<Reservation> findActive() {
        return findAll().stream().filter(Reservation::isActive).toList();
    }
}
