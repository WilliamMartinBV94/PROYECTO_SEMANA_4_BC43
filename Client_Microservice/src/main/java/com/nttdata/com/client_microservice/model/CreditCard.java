package com.nttdata.com.client_microservice.model;

import lombok.Data;

@Data
public class CreditCard {
    private String id;
    private double creditLimit;
    private double availableBalance;
}
