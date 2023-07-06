package com.nttdata.com.client_microservice.repository;

import com.nttdata.com.client_microservice.model.Account;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface AccountRepository extends ReactiveMongoRepository<Account, String> {
    Flux<Account> findByClientId(String clientId);
}
