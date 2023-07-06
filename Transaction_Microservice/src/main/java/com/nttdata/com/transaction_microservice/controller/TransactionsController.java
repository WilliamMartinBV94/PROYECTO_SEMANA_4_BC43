package com.nttdata.com.transaction_microservice.controller;

import com.nttdata.com.transaction_microservice.model.Transaction;
import com.nttdata.com.transaction_microservice.service.TransactionsService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.*;

@RestController
@RequestMapping("/transactions")
public class TransactionsController {

    private final TransactionsService transactionsService;

    public TransactionsController(TransactionsService transactionsService) {
        this.transactionsService = transactionsService;
    }

    @GetMapping
    public Flux<Transaction> getAllTransactions() {
        return transactionsService.getAllTransactions();
    }

    @GetMapping("/{transactionId}")
    public Mono<Transaction> getTransactionById(@PathVariable String transactionId) {
        return transactionsService.getTransactionById(transactionId);
    }

    @GetMapping("/products/{productId}")
    public Flux<Transaction> getTransactionsByProductId(@PathVariable String productId) {
        return transactionsService.getTransactionsByProductId(productId);
    }

    @GetMapping("/customers/{customerId}")
    public Flux<Transaction> getTransactionsByCustomerId(@PathVariable String customerId) {
        return transactionsService.getTransactionsByCustomerId(customerId);
    }

    @PostMapping
    public Mono<Transaction> createTransaction(@RequestBody Transaction transaction) {
        return transactionsService.createTransaction(transaction);
    }

    @DeleteMapping("/{transactionId}")
    public Mono<Void> deleteTransaction(@PathVariable String transactionId) {
        return transactionsService.deleteTransaction(transactionId);
    }
}

