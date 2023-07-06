package com.nttdata.com.transaction_microservice.service;

import com.nttdata.com.transaction_microservice.model.Transaction;
import reactor.core.publisher.*;

public interface TransactionsService {
    Flux<Transaction> getAllTransactions();
    Mono<Transaction> getTransactionById(String transactionId);
    Flux<Transaction> getTransactionsByProductId(String productId);
    Flux<Transaction> getTransactionsByCustomerId(String customerId);
    Mono<Transaction> createTransaction(Transaction transaction);
    Mono<Void> deleteTransaction(String transactionId);
}

