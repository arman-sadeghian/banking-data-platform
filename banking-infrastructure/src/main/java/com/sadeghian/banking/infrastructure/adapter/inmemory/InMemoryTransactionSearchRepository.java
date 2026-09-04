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
public class InMemoryTransactionSearchRepository
        implements TransactionSearchRepository {

    private final List<Transaction> transactions =
            new CopyOnWriteArrayList<>();

    @Override
    public void index(Transaction transaction) {
        transactions.add(transaction);
    }

    @Override
    public List<TransactionSearchResult> findByCustomerId(
            String customerId
    ) {
        return transactions.stream()
                .filter(transaction ->
                        transaction.customerId().equals(customerId)
                )
                .map(this::toSearchResult)
                .toList();
    }

    private TransactionSearchResult toSearchResult(
            Transaction transaction
    ) {
        return new TransactionSearchResult(
                transaction.transactionId(),
                transaction.customerId(),
                transaction.accountId(),
                transaction.money().amount(),
                transaction.money()
                        .currency()
                        .getCurrencyCode(),
                transaction.type().name(),
                transaction.channel().name(),
                transaction.transactionTime(),
                transaction.status().name()
        );
    }
}