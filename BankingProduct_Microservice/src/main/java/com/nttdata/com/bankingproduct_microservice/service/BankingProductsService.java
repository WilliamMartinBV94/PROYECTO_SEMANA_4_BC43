package com.nttdata.com.bankingproduct_microservice.service;

import com.nttdata.com.bankingproduct_microservice.model.BankingProduct;
import reactor.core.publisher.*;

import java.math.BigDecimal;

public interface BankingProductsService {
    Mono<BankingProduct> createBankingProduct(BankingProduct bankingProduct);

    Mono<BankingProduct> getBankingProductById(String productId);

    Flux<BankingProduct> getAllBankingProducts();

    Mono<BankingProduct> updateBankingProduct(String productId, BankingProduct updatedProduct);

    Mono<Void> deleteBankingProduct(String productId);

    Mono<BigDecimal> getAvailableBalance(String productId);

    Flux<BankingProduct> getCustomerBankingProducts(String customerId);

    Mono<BankingProduct> getCustomerBankingProduct(String customerId, String productId);

    Flux<BankingProduct> getCustomerCreditProducts(String customerId);

    Mono<BankingProduct> createCreditProduct(String customerId, BankingProduct creditProduct);

    Flux<BankingProduct> getCustomerDebitProducts(String customerId);

    Mono<BankingProduct> createDebitProduct(String customerId, BankingProduct debitProduct);

    Flux<BankingProduct> getCustomerSavingsAccounts(String customerId);

    Mono<BankingProduct> createSavingsAccount(String customerId, BankingProduct savingsAccount);

    Flux<BankingProduct> getCustomerCurrentAccounts(String customerId);

    Mono<BankingProduct> createCurrentAccount(String customerId, BankingProduct currentAccount);

    Flux<BankingProduct> getCustomerFixedTermAccounts(String customerId);

    Mono<BankingProduct> createFixedTermAccount(String customerId, BankingProduct fixedTermAccount);


}
