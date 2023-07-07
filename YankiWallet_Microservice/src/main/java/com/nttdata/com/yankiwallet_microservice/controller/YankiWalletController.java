package com.nttdata.com.yankiwallet_microservice.controller;

import com.nttdata.com.yankiwallet_microservice.model.YankiWallet;
import com.nttdata.com.yankiwallet_microservice.service.YankiWalletService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/wallets")
public class YankiWalletController {

    private final YankiWalletService walletService;

    public YankiWalletController(YankiWalletService walletService) {
        this.walletService = walletService;
    }

    @PostMapping
    public Mono<YankiWallet> createWallet(@RequestBody YankiWallet wallet) {
        return walletService.createWallet(wallet);
    }

    @GetMapping("/{walletId}")
    public Mono<YankiWallet> getWalletById(@PathVariable String walletId) {
        return walletService.getWalletById(walletId);
    }

    @PutMapping
    public Mono<YankiWallet> updateWallet(@RequestBody YankiWallet wallet) {
        return walletService.updateWallet(wallet);
    }

    @DeleteMapping("/{walletId}")
    public Mono<Void> deleteWallet(@PathVariable String walletId) {
        return walletService.deleteWallet(walletId);
    }

    @PostMapping("/{walletId}/associate-card")
    public Mono<YankiWallet> associateCard(@PathVariable String walletId, @RequestParam String cardId) {
        return walletService.associateCard(walletId, cardId);
    }

    @PostMapping("/{walletId}/remove-card-association")
    public Mono<YankiWallet> removeCardAssociation(@PathVariable String walletId, @RequestParam String cardId) {
        return walletService.removeCardAssociation(walletId, cardId);
    }
}
