package com.sadeghian.banking.application.dto;

import java.math.BigDecimal;
import java.time.Instant;

public record CreateTransactionCommand(
        String transactionId,
        String customerId,
        String accountId,
        BigDecimal amount,
        String currency,
        String type,
        String channel,
        Instant transactionTime
) {
}