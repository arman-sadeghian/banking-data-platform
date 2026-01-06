package com.sadeghian.banking.infrastructure.elasticsearch.adapter;

import com.sadeghian.banking.application.dto.TransactionSearchResult;
import com.sadeghian.banking.application.port.out.TransactionSearchRepository;
import com.sadeghian.banking.domain.model.transaction.Transaction;
import com.sadeghian.banking.infrastructure.elasticsearch.mapper.TransactionDocumentMapper;
import com.sadeghian.banking.infrastructure.elasticsearch.mapper.TransactionSearchResultMapper;
import com.sadeghian.banking.infrastructure.elasticsearch.repository.TransactionElasticsearchRepository;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Profile("!local")
public class ElasticsearchTransactionSearchAdapter
        implements TransactionSearchRepository {

    private final TransactionElasticsearchRepository repository;

    public ElasticsearchTransactionSearchAdapter(TransactionElasticsearchRepository repository) {
        this.repository = repository;
    }

    @Override
    public void index(Transaction transaction) {
        repository.save(TransactionDocumentMapper.toDocument(transaction));
    }

    @Override
    public List<TransactionSearchResult> findByCustomerId(String customerId) {
        return repository.findByCustomerId(customerId)
                .stream()
                .map(TransactionSearchResultMapper::toDto)
                .toList();
    }
}
