package com.sadeghian.banking.infrastructure.elasticsearch.document;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Document(indexName = "banking-transactions")
public class TransactionDocument {

    @Id
    private String transactionId;
    private String customerId;
    private String accountId;
    private BigDecimal amount;
    private String currency;
    private String transactionType;
    private String channel;
    private Instant transactionTime;
    private String status;

}
