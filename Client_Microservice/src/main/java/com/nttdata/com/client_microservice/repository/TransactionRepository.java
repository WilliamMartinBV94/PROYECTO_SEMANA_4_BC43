package com.nttdata.com.client_microservice.repository;

import com.nttdata.com.client_microservice.model.Transaction;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface TransactionRepository extends ReactiveMongoRepository<Transaction, String> {
    Flux<Transaction> findByDebitCardId(String debitCardId);
}
