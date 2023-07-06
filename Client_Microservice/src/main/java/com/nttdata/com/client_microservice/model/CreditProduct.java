package com.nttdata.com.client_microservice.model;

import lombok.Data;

@Data
public class CreditProduct {
    private String id;
    private String clientId;
    private double balance;
    private CreditProductType creditProductType;


}
