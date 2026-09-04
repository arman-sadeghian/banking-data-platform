package com.sadeghian.banking.application.usecase;

import com.sadeghian.banking.application.port.in.ProcessTransactionEventUseCase;
import com.sadeghian.banking.application.port.out.CustomerLookupPort;
import com.sadeghian.banking.application.port.out.TransactionRepository;
import com.sadeghian.banking.application.port.out.TransactionSearchRepository;
import com.sadeghian.banking.domain.event.TransactionEvent;
import com.sadeghian.banking.domain.model.transaction.Transaction;

import java.util.Objects;

public class ProcessTransactionEventService
        implements ProcessTransactionEventUseCase {

    private final TransactionRepository transactionRepository;
    private final TransactionSearchRepository searchRepository;
    private final CustomerLookupPort customerLookupPort;

    public ProcessTransactionEventService(
            TransactionRepository transactionRepository,
            TransactionSearchRepository searchRepository,
            CustomerLookupPort customerLookupPort
    ) {
        this.transactionRepository = Objects.requireNonNull(transactionRepository);
        this.searchRepository = Objects.requireNonNull(searchRepository);
        this.customerLookupPort = Objects.requireNonNull(customerLookupPort);
    }

    @Override
    public void process(TransactionEvent event) {

        Transaction transaction = event.transaction();

        transaction.markValidated();

        var customer =
                customerLookupPort.findByCustomerId(
                        transaction.customerId()
                );

        transaction.enrich(customer);

        transactionRepository.save(transaction);

        searchRepository.index(transaction);

        transaction.markIndexed();
    }
}