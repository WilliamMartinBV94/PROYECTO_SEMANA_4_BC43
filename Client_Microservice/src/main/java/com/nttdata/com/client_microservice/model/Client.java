package com.nttdata.com.client_microservice.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "clients")
public class Client {
    @Id
    private String id;
    private String nombre;
    private String name;
    private ClientType clientType;
    private List<BankAccount> bankAccounts;
    private List<CreditProduct> creditProducts;
    private List<CreditCard> creditCards;

}
