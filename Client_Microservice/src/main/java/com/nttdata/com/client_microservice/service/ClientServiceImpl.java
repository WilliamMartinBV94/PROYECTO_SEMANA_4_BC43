package com.nttdata.com.client_microservice.service;
import com.nttdata.com.client_microservice.exception.InsufficientBalanceException;
import com.nttdata.com.client_microservice.model.*;
import com.nttdata.com.client_microservice.repository.*;
import org.springframework.stereotype.Service;
import reactor.core.publisher.*;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final AccountRepository accountRepository;
    private final CreditProductRepository creditProductRepository;
    private final DebitCardRepository debitCardRepository;
    private final TransactionRepository transactionRepository;

    public ClientServiceImpl(ClientRepository clientRepository, AccountRepository accountRepository,
                             CreditProductRepository creditProductRepository, DebitCardRepository debitCardRepository,
                             TransactionRepository transactionRepository) {
        this.clientRepository = clientRepository;
        this.accountRepository = accountRepository;
        this.creditProductRepository = creditProductRepository;
        this.debitCardRepository = debitCardRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Mono<Client> createClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Mono<Client> getClient(String clientId) {
        return clientRepository.findById(clientId);
    }

    @Override
    public Flux<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public Mono<Client> updateClient(String clientId, Client updatedClient) {
        return clientRepository.findById(clientId)
                .flatMap(client -> {
                    client.setName(updatedClient.getName());
                    return clientRepository.save(client);
                });
    }

    @Override
    public Mono<Void> deleteClient(String clientId) {
        return clientRepository.deleteById(clientId);
    }

    @Override
    public Flux<Account> getClientAccounts(String clientId) {
        return accountRepository.findByClientId(clientId);
    }

    @Override
    public Flux<CreditProduct> getClientCreditProducts(String clientId) {
        return creditProductRepository.findByClientId(clientId);
    }

    @Override
    public Mono<Double> getAccountBalance(String accountId) {
        return accountRepository.findById(accountId)
                .map(Account::getBalance);
    }

    @Override
    public Mono<Void> deposit(String accountId, double amount) {
        return accountRepository.findById(accountId)
                .flatMap(account -> {
                    account.setBalance(account.getBalance() + amount);
                    return accountRepository.save(account);
                })
                .then();
    }

    @Override
    public Mono<Void> withdraw(String accountId, double amount) {
        return accountRepository.findById(accountId)
                .flatMap(account -> {
                    if (account.getBalance() >= amount) {
                        account.setBalance(account.getBalance() - amount);
                        return accountRepository.save(account);
                    } else {
                        return Mono.error(new InsufficientBalanceException("Insufficient balance in the account."));
                    }
                })
                .then();
    }

    @Override
    public Mono<Void> createCreditProduct(String clientId, CreditProduct creditProduct) {
        creditProduct.setClientId(clientId);
        return creditProductRepository.save(creditProduct)
                .then();
    }

    @Override
    public Mono<Void> makePayment(String creditProductId, double amount) {
        return creditProductRepository.findById(creditProductId)
                .flatMap(creditProduct -> {
                    if (creditProduct.getBalance() >= amount) {
                        creditProduct.setBalance(creditProduct.getBalance() - amount);
                        return creditProductRepository.save(creditProduct);
                    } else {
                        return Mono.error(new InsufficientBalanceException("Insufficient balance in the credit product."));
                    }
                })
                .then();
    }

    @Override
    public Mono<Double> getCreditProductBalance(String creditProductId) {
        return creditProductRepository.findById(creditProductId)
                .map(CreditProduct::getBalance);
    }

    @Override
    public Mono<Void> associateDebitCard(String clientId, String accountId) {
        return Mono.zip(
                        clientRepository.findById(clientId),
                        accountRepository.findById(accountId)
                )
                .flatMap(tuple -> {
                    Client client = tuple.getT1();
                    Account account = tuple.getT2();
                    DebitCard debitCard = new DebitCard(client.getId(), account.getId());
                    return debitCardRepository.save(debitCard);
                })
                .then();
    }

    @Override
    public Flux<Transaction> getDebitCardTransactions(String debitCardId) {
        return transactionRepository.findByDebitCardId(debitCardId);
    }

    @Override
    public Mono<Double> getDebitCardBalance(String debitCardId) {
        return debitCardRepository.findById(debitCardId)
                .map(DebitCard::getBalance);
    }
}

