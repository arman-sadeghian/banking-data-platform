package com.sadeghian.banking.application.usecase;

import com.sadeghian.banking.application.dto.TransactionSearchResult;
import com.sadeghian.banking.application.port.in.SearchTransactionsUseCase;
import com.sadeghian.banking.application.port.out.TransactionSearchRepository;

import java.util.List;
import java.util.Objects;

public class SearchTransactionsService implements SearchTransactionsUseCase {

    private final TransactionSearchRepository repository;

    public SearchTransactionsService(TransactionSearchRepository repository) {
        this.repository = Objects.requireNonNull(repository);
    }

    @Override
    public List<TransactionSearchResult> searchByCustomerId(String customerId) {
        return repository.findByCustomerId(customerId);
    }
}
