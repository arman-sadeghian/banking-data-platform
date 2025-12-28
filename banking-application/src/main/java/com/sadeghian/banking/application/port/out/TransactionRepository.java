package com.sadeghian.banking.application.port.out;

import com.sadeghian.banking.domain.model.transaction.Transaction;

public interface TransactionRepository {
    void save(Transaction transaction);
}
