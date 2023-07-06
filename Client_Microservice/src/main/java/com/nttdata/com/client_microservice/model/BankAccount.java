package com.nttdata.com.client_microservice.model;

import lombok.Data;

@Data
public class BankAccount {
    private String id;
    private AccountType accountType;
    private double balance;
    private int transactionCount;
}
