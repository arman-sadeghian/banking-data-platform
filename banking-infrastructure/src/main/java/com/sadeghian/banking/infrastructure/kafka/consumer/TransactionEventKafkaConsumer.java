package com.sadeghian.banking.infrastructure.kafka.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sadeghian.banking.application.port.in.ProcessTransactionEventUseCase;
import com.sadeghian.banking.infrastructure.kafka.dto.TransactionEventMessage;
import com.sadeghian.banking.infrastructure.kafka.mapper.TransactionEventMapper;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.Clock;

@Component
public class TransactionEventKafkaConsumer {

    private final ObjectMapper objectMapper;
    private final ProcessTransactionEventUseCase useCase;
    private final Clock clock;

    public TransactionEventKafkaConsumer(
            ObjectMapper objectMapper,
            ProcessTransactionEventUseCase useCase,
            Clock clock
    ) {
        this.objectMapper = objectMapper;
        this.useCase = useCase;
        this.clock = clock;
    }

    @KafkaListener(
            topics = "banking.transaction.events",
            groupId = "banking-transaction-processor"
    )
    public void consume(ConsumerRecord<String, String> record) {

        try {
            TransactionEventMessage message =
                    objectMapper.readValue(
                            record.value(),
                            TransactionEventMessage.class
                    );

            var domainEvent =
                    TransactionEventMapper.toDomain(message, clock);

            useCase.process(domainEvent);

        } catch (Exception ex) {
            throw new RuntimeException("Kafka processing failed", ex);
        }
    }
}
