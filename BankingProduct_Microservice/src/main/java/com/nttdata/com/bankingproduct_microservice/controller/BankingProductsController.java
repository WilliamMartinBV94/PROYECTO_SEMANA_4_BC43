package com.nttdata.com.bankingproduct_microservice.controller;

import com.nttdata.com.bankingproduct_microservice.model.BankingProduct;
import com.nttdata.com.bankingproduct_microservice.service.BankingProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@RestController
@RequestMapping("/banking-products")
public class BankingProductsController {

    private final BankingProductsService bankingProductsService;

    public BankingProductsController(BankingProductsService bankingProductsService) {
        this.bankingProductsService = bankingProductsService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<BankingProduct> createBankingProduct(@RequestBody BankingProduct bankingProduct) {
        return bankingProductsService.createBankingProduct(bankingProduct);
    }

    @GetMapping("/{productId}")
    public Mono<BankingProduct> getBankingProductById(@PathVariable String productId) {
        return bankingProductsService.getBankingProductById(productId);
    }

    @GetMapping
    public Flux<BankingProduct> getAllBankingProducts() {
        return bankingProductsService.getAllBankingProducts();
    }

    @PutMapping("/{productId}")
    public Mono<BankingProduct> updateBankingProduct(@PathVariable String productId, @RequestBody BankingProduct updatedProduct) {
        return bankingProductsService.updateBankingProduct(productId, updatedProduct);
    }

    @DeleteMapping("/{productId}")
    public Mono<Void> deleteBankingProduct(@PathVariable String productId) {
        return bankingProductsService.deleteBankingProduct(productId);
    }

    @GetMapping("/{productId}/available-balance")
    public Mono<BigDecimal> getAvailableBalance(@PathVariable String productId) {
        return bankingProductsService.getAvailableBalance(productId);
    }

    @GetMapping("/customer/{customerId}")
    public Flux<BankingProduct> getCustomerBankingProducts(@PathVariable String customerId) {
        return bankingProductsService.getCustomerBankingProducts(customerId);
    }

    @GetMapping("/customer/{customerId}/{productId}")
    public Mono<BankingProduct> getCustomerBankingProduct(@PathVariable String customerId, @PathVariable String productId) {
        return bankingProductsService.getCustomerBankingProduct(customerId, productId);
    }

    @GetMapping("/customer/{customerId}/credit")
    public Flux<BankingProduct> getCustomerCreditProducts(@PathVariable String customerId) {
        return bankingProductsService.getCustomerCreditProducts(customerId);
    }

    @PostMapping("/customer/{customerId}/credit")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<BankingProduct> createCreditProduct(@PathVariable String customerId, @RequestBody BankingProduct creditProduct) {
        return bankingProductsService.createCreditProduct(customerId, creditProduct);
    }

    @GetMapping("/customer/{customerId}/debit")
    public Flux<BankingProduct> getCustomerDebitProducts(@PathVariable String customerId) {
        return bankingProductsService.getCustomerDebitProducts(customerId);
    }

    @PostMapping("/customer/{customerId}/debit")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<BankingProduct> createDebitProduct(@PathVariable String customerId, @RequestBody BankingProduct debitProduct) {
        return bankingProductsService.createDebitProduct(customerId, debitProduct);
    }

    @GetMapping("/customer/{customerId}/savings")
    public Flux<BankingProduct> getCustomerSavingsAccounts(@PathVariable String customerId) {
        return bankingProductsService.getCustomerSavingsAccounts(customerId);
    }

    @PostMapping("/customer/{customerId}/savings")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<BankingProduct> createSavingsAccount(@PathVariable String customerId, @RequestBody BankingProduct savingsAccount) {
        return bankingProductsService.createSavingsAccount(customerId, savingsAccount);
    }

    @GetMapping("/customer/{customerId}/current")
    public Flux<BankingProduct> getCustomerCurrentAccounts(@PathVariable String customerId) {
        return bankingProductsService.getCustomerCurrentAccounts(customerId);
    }

    @PostMapping("/customer/{customerId}/current")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<BankingProduct> createCurrentAccount(@PathVariable String customerId, @RequestBody BankingProduct currentAccount) {
        return bankingProductsService.createCurrentAccount(customerId, currentAccount);
    }

    @GetMapping("/customer/{customerId}/fixed-term")
    public Flux<BankingProduct> getCustomerFixedTermAccounts(@PathVariable String customerId) {
        return bankingProductsService.getCustomerFixedTermAccounts(customerId);
    }

    @PostMapping("/customer/{customerId}/fixed-term")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<BankingProduct> createFixedTermAccount(@PathVariable String customerId, @RequestBody BankingProduct fixedTermAccount) {
        return bankingProductsService.createFixedTermAccount(customerId, fixedTermAccount);
    }
}