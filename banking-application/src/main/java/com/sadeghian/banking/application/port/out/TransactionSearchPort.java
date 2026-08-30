package com.sadeghian.banking.application.port.out;

import com.sadeghian.banking.application.dto.TransactionSearchResult;

import java.util.List;

public interface TransactionSearchPort {

    List<TransactionSearchResult> searchByCustomerId(String customerId);
}