package com.nttdata.com.bankingproduct_microservice.repository;

import com.nttdata.com.bankingproduct_microservice.model.BankingProduct;
import com.nttdata.com.bankingproduct_microservice.model.ProductType;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.*;

@Repository
public interface BankingProductsRepository extends ReactiveMongoRepository<BankingProduct, String> {
    Flux<BankingProduct> findByCustomerIdsContains(String customerId);
    Mono<BankingProduct> findByCustomerIdsContainsAndId(String customerId, String productId);
    Flux<BankingProduct> findByCustomerIdsContainsAndType(String customerId, ProductType type);
}
