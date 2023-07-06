package com.nttdata.com.client_microservice.model;

import lombok.Data;

@Data
public class DebitCard {
    private String id;
    private String clientId;
    private String accountId;
    private double balance;
    public DebitCard(String clientId, String accountId) {
        this.clientId = clientId;
        this.accountId = accountId;
    }
}
