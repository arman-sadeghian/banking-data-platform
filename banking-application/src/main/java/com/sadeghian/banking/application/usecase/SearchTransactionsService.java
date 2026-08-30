package com.sadeghian.banking.application.usecase;

import com.sadeghian.banking.application.dto.TransactionSearchResult;
import com.sadeghian.banking.application.port.in.SearchTransactionsUseCase;
import com.sadeghian.banking.application.port.out.TransactionSearchRepository;
import com.sadeghian.banking.domain.exception.ResourceNotFoundException;

import java.util.List;

public class SearchTransactionsService implements SearchTransactionsUseCase {

    private final TransactionSearchRepository repository;

    public SearchTransactionsService(TransactionSearchRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<TransactionSearchResult> searchByCustomerId(String customerId) {

        List<TransactionSearchResult> results =
                repository.findByCustomerId(customerId);

        if (results == null || results.isEmpty()) {
            throw new ResourceNotFoundException(
                    "No transactions found for customer: " + customerId
            );
        }

        return results;
    }
}