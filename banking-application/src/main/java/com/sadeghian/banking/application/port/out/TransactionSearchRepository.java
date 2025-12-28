package com.sadeghian.banking.application.port.out;

import com.sadeghian.banking.domain.model.transaction.Transaction;

public interface TransactionSearchRepository {
    void index(Transaction transaction);
}
