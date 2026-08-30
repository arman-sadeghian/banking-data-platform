package com.sadeghian.banking.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public class CreateTransactionRequest {

    @NotBlank(message = "transactionId must not be blank")
    private String transactionId;

    @NotBlank(message = "customerId must not be blank")
    private String customerId;

    @NotNull(message = "amount must not be null")
    @Positive(message = "amount must be greater than zero")
    private BigDecimal amount;

    @NotBlank(message = "currency must not be blank")
    private String currency;

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}