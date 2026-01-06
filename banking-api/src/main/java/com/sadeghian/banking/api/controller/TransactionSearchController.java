package com.sadeghian.banking.api.controller;

import com.sadeghian.banking.application.dto.TransactionSearchResult;
import com.sadeghian.banking.application.port.in.SearchTransactionsUseCase;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@Tag(name = "Transactions", description = "Search banking transactions")
public class TransactionSearchController {

    private final SearchTransactionsUseCase useCase;

    public TransactionSearchController(SearchTransactionsUseCase useCase) {
        this.useCase = useCase;
    }

    @GetMapping
    @Operation(
            summary = "Search transactions by customer id",
            description = "Returns indexed transactions from Elasticsearch for a given customer"
    )
    public List<TransactionSearchResult> searchByCustomer(
            @RequestParam @Parameter(description = "Customer identifier", example = "123")
            String customerId
    ) {
        return useCase.searchByCustomerId(customerId);
    }
}

