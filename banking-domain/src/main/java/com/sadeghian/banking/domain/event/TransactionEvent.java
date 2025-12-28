package com.sadeghian.banking.domain.event;

import com.sadeghian.banking.domain.exception.DomainException;
import com.sadeghian.banking.domain.model.transaction.Transaction;

import java.time.Instant;
import java.util.Objects;

public record TransactionEvent(
        String eventId,
        EventType type,
        Transaction transaction,
        Instant eventTime,
        String sourceSystem
) {
    public enum EventType { CREATED, FAILED, REVERSED }

    public TransactionEvent {
        if (eventId == null || eventId.isBlank()) throw new DomainException("EventId must not be blank");
        Objects.requireNonNull(type, "EventType must not be null");
        Objects.requireNonNull(transaction, "Transaction must not be null");
        Objects.requireNonNull(eventTime, "EventTime must not be null");
        if (sourceSystem == null || sourceSystem.isBlank()) throw new DomainException("SourceSystem must not be blank");
    }
}
