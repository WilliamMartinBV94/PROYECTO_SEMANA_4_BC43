package com.nttdata.com.client_microservice.repository;

import com.nttdata.com.client_microservice.model.DebitCard;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface DebitCardRepository extends ReactiveMongoRepository<DebitCard, String> {
}
