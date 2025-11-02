package com.ben.Payment.Service.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderConfirmProducer {

    private static final String TOPIC = "order-topic";

    private final KafkaTemplate<String, String> kafkaTemplate;

    public OrderConfirmProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String orderId) {
        kafkaTemplate.send(TOPIC, orderId);
    }
}
