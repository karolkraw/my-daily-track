package org.example.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaMessageProducer {

    private static final Logger logger = LoggerFactory.getLogger(KafkaMessageProducer.class);

    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaMessageProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendGoals(String topic, String message) {
        try {
            kafkaTemplate.send(topic, message);
            logger.info("Sent message to topic {}: {}", topic, message);
        } catch (Exception e) {
            logger.error("Error sending message to topic {}: {}", topic, e.getMessage(), e);
        }
    }
}
