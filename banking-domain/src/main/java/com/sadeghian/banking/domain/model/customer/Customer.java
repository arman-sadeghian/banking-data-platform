package com.sadeghian.banking.domain.model.customer;

import com.sadeghian.banking.domain.exception.DomainException;

import java.util.Objects;

public final class Customer {
    private final String customerId;
    private final CustomerType type;
    private final RiskLevel riskLevel;

    public Customer(String customerId, CustomerType type, RiskLevel riskLevel) {
        if (customerId == null || customerId.isBlank()) throw new DomainException("CustomerId must not be blank");
        this.customerId = customerId;
        this.type = Objects.requireNonNull(type, "CustomerType must not be null");
        this.riskLevel = Objects.requireNonNull(riskLevel, "RiskLevel must not be null");
    }

    public String customerId() { return customerId; }
    public CustomerType type() { return type; }
    public RiskLevel riskLevel() { return riskLevel; }
}
