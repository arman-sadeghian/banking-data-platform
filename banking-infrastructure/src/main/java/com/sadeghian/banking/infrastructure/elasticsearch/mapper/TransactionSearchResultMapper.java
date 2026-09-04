package com.sadeghian.banking.infrastructure.elasticsearch.mapper;

import com.sadeghian.banking.application.dto.TransactionSearchResult;
import com.sadeghian.banking.infrastructure.elasticsearch.document.TransactionDocument;
import java.time.Instant;

public class TransactionSearchResultMapper {

    public static TransactionSearchResult toDto(TransactionDocument document) {
        return new TransactionSearchResult(
                document.getTransactionId(),
                document.getCustomerId(),
                document.getAccountId(),
                document.getAmount(),
                document.getCurrency(),
                document.getTransactionType(),
                document.getChannel(),
                Instant.ofEpochMilli(document.getTransactionTime()),
                document.getStatus()
        );
    }
}
