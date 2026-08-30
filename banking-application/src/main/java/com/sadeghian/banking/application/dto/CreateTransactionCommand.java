package com.sadeghian.banking.application.dto;

import java.math.BigDecimal;

public record CreateTransactionCommand(
        String transactionId,
        String customerId,
        BigDecimal amount,
        String currency
) {
}