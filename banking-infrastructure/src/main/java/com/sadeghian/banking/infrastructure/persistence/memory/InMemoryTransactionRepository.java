package com.sadeghian.banking.infrastructure.persistence.memory;

import com.sadeghian.banking.application.port.out.TransactionRepository;
import com.sadeghian.banking.domain.model.transaction.Transaction;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Profile({"local", "dev", "docker"})
public class InMemoryTransactionRepository implements TransactionRepository {

    private final List<Transaction> store = new ArrayList<>();

    @Override
    public void save(Transaction transaction) {
        store.add(transaction);
    }
}
