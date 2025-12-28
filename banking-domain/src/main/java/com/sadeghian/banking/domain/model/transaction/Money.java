package com.sadeghian.banking.domain.model.transaction;

import com.sadeghian.banking.domain.exception.DomainException;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Objects;

public record Money(BigDecimal amount, Currency currency) {

    public Money {
        if (amount == null) throw new DomainException("Amount must not be null");
        if (currency == null) throw new DomainException("Currency must not be null");
        if (amount.signum() <= 0) throw new DomainException("Amount must be positive");
    }

    public static Money of(BigDecimal amount, String currencyCode) {
        Objects.requireNonNull(currencyCode, "Currency code must not be null");
        return new Money(amount, Currency.getInstance(currencyCode));
    }
}
