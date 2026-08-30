package com.sadeghian.banking.infrastructure.kafka.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sadeghian.banking.application.port.out.TransactionEventPublisher;
import com.sadeghian.banking.domain.event.TransactionEvent;
import com.sadeghian.banking.domain.model.transaction.Transaction;
import com.sadeghian.banking.infrastructure.kafka.dto.TransactionEventMessage;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaTransactionEventPublisher
        implements TransactionEventPublisher {

    private static final String TOPIC =
            "banking.transaction.events";

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public KafkaTransactionEventPublisher(
            KafkaTemplate<String, String> kafkaTemplate,
            ObjectMapper objectMapper
    ) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public void publish(TransactionEvent event) {

        try {

            Transaction transaction = event.transaction();

            TransactionEventMessage message =
                    new TransactionEventMessage(
                            event.eventId(),
                            event.type().name(),
                            transaction.transactionId(),
                            transaction.customerId(),
                            transaction.accountId(),
                            transaction.money().amount(),
                            transaction.money().currency().getCurrencyCode(),
                            transaction.type().name(),
                            transaction.channel().name(),
                            transaction.transactionTime(),
                            event.eventTime(),
                            event.sourceSystem()
                    );

            String payload =
                    objectMapper.writeValueAsString(message);

            kafkaTemplate.send(
                    TOPIC,
                    transaction.transactionId(),
                    payload
            );

        } catch (JsonProcessingException e) {

            throw new RuntimeException(
                    "Failed to serialize transaction event",
                    e
            );
        }
    }
}