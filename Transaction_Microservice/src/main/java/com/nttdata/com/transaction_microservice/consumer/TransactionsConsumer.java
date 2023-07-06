package com.nttdata.com.transaction_microservice.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class TransactionsConsumer {

    private static final String TOPIC = "transactions-topic";

    @KafkaListener(topics = TOPIC)
    public void receiveTransactionEvent(String transaction) {
        // Procesa el evento de transacción recibido
        System.out.println("Received transaction event: " + transaction);
    }
}

