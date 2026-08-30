package com.sadeghian.banking.bootstrap.config;

import com.sadeghian.banking.application.port.in.CreateTransactionUseCase;
import com.sadeghian.banking.application.port.in.ProcessTransactionEventUseCase;
import com.sadeghian.banking.application.port.in.SearchTransactionsUseCase;
import com.sadeghian.banking.application.port.out.CustomerLookupPort;
import com.sadeghian.banking.application.port.out.TransactionEventPublisher;
import com.sadeghian.banking.application.port.out.TransactionRepository;
import com.sadeghian.banking.application.port.out.TransactionSearchRepository;
import com.sadeghian.banking.application.usecase.CreateTransactionService;
import com.sadeghian.banking.application.usecase.ProcessTransactionEventService;
import com.sadeghian.banking.application.usecase.SearchTransactionsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;
@Configuration
public class UseCaseConfiguration {

    @Bean
    public ProcessTransactionEventUseCase processTransactionEventUseCase(
            TransactionRepository transactionRepository,
            TransactionSearchRepository searchRepository,
            CustomerLookupPort customerLookupPort
    ) {
        return new ProcessTransactionEventService(
                transactionRepository,
                searchRepository,
                customerLookupPort
        );
    }

    @Bean
    public SearchTransactionsUseCase searchTransactionsUseCase(
            TransactionSearchRepository repository
    ) {
        return new SearchTransactionsService(repository);
    }

    @Bean
    public CreateTransactionUseCase createTransactionUseCase(
            TransactionEventPublisher eventPublisher,
            Clock clock
    ) {
        return new CreateTransactionService(
                eventPublisher,
                clock
        );
    }

    @Bean
    public Clock systemClock() {
        return Clock.systemUTC();
    }
}