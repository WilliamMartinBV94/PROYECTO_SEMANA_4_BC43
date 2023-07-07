package com.nttdata.com.yankiwallet_microservice.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.math.BigDecimal;

@Data
@RedisHash("YankiWallet")
public class YankiWallet {
    @Id
    private String id;
    private String documentId;
    private String phoneNumber;
    private String imei;
    private String email;
    private BigDecimal balance;
    private String cardId;

}

