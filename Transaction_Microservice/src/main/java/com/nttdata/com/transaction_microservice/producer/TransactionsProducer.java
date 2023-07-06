package com.nttdata.com.transaction_microservice.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class TransactionsProducer {

    private static final String TOPIC = "transactions-topic"; // Nombre del tema de Kafka

    private final KafkaTemplate<String, String> kafkaTemplate;

    public TransactionsProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendTransactionEvent(String transaction) {
        kafkaTemplate.send(TOPIC, transaction);
    }
}

