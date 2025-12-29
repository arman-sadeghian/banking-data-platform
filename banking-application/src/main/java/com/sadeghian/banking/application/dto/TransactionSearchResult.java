package com.sadeghian.banking.application.dto;

import java.math.BigDecimal;
import java.time.Instant;

public record TransactionSearchResult(
        String transactionId,
        String customerId,
        String accountId,
        BigDecimal amount,
        String currency,
        String transactionType,
        String channel,
        Instant transactionTime,
        String status
) {}
