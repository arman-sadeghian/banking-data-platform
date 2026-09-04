package com.sadeghian.banking.infrastructure.kafka.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sadeghian.banking.application.port.in.ProcessTransactionEventUseCase;
import com.sadeghian.banking.domain.event.TransactionEvent;
import com.sadeghian.banking.domain.model.transaction.Channel;
import com.sadeghian.banking.domain.model.transaction.Money;
import com.sadeghian.banking.domain.model.transaction.Transaction;
import com.sadeghian.banking.domain.model.transaction.TransactionType;
import com.sadeghian.banking.infrastructure.kafka.dto.TransactionEventMessage;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.util.Currency;

@Component
public class TransactionEventConsumer {

    private final ObjectMapper objectMapper;
    private final ProcessTransactionEventUseCase processTransactionEventUseCase;
    private final Clock clock;

    public TransactionEventConsumer(
            ObjectMapper objectMapper,
            ProcessTransactionEventUseCase processTransactionEventUseCase,
            Clock clock
    ) {
        this.objectMapper = objectMapper;
        this.processTransactionEventUseCase = processTransactionEventUseCase;
        this.clock = clock;
    }

    @KafkaListener(
            topics = "banking.transaction.events",
            groupId = "banking-transaction-processor"
    )
    public void consume(String payload) {
        try {
            TransactionEventMessage message =
                    objectMapper.readValue(
                            payload,
                            TransactionEventMessage.class
                    );

            Money money = new Money(
                    message.amount(),
                    Currency.getInstance(
                            message.currency().toUpperCase()
                    )
            );

            Transaction transaction =
                    new Transaction(
                            message.transactionId(),
                            message.customerId(),
                            message.accountId(),
                            money,
                            TransactionType.valueOf(
                                    message.transactionType().toUpperCase()
                            ),
                            Channel.valueOf(
                                    message.channel().toUpperCase()
                            ),
                            message.transactionTime(),
                            clock
                    );

            TransactionEvent event =
                    new TransactionEvent(
                            message.eventId(),
                            TransactionEvent.EventType.valueOf(
                                    message.eventType().toUpperCase()
                            ),
                            transaction,
                            message.eventTime(),
                            message.sourceSystem()
                    );

            processTransactionEventUseCase.process(event);

        } catch (Exception e) {
            throw new RuntimeException(
                    "Failed to process transaction event",
                    e
            );
        }
    }
}