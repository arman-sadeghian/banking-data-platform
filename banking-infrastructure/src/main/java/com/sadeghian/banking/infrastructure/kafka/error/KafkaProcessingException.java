package com.sadeghian.banking.infrastructure.kafka.error;

public class KafkaProcessingException extends RuntimeException {

    public KafkaProcessingException(String message, Throwable cause) {
        super(message, cause);
    }
}
