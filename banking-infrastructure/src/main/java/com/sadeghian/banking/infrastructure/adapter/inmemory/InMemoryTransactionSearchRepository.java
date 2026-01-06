package com.sadeghian.banking.infrastructure.adapter.inmemory;

import com.sadeghian.banking.application.dto.TransactionSearchResult;
import com.sadeghian.banking.application.port.out.TransactionSearchRepository;
import com.sadeghian.banking.domain.model.transaction.Transaction;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Repository
@Profile("local")
public class InMemoryTransactionSearchRepository implements TransactionSearchRepository {

    @Override
    public void index(Transaction transaction) {

    }

    @Override
    public List<TransactionSearchResult> findByCustomerId(String customerId) {
        return List.of();
    }
}
