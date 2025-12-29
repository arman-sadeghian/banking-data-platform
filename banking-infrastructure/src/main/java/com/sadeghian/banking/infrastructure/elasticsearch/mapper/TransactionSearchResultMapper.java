package com.sadeghian.banking.infrastructure.elasticsearch.mapper;

import com.sadeghian.banking.application.dto.TransactionSearchResult;
import com.sadeghian.banking.infrastructure.elasticsearch.document.TransactionDocument;

public class TransactionSearchResultMapper {

    public static TransactionSearchResult toDto(TransactionDocument doc) {
        return new TransactionSearchResult(
                doc.getTransactionId(),
                doc.getCustomerId(),
                doc.getAccountId(),
                doc.getAmount(),
                doc.getCurrency(),
                doc.getTransactionType(),
                doc.getChannel(),
                doc.getTransactionTime(),
                doc.getStatus()
        );
    }
}
