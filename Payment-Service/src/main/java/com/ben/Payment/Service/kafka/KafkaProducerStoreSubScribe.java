package com.ben.Payment.Service.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerStoreSubScribe {

    private static final String TOPIC = "payment-topic";

    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaProducerStoreSubScribe(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }


    public void sendMessage(String storeId) {
        kafkaTemplate.send(TOPIC, storeId);
    }
}
