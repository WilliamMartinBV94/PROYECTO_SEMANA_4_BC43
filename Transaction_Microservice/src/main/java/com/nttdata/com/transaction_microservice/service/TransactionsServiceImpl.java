package com.nttdata.com.transaction_microservice.service;

import com.nttdata.com.transaction_microservice.model.Transaction;
import com.nttdata.com.transaction_microservice.repository.TransactionsRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.*;

@Service
public class TransactionsServiceImpl implements TransactionsService {

    private final TransactionsRepository transactionsRepository;

    public TransactionsServiceImpl(TransactionsRepository transactionsRepository) {
        this.transactionsRepository = transactionsRepository;
    }

    @Override
    public Flux<Transaction> getAllTransactions() {
        return transactionsRepository.findAll();
    }

    @Override
    public Mono<Transaction> getTransactionById(String transactionId) {
        return transactionsRepository.findById(transactionId);
    }

    @Override
    public Flux<Transaction> getTransactionsByProductId(String productId) {
        return transactionsRepository.findByProductId(productId);
    }

    @Override
    public Flux<Transaction> getTransactionsByCustomerId(String customerId) {
        return transactionsRepository.findByCustomerId(customerId);
    }

    @Override
    public Mono<Transaction> createTransaction(Transaction transaction) {
        return transactionsRepository.save(transaction);
    }

    @Override
    public Mono<Void> deleteTransaction(String transactionId) {
        return transactionsRepository.deleteById(transactionId);
    }
}

