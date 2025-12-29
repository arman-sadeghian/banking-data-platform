package com.sadeghian.banking.api.controller;

import com.sadeghian.banking.application.dto.TransactionSearchResult;
import com.sadeghian.banking.application.port.in.SearchTransactionsUseCase;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionSearchController {

    private final SearchTransactionsUseCase useCase;

    public TransactionSearchController(SearchTransactionsUseCase useCase) {
        this.useCase = useCase;
    }

    @GetMapping
    public List<TransactionSearchResult> searchByCustomer(
            @RequestParam String customerId
    ) {
        return useCase.searchByCustomerId(customerId);
    }
}
