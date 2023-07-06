package com.nttdata.com.client_microservice.model;

import lombok.Data;

import java.util.Date;

@Data
public class Transaction {
    private String id;

    private String accountId;
    private String debitCardId;

    private TransactionType transactionType;

    private double amount;

    private Date date;
}
