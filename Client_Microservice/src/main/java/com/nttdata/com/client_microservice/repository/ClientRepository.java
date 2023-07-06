package com.nttdata.com.client_microservice.repository;

import com.nttdata.com.client_microservice.model.Client;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ClientRepository extends ReactiveMongoRepository<Client, String> {
}
