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

    public InMemoryTransactionSearchRepository() {
        System.out.println(
                ">>> SEARCH REPOSITORY CREATED: "
                        + System.identityHashCode(this)
        );
    }

    @Override
    public void index(Transaction transaction) {

        System.out.println(
                ">>> INDEX repository="
                        + System.identityHashCode(this)
                        + " transaction="
                        + transaction.transactionId()
        );

        transactions.add(transaction);

        System.out.println(
                ">>> INDEX SIZE = " + transactions.size()
        );
    }

    @Override
    public List<TransactionSearchResult> findByCustomerId(
            String customerId
    ) {

        System.out.println(
                ">>> SEARCH repository="
                        + System.identityHashCode(this)
                        + " customer="
                        + customerId
        );

        System.out.println(
                ">>> SEARCH SIZE BEFORE FILTER = "
                        + transactions.size()
        );

        transactions.forEach(transaction ->
                System.out.println(
                        ">>> STORED: "
                                + transaction.transactionId()
                                + " customer="
                                + transaction.customerId()
                                + " status="
                                + transaction.status()
                )
        );

        return transactions.stream()
                .filter(transaction ->
                        transaction.customerId()
                                .equals(customerId)
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