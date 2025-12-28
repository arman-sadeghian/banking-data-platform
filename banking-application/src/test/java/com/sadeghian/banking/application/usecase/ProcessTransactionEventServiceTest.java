package com.sadeghian.banking.application.usecase;

import com.sadeghian.banking.application.port.out.CustomerLookupPort;
import com.sadeghian.banking.application.port.out.TransactionRepository;
import com.sadeghian.banking.application.port.out.TransactionSearchRepository;
import com.sadeghian.banking.domain.event.TransactionEvent;
import com.sadeghian.banking.domain.model.customer.Customer;
import com.sadeghian.banking.domain.model.customer.CustomerType;
import com.sadeghian.banking.domain.model.customer.RiskLevel;
import com.sadeghian.banking.domain.model.transaction.*;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneOffset;

import static org.assertj.core.api.Assertions.assertThat;

class ProcessTransactionEventServiceTest {

    private final TransactionRepository repository = tx -> {};
    private final TransactionSearchRepository searchRepository = tx -> {};
    private final CustomerLookupPort customerLookup =
            id -> new Customer(id, CustomerType.INDIVIDUAL, RiskLevel.LOW);

    @Test
    void should_process_transaction_event_successfully() {
        var clock = Clock.fixed(
                Instant.parse("2025-01-01T10:00:00Z"),
                ZoneOffset.UTC
        );

        Transaction tx = new Transaction(
                "tx-100",
                "cust-100",
                "acc-100",
                Money.of(BigDecimal.valueOf(1_000_000), "IRR"),
                TransactionType.DEPOSIT,
                Channel.MOBILE,
                Instant.parse("2025-01-01T09:59:00Z"),
                clock
        );

        TransactionEvent event = new TransactionEvent(
                "evt-1",
                TransactionEvent.EventType.CREATED,
                tx,
                Instant.now(clock),
                "CORE_BANKING"
        );

        var useCase = new ProcessTransactionEventService(
                repository,
                searchRepository,
                customerLookup
        );

        useCase.process(event);

        assertThat(tx.status()).isEqualTo(TransactionStatus.INDEXED);
        assertThat(tx.enrichedCustomer()).isNotNull();
    }
}
