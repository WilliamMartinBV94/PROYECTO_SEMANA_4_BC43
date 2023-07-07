package com.nttdata.com.yankiwallet_microservice.service;

import com.nttdata.com.yankiwallet_microservice.model.YankiWallet;
import reactor.core.publisher.Mono;

public interface YankiWalletService {
    Mono<YankiWallet> createWallet(YankiWallet wallet);

    Mono<YankiWallet> getWalletById(String walletId);

    Mono<YankiWallet> updateWallet(YankiWallet wallet);

    Mono<Void> deleteWallet(String walletId);

    Mono<YankiWallet> associateCard(String walletId, String cardId);

    Mono<YankiWallet> removeCardAssociation(String walletId, String cardId);

    /*Mono<Double> getWalletBalance(String walletId);

    Mono<Void> depositToWallet(String walletId, double amount);

    Mono<Void> withdrawFromWallet(String walletId, double amount);

    Mono<Void> transferFunds(String sourceWalletId, String destinationWalletId, double amount);*/

}
