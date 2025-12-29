package com.sadeghian.banking.infrastructure.elasticsearch.repository;

import com.sadeghian.banking.infrastructure.elasticsearch.document.TransactionDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface TransactionElasticsearchRepository
        extends ElasticsearchRepository<TransactionDocument, String> {

    List<TransactionDocument> findByCustomerId(String customerId);
}
