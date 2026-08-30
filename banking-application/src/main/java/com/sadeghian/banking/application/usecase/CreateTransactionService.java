package com.sadeghian.banking.application.usecase;

import com.sadeghian.banking.application.dto.CreateTransactionCommand;
import com.sadeghian.banking.application.port.in.CreateTransactionUseCase;

public class CreateTransactionService implements CreateTransactionUseCase {

    @Override
    public void create(CreateTransactionCommand command) {

        System.out.println(
                "Processing transaction: transactionId=" + command.transactionId()
                        + ", customerId=" + command.customerId()
                        + ", amount=" + command.amount()
                        + ", currency=" + command.currency()
        );
    }
}