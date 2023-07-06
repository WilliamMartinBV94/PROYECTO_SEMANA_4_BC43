package com.nttdata.com.client_microservice.model;

import lombok.Data;

@Data
public class Account {
    private String id;

    private String clientId;

    private AccountType accountType;

    private double balance;
}
