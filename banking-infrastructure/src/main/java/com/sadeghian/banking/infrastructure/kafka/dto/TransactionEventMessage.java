package com.sadeghian.banking.infrastructure.kafka.dto;

import java.math.BigDecimal;
import java.time.Instant;

public record TransactionEventMessage(
        String eventId,
        String eventType,
        String transactionId,
        String customerId,
        String accountId,
        BigDecimal amount,
        String currency,
        String transactionType,
        String channel,
        Instant transactionTime,
        String sourceSystem
) {}
