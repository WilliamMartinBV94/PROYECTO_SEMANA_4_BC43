package com.nttdata.com.client_microservice.repository;

import com.nttdata.com.client_microservice.model.Account;
import com.nttdata.com.client_microservice.model.CreditProduct;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface CreditProductRepository extends ReactiveMongoRepository<CreditProduct, String> {
    Flux<CreditProduct> findByClientId(String clientId);
}
