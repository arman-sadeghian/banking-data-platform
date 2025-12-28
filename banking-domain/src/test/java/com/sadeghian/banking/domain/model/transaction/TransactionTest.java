package com.sadeghian.banking.domain.model.transaction;

import com.sadeghian.banking.domain.exception.DomainException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneOffset;

import static org.assertj.core.api.Assertions.*;

class TransactionTest {

    private final Clock fixedClock =
            Clock.fixed(Instant.parse("2025-01-01T10:00:00Z"), ZoneOffset.UTC);

    @Test
    void should_transition_status_correctly() {
        Transaction tx = new Transaction(
                "tx-3",
                "cust-1",
                "acc-1",
                Money.of(BigDecimal.valueOf(100), "IRR"),
                TransactionType.WITHDRAW,
                Channel.ATM,
                Instant.parse("2025-01-01T09:58:00Z"),
                fixedClock
        );

        tx.markValidated();
        assertThat(tx.status()).isEqualTo(TransactionStatus.VALIDATED);

        tx.markIndexed();
        assertThat(tx.status()).isEqualTo(TransactionStatus.INDEXED);
    }

    
    @Test
    void should_create_transaction_when_time_is_valid() {
        Transaction tx = new Transaction(
                "tx-1",
                "cust-1",
                "acc-1",
                Money.of(BigDecimal.valueOf(500), "IRR"),
                TransactionType.DEPOSIT,
                Channel.MOBILE,
                Instant.parse("2025-01-01T09:59:00Z"),
                fixedClock
        );

        assertThat(tx.status()).isEqualTo(TransactionStatus.RECEIVED);
    }

    @Test
    void should_fail_when_transaction_time_is_in_future() {
        assertThatThrownBy(() ->
                new Transaction(
                        "tx-2",
                        "cust-1",
                        "acc-1",
                        Money.of(BigDecimal.valueOf(500), "IRR"),
                        TransactionType.DEPOSIT,
                        Channel.MOBILE,
                        Instant.parse("2025-01-01T10:10:00Z"),
                        fixedClock
                )
        ).isInstanceOf(DomainException.class);
    }
}
