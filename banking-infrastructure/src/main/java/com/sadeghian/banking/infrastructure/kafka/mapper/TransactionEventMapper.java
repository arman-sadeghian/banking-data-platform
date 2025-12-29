package com.sadeghian.banking.infrastructure.kafka.mapper;

import com.sadeghian.banking.domain.event.TransactionEvent;
import com.sadeghian.banking.domain.model.transaction.*;

import java.time.Clock;

public class TransactionEventMapper {

    public static TransactionEvent toDomain(
            com.sadeghian.banking.infrastructure.kafka.dto.TransactionEventMessage msg,
            Clock clock
    ) {
        Transaction tx = new Transaction(
                msg.transactionId(),
                msg.customerId(),
                msg.accountId(),
                Money.of(msg.amount(), msg.currency()),
                TransactionType.valueOf(msg.transactionType()),
                Channel.valueOf(msg.channel()),
                msg.transactionTime(),
                clock
        );

        return new TransactionEvent(
                msg.eventId(),
                TransactionEvent.EventType.valueOf(msg.eventType()),
                tx,
                msg.transactionTime(),
                msg.sourceSystem()
        );
    }
}
