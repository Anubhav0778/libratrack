package com.libratrack.dao;

import com.libratrack.model.Transaction;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/** In-memory storage of issue/return transactions. */
public class TransactionDao extends InMemoryDao<Transaction> {

    @Override
    protected long idOf(Transaction transaction) {
        return transaction.getId();
    }

    public List<Transaction> findActive() {
        return findAll().stream().filter(t -> !t.isReturned()).toList();
    }

    public List<Transaction> findActiveByMember(long memberId) {
        return findActive().stream().filter(t -> t.getMemberId() == memberId).toList();
    }

    public Optional<Transaction> findActiveByMemberAndBook(long memberId, long bookId) {
        return findActiveByMember(memberId).stream().filter(t -> t.getBookId() == bookId).findFirst();
    }

    public List<Transaction> findOverdue(LocalDate today) {
        return findActive().stream().filter(t -> t.getDueDate().isBefore(today)).toList();
    }
}
