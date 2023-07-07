package com.nttdata.com.yankiwallet_microservice.service;

import com.nttdata.com.yankiwallet_microservice.model.YankiWallet;
import com.nttdata.com.yankiwallet_microservice.repository.YankiWalletRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class YankiWalletServiceImpl implements YankiWalletService {

    private final YankiWalletRepository walletRepository;

    public YankiWalletServiceImpl(YankiWalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Override
    public Mono<YankiWallet> createWallet(YankiWallet wallet) {
        return walletRepository.save(wallet);
    }

    @Override
    public Mono<YankiWallet> getWalletById(String walletId) {
        return walletRepository.findById(walletId);
    }

    @Override
    public Mono<YankiWallet> updateWallet(YankiWallet wallet) {
        return walletRepository.save(wallet);
    }

    @Override
    public Mono<Void> deleteWallet(String walletId) {
        return walletRepository.deleteById(walletId);
    }

    @Override
    public Mono<YankiWallet> associateCard(String walletId, String cardId) {
        return walletRepository.findById(walletId)
                .flatMap(wallet -> {
                    wallet.setCardId(cardId);
                    return walletRepository.save(wallet);
                });
    }

    @Override
    public Mono<YankiWallet> removeCardAssociation(String walletId, String cardId) {
        return walletRepository.findById(walletId)
                .flatMap(wallet -> {
                    wallet.setCardId(null);
                    return walletRepository.save(wallet);
                });
    }

    /*@Override
    public Mono<Double> getWalletBalance(String walletId) {
        return walletRepository.findById(walletId)
                .map(YankiWallet::getBalance);
    }

    @Override
    public Mono<Void> depositToWallet(String walletId, double amount) {
        return walletRepository.findById(walletId)
                .flatMap(wallet -> {
                    wallet.setBalance(wallet.getBalance() + amount);
                    return walletRepository.save(wallet);
                })
                .then();
    }

    @Override
    public Mono<Void> withdrawFromWallet(String walletId, double amount) {
        return walletRepository.findById(walletId)
                .flatMap(wallet -> {
                    if (wallet.getBalance() < amount) {
                        return Mono.error(new InsufficientFundsException("Insufficient funds in the wallet."));
                    } else {
                        wallet.setBalance(wallet.getBalance() - amount);
                        return walletRepository.save(wallet);
                    }
                })
                .then();
    }

    @Override
    public Mono<Void> transferFunds(String sourceWalletId, String destinationWalletId, double amount) {
        Mono<Void> withdraw = withdrawFromWallet(sourceWalletId, amount);
        Mono<Void> deposit = depositToWallet(destinationWalletId, amount);
        return Mono.when(withdraw, deposit);
    }*/
}

