package com.sadeghian.banking.api.controller;

import com.sadeghian.banking.api.dto.CreateTransactionRequest;
import com.sadeghian.banking.application.dto.TransactionSearchResult;
import com.sadeghian.banking.application.port.in.SearchTransactionsUseCase;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/transactions")
@Tag(name = "Transactions", description = "Customer transaction operations")
public class TransactionSearchController {

    private final SearchTransactionsUseCase useCase;

    public TransactionSearchController(SearchTransactionsUseCase useCase) {
        this.useCase = useCase;
    }


    @PostMapping
    @Operation(
            summary = "Create transaction",
            description = "Accepts a new banking transaction"
    )
    public ResponseEntity<CreateTransactionRequest> createTransaction(
            @Valid @RequestBody CreateTransactionRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(request);
    }

    @GetMapping
    @Operation(
            summary = "Search transactions by customer",
            description = "Returns list of transactions filtered by customer id"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Customer not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public List<TransactionSearchResult> searchByCustomer(
            @RequestParam(name = "customerId")
            @Parameter(description = "Customer identifier", example = "123")
            @NotBlank(message = "customerId must not be blank")
            String customerId
    ) {
        return useCase.searchByCustomerId(customerId);
    }
}

