package com.nttdata.com.transaction_microservice.repository;

import com.nttdata.com.transaction_microservice.model.Transaction;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface TransactionsRepository extends ReactiveMongoRepository<Transaction, String> {
    Flux<Transaction> findByProductId(String productId);
    Flux<Transaction> findByCustomerId(String customerId);
}

