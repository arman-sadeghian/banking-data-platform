package com.sadeghian.banking.application.service;

import com.sadeghian.banking.application.dto.CreateTransactionCommand;
import com.sadeghian.banking.application.port.out.TransactionEventPublisher;
import com.sadeghian.banking.domain.event.TransactionEvent;
import org.junit.jupiter.api.Test;
import com.sadeghian.banking.application.usecase.CreateTransactionService;
import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneOffset;

import static org.junit.jupiter.api.Assertions.*;

class CreateTransactionServiceTest {

    @Test
    void shouldCreateAndPublishTransactionEvent() {

        Instant fixedTime = Instant.parse("2026-09-04T12:00:00Z");
        Clock clock = Clock.fixed(fixedTime, ZoneOffset.UTC);

        FakeTransactionEventPublisher publisher =
                new FakeTransactionEventPublisher();

        CreateTransactionService service =
                new CreateTransactionService(publisher, clock);

        CreateTransactionCommand command =
                new CreateTransactionCommand(
                        "TX-TEST-001",
                        "CUST-123",
                        "ACC-456",
                        new BigDecimal("2500000"),
                        "IRR",
                        "DEPOSIT",
                        "MOBILE",
                        Instant.parse("2026-09-04T11:00:00Z")
                );

        service.create(command);

        assertNotNull(publisher.publishedEvent);

        TransactionEvent event = publisher.publishedEvent;

        assertEquals("TX-TEST-001",
                event.transaction().transactionId());

        assertEquals("CUST-123",
                event.transaction().customerId());

        assertEquals("ACC-456",
                event.transaction().accountId());

        assertEquals(
                new BigDecimal("2500000"),
                event.transaction().money().amount()
        );

        assertEquals(
                "IRR",
                event.transaction().money().currency().getCurrencyCode()
        );

        assertEquals(
                TransactionEvent.EventType.CREATED,
                event.type()
        );

        assertEquals(
                fixedTime,
                event.eventTime()
        );

        assertEquals(
                "BANKING_API",
                event.sourceSystem()
        );
    }

    private static class FakeTransactionEventPublisher
            implements TransactionEventPublisher {

        private TransactionEvent publishedEvent;

        @Override
        public void publish(TransactionEvent event) {
            this.publishedEvent = event;
        }
    }
}