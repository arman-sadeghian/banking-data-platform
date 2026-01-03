package com.sadeghian.banking.application.usecase;

import com.sadeghian.banking.application.dto.TransactionSearchResult;
import com.sadeghian.banking.application.port.out.TransactionSearchRepository;
import com.sadeghian.banking.domain.model.transaction.Transaction;

import java.util.List;

class FakeTransactionSearchRepository implements TransactionSearchRepository {

    @Override
    public void index(Transaction transaction) {}

    @Override
    public List<TransactionSearchResult> findByCustomerId(String customerId) {
        return List.of();
    }
}

