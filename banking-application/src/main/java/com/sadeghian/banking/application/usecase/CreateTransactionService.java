package com.sadeghian.banking.application.usecase;

import com.sadeghian.banking.application.dto.CreateTransactionCommand;
import com.sadeghian.banking.application.port.in.CreateTransactionUseCase;
import com.sadeghian.banking.application.port.out.TransactionEventPublisher;
import com.sadeghian.banking.domain.event.TransactionEvent;
import com.sadeghian.banking.domain.model.transaction.Channel;
import com.sadeghian.banking.domain.model.transaction.Money;
import com.sadeghian.banking.domain.model.transaction.Transaction;
import com.sadeghian.banking.domain.model.transaction.TransactionType;
import java.util.Currency;
import java.time.Clock;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

public class CreateTransactionService
        implements CreateTransactionUseCase {

    private final TransactionEventPublisher eventPublisher;
    private final Clock clock;

    public CreateTransactionService(
            TransactionEventPublisher eventPublisher,
            Clock clock
    ) {
        this.eventPublisher =
                Objects.requireNonNull(eventPublisher);

        this.clock =
                Objects.requireNonNull(clock);
    }

    @Override
    public void create(CreateTransactionCommand command) {

        Money money = new Money(
                command.amount(),
                Currency.getInstance(command.currency().toUpperCase())
        );

        Transaction transaction =
                new Transaction(
                        command.transactionId(),
                        command.customerId(),
                        command.accountId(),
                        money,
                        TransactionType.valueOf(
                                command.type().toUpperCase()
                        ),
                        Channel.valueOf(
                                command.channel().toUpperCase()
                        ),
                        command.transactionTime(),
                        clock
                );

        TransactionEvent event =
                new TransactionEvent(
                        UUID.randomUUID().toString(),
                        TransactionEvent.EventType.CREATED,
                        transaction,
                        Instant.now(clock),
                        "BANKING_API"
                );

        eventPublisher.publish(event);
    }
}