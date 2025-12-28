package com.sadeghian.banking.application.port.in;

import com.sadeghian.banking.domain.event.TransactionEvent;

public interface ProcessTransactionEventUseCase {
    void process(TransactionEvent event);
}
