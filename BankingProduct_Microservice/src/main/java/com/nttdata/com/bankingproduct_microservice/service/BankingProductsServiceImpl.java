package com.nttdata.com.bankingproduct_microservice.service;

import com.nttdata.com.bankingproduct_microservice.model.BankingProduct;
import com.nttdata.com.bankingproduct_microservice.model.ProductType;
import com.nttdata.com.bankingproduct_microservice.repository.BankingProductsRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.Collections;

@Service
public class BankingProductsServiceImpl implements BankingProductsService {

    private final BankingProductsRepository bankingProductsRepository;

    public BankingProductsServiceImpl(BankingProductsRepository bankingProductsRepository) {
        this.bankingProductsRepository = bankingProductsRepository;
    }

    @Override
    public Mono<BankingProduct> createBankingProduct(BankingProduct bankingProduct) {
        return bankingProductsRepository.save(bankingProduct);
    }

    @Override
    public Mono<BankingProduct> getBankingProductById(String productId) {
        return bankingProductsRepository.findById(productId);
    }

    @Override
    public Flux<BankingProduct> getAllBankingProducts() {
        return bankingProductsRepository.findAll();
    }

    @Override
    public Mono<BankingProduct> updateBankingProduct(String productId, BankingProduct updatedProduct) {
        return bankingProductsRepository.findById(productId)
                .flatMap(existingProduct -> {
                    existingProduct.setName(updatedProduct.getName());
                    existingProduct.setType(updatedProduct.getType());
                    return bankingProductsRepository.save(existingProduct);
                });
    }

    @Override
    public Mono<Void> deleteBankingProduct(String productId) {
        return bankingProductsRepository.deleteById(productId);
    }

    @Override
    public Mono<BigDecimal> getAvailableBalance(String productId) {
        return (Mono<BigDecimal>) bankingProductsRepository.findById(productId)
                .map(BankingProduct::getBalance);
    }

    @Override
    public Flux<BankingProduct> getCustomerBankingProducts(String customerId) {
        return bankingProductsRepository.findByCustomerIdsContains(customerId);
    }

    @Override
    public Mono<BankingProduct> getCustomerBankingProduct(String customerId, String productId) {
        return bankingProductsRepository.findByCustomerIdsContainsAndId(customerId, productId);
    }

    @Override
    public Flux<BankingProduct> getCustomerCreditProducts(String customerId) {
        return bankingProductsRepository.findByCustomerIdsContainsAndType(customerId, ProductType.CREDIT);
    }

    @Override
    public Mono<BankingProduct> createCreditProduct(String customerId, BankingProduct creditProduct) {
        creditProduct.setType(ProductType.CREDIT.toString());
        return bankingProductsRepository.save(creditProduct);
    }

    @Override
    public Flux<BankingProduct> getCustomerDebitProducts(String customerId) {
        return bankingProductsRepository.findByCustomerIdsContainsAndType(customerId, ProductType.DEBIT);
    }

    @Override
    public Mono<BankingProduct> createDebitProduct(String customerId, BankingProduct debitProduct) {
        debitProduct.setType("DEBIT");
        return bankingProductsRepository.save(debitProduct);
    }

    @Override
    public Flux<BankingProduct> getCustomerSavingsAccounts(String customerId) {
        return bankingProductsRepository.findByCustomerIdsContainsAndType(customerId, ProductType.SAVINGS_ACCOUNT);
    }

    @Override
    public Mono<BankingProduct> createSavingsAccount(String customerId, BankingProduct savingsAccount) {
        savingsAccount.setType("SAVINGS_ACCOUNT");
        return bankingProductsRepository.save(savingsAccount);
    }

    @Override
    public Flux<BankingProduct> getCustomerCurrentAccounts(String customerId) {
        return bankingProductsRepository.findByCustomerIdsContainsAndType(customerId, ProductType.CURRENT_ACCOUNT);
    }

    @Override
    public Mono<BankingProduct> createCurrentAccount(String customerId, BankingProduct currentAccount) {
        currentAccount.setType(String.valueOf(ProductType.CURRENT_ACCOUNT));
        return bankingProductsRepository.save(currentAccount);
    }

    @Override
    public Flux<BankingProduct> getCustomerFixedTermAccounts(String customerId) {
        return bankingProductsRepository.findByCustomerIdsContainsAndType(customerId, ProductType.FIXED_TERM_ACCOUNT);
    }

    @Override
    public Mono<BankingProduct> createFixedTermAccount(String customerId, BankingProduct fixedTermAccount) {
        fixedTermAccount.setType(String.valueOf(ProductType.FIXED_TERM_ACCOUNT));
        return bankingProductsRepository.save(fixedTermAccount);
    }
}
