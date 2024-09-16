package org.example.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


@Component
public class KafkaMessageConsumer {

    private static final Logger logger = LoggerFactory.getLogger(KafkaMessageConsumer.class);

    @KafkaListener(topics = "${spring.kafka.topic.goal-completed}",
            groupId = "${spring.kafka.consumer.group-id}")
    public void listen(ConsumerRecord<String, String> record) {
        try {
            String message = record.value();
            logger.info("Received message: {}", message);
            // Process the message
        } catch (Exception e) {
            logger.error("Error processing message: {}", e.getMessage(), e);
            throw e;  // Re-throw to trigger error handler
        }
    }
}
