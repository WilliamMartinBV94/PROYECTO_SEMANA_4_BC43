package com.nttdata.com.yankiwallet_microservice.repository;

import com.nttdata.com.yankiwallet_microservice.model.YankiWallet;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface YankiWalletRepository extends ReactiveCrudRepository<YankiWallet, String> {
    Mono<YankiWallet> findByPhoneNumber(String phoneNumber);
    Mono<YankiWallet> findByDocumentId(String documentId);
}
