package com.sadeghian.banking.application.port.out;

import com.sadeghian.banking.application.dto.TransactionSearchResult;

import java.util.List;

public interface TransactionSearchRepository {

    void index(com.sadeghian.banking.domain.model.transaction.Transaction transaction);

    List<TransactionSearchResult> findByCustomerId(String customerId);
}
