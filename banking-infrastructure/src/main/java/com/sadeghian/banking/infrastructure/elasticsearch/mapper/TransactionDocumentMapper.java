package com.sadeghian.banking.infrastructure.elasticsearch.mapper;

import com.sadeghian.banking.domain.model.transaction.Transaction;
import com.sadeghian.banking.infrastructure.elasticsearch.document.TransactionDocument;

public class TransactionDocumentMapper {

    public static TransactionDocument toDocument(Transaction tx) {
        TransactionDocument doc = new TransactionDocument();

        doc.setTransactionId(tx.transactionId());
        doc.setCustomerId(tx.customerId());
        doc.setAccountId(tx.accountId());
        doc.setAmount(tx.money().amount());
        doc.setCurrency(tx.money().currency().getCurrencyCode());
        doc.setTransactionType(tx.type().name());
        doc.setChannel(tx.channel().name());
        doc.setTransactionTime(tx.transactionTime());
        doc.setStatus(tx.status().name());

        return doc;
    }
}
