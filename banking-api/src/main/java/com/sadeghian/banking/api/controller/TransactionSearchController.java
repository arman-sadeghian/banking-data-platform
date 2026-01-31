package com.sadeghian.banking.api.controller;

import com.sadeghian.banking.application.dto.TransactionSearchResult;
import com.sadeghian.banking.application.port.in.SearchTransactionsUseCase;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@Tag(name = "Transactions", description = "Customer transaction operations")
public class TransactionSearchController {

    private final SearchTransactionsUseCase useCase;

    public TransactionSearchController(SearchTransactionsUseCase useCase) {
        this.useCase = useCase;
    }

    @GetMapping
    @Operation(
            summary = "Search transactions by customer",
            description = "Returns list of transactions filtered by customer id"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Customer not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "500", description = "Internal error")
    })
    public List<TransactionSearchResult> searchByCustomer(
            @RequestParam @Parameter(description = "Customer identifier", example = "123")
            String customerId
    ) {
        return useCase.searchByCustomerId(customerId);
    }
}

