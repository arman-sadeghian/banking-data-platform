package com.sadeghian.banking.application.port.out;

import com.sadeghian.banking.domain.event.TransactionEvent;

public interface TransactionEventPublisher {
    void publish(TransactionEvent event);
}