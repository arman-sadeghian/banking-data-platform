package com.sadeghian.banking.domain.model.transaction;

import com.sadeghian.banking.domain.exception.DomainException;
import com.sadeghian.banking.domain.model.customer.Customer;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.Objects;

public final class Transaction {

    private static final Duration FUTURE_SKEW_TOLERANCE = Duration.ofMinutes(2);

    private final String transactionId;
    private final String customerId;
    private final String accountId;
    private final Money money;
    private final TransactionType type;
    private final Channel channel;
    private final Instant transactionTime;

    private TransactionStatus status;
    private Customer enrichedCustomer; // optional enrichment data

    public Transaction(
            String transactionId,
            String customerId,
            String accountId,
            Money money,
            TransactionType type,
            Channel channel,
            Instant transactionTime,
            Clock clock
    ) {
        if (transactionId == null || transactionId.isBlank()) throw new DomainException("TransactionId must not be blank");
        if (customerId == null || customerId.isBlank()) throw new DomainException("CustomerId must not be blank");
        if (accountId == null || accountId.isBlank()) throw new DomainException("AccountId must not be blank");

        this.transactionId = transactionId;
        this.customerId = customerId;
        this.accountId = accountId;
        this.money = Objects.requireNonNull(money, "Money must not be null");
        this.type = Objects.requireNonNull(type, "TransactionType must not be null");
        this.channel = Objects.requireNonNull(channel, "Channel must not be null");

        if (transactionTime == null) throw new DomainException("TransactionTime must not be null");

        Instant now = Instant.now(Objects.requireNonNull(clock, "Clock must not be null"));
        if (transactionTime.isAfter(now.plus(FUTURE_SKEW_TOLERANCE))) {
            throw new DomainException("TransactionTime must not be in the future");
        }

        this.transactionTime = transactionTime;
        this.status = TransactionStatus.RECEIVED;
    }

    public void markValidated() {
        if (status == TransactionStatus.FAILED) throw new DomainException("Cannot validate a failed transaction");
        this.status = TransactionStatus.VALIDATED;
    }

    public void enrich(Customer customer) {
        this.enrichedCustomer = Objects.requireNonNull(customer, "Customer must not be null");
        this.status = TransactionStatus.ENRICHED;
    }

    public void markIndexed() {
        this.status = TransactionStatus.INDEXED;
    }

    public void markFailed(String reason) {
        this.status = TransactionStatus.FAILED;
    }

    public String transactionId() { return transactionId; }
    public String customerId() { return customerId; }
    public String accountId() { return accountId; }
    public Money money() { return money; }
    public TransactionType type() { return type; }
    public Channel channel() { return channel; }
    public Instant transactionTime() { return transactionTime; }
    public TransactionStatus status() { return status; }
    public Customer enrichedCustomer() { return enrichedCustomer; }
}
