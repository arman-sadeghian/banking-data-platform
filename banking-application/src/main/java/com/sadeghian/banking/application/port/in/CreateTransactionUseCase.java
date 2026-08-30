package com.sadeghian.banking.application.port.in;

import com.sadeghian.banking.application.dto.CreateTransactionCommand;

public interface CreateTransactionUseCase {

    void create(CreateTransactionCommand command);
}