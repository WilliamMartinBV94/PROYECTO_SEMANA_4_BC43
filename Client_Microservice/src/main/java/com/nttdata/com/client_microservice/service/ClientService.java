package com.nttdata.com.client_microservice.service;
import com.nttdata.com.client_microservice.model.Account;
import com.nttdata.com.client_microservice.model.Client;
import com.nttdata.com.client_microservice.model.CreditProduct;
import com.nttdata.com.client_microservice.model.Transaction;
import reactor.core.publisher.*;

public interface ClientService {
    Mono<Client> createClient(Client client);

    Mono<Client> getClient(String clientId);

    Flux<Client> getAllClients();

    Mono<Client> updateClient(String clientId, Client updatedClient);

    Mono<Void> deleteClient(String clientId);

    Flux<Account> getClientAccounts(String clientId);

    Flux<CreditProduct> getClientCreditProducts(String clientId);

    Mono<Double> getAccountBalance(String accountId);

    Mono<Void> deposit(String accountId, double amount);

    Mono<Void> withdraw(String accountId, double amount);

    Mono<Void> createCreditProduct(String clientId, CreditProduct creditProduct);

    Mono<Void> makePayment(String creditProductId, double amount);

    Mono<Double> getCreditProductBalance(String creditProductId);

    Mono<Void> associateDebitCard(String clientId, String accountId);

    Flux<Transaction> getDebitCardTransactions(String debitCardId);

    Mono<Double> getDebitCardBalance(String debitCardId);
}
