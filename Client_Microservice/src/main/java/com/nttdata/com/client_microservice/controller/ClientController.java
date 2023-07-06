package com.nttdata.com.client_microservice.controller;


import com.nttdata.com.client_microservice.model.Account;
import com.nttdata.com.client_microservice.model.Client;
import com.nttdata.com.client_microservice.model.CreditProduct;
import com.nttdata.com.client_microservice.model.Transaction;
import com.nttdata.com.client_microservice.service.ClientService;
import io.reactivex.rxjava3.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public Mono<ResponseEntity<Client>> createClient(@RequestBody Client client) {
        return clientService.createClient(client)
                .map(createdClient -> ResponseEntity.status(HttpStatus.CREATED).body(createdClient));
    }

    @GetMapping("/{clientId}")
    public Mono<ResponseEntity<Client>> getClient(@PathVariable String clientId) {
        return clientService.getClient(clientId)
                .map(client -> ResponseEntity.ok().body(client))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping
    public Flux<Client> getAllClients() {
        return clientService.getAllClients();
    }

    @PutMapping("/{clientId}")
    public Mono<ResponseEntity<Client>> updateClient(@PathVariable String clientId, @RequestBody Client updatedClient) {
        return clientService.updateClient(clientId, updatedClient)
                .map(client -> ResponseEntity.ok().body(client))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{clientId}")
    public Mono<ResponseEntity<Void>> deleteClient(@PathVariable String clientId) {
        return clientService.deleteClient(clientId)
                .then(Mono.just(ResponseEntity.noContent().<Void>build()))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("/{clientId}/accounts")
    public Flux<Account> getClientAccounts(@PathVariable String clientId) {
        return clientService.getClientAccounts(clientId);
    }

    @GetMapping("/{clientId}/credit-products")
    public Flux<CreditProduct> getClientCreditProducts(@PathVariable String clientId) {
        return clientService.getClientCreditProducts(clientId);
    }

    @GetMapping("/{accountId}/balance")
    public Mono<Double> getAccountBalance(@PathVariable String accountId) {
        return clientService.getAccountBalance(accountId);
    }

    @PostMapping("/{accountId}/deposit")
    public Mono<ResponseEntity<Void>> deposit(@PathVariable String accountId, @RequestParam double amount) {
        return clientService.deposit(accountId, amount)
                .then(Mono.just(ResponseEntity.ok().build()));
    }

    @PostMapping("/{accountId}/withdraw")
    public Mono<ResponseEntity<Void>> withdraw(@PathVariable String accountId, @RequestParam double amount) {
        return clientService.withdraw(accountId, amount)
                .then(Mono.just(ResponseEntity.ok().build()));
    }

    @PostMapping("/{clientId}/credit-products")
    public Mono<ResponseEntity<Void>> createCreditProduct(@PathVariable String clientId, @RequestBody CreditProduct creditProduct) {
        return clientService.createCreditProduct(clientId, creditProduct)
                .then(Mono.just(ResponseEntity.status(HttpStatus.CREATED).build()));
    }

    @PostMapping("/{creditProductId}/payment")
    public Mono<ResponseEntity<Void>> makePayment(@PathVariable String creditProductId, @RequestParam double amount) {
        return clientService.makePayment(creditProductId, amount)
                .then(Mono.just(ResponseEntity.ok().build()));
    }

    @GetMapping("/{creditProductId}/balance")
    public Mono<Double> getCreditProductBalance(@PathVariable String creditProductId) {
        return clientService.getCreditProductBalance(creditProductId);
    }

    @PostMapping("/{clientId}/associate-debit-card")
    public Mono<ResponseEntity<Void>> associateDebitCard(@PathVariable String clientId, @RequestParam String accountId) {
        return clientService.associateDebitCard(clientId, accountId)
                .then(Mono.just(ResponseEntity.status(HttpStatus.CREATED).build()));
    }

    @GetMapping("/{debitCardId}/transactions")
    public Flux<Transaction> getDebitCardTransactions(@PathVariable String debitCardId) {
        return clientService.getDebitCardTransactions(debitCardId);
    }

    @GetMapping("/{debitCardId}/balance")
    public Mono<Double> getDebitCardBalance(@PathVariable String debitCardId) {
        return clientService.getDebitCardBalance(debitCardId);
    }
}
