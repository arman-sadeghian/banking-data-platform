package com.sadeghian.banking.domain.model.transaction;

import com.sadeghian.banking.domain.exception.DomainException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.*;

class MoneyTest {

    @Test
    void should_create_money_when_amount_is_positive() {
        Money money = Money.of(BigDecimal.valueOf(1000), "IRR");

        assertThat(money.amount()).isEqualByComparingTo("1000");
        assertThat(money.currency().getCurrencyCode()).isEqualTo("IRR");
    }

    @Test
    void should_throw_exception_when_amount_is_zero_or_negative() {
        assertThatThrownBy(() ->
                Money.of(BigDecimal.ZERO, "IRR")
        ).isInstanceOf(DomainException.class);
    }
}
