package com.sadeghian.banking.application.port.in;

import com.sadeghian.banking.application.dto.TransactionSearchResult;

import java.util.List;

public interface SearchTransactionsUseCase {

    List<TransactionSearchResult> searchByCustomerId(String customerId);
}
