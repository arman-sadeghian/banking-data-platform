package com.sadeghian.banking.infrastructure.elasticsearch.repository;

import com.sadeghian.banking.infrastructure.elasticsearch.document.TransactionDocument;
import org.springframework.context.annotation.Profile;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

@Profile("!local")
public interface TransactionElasticsearchRepository
        extends ElasticsearchRepository<TransactionDocument, String> {

    List<TransactionDocument> findByCustomerId(String customerId);
}
