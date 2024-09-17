package org.example.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


@Component
public class KafkaMessageConsumer {

    private static final Logger logger = LoggerFactory.getLogger(KafkaMessageConsumer.class);

    @KafkaListener(topics = "${spring.kafka.consumer.topic.goal-completed}",
            groupId = "${spring.kafka.consumer.group-id}")
    public void listen(Message message) {
        try {
            logger.info("Received message: Title = {}, Description = {}", message.getTitle(), message.getDescription());
            // Process the message
        } catch (Exception e) {
            logger.error("Error processing message: {}", e.getMessage(), e);
            throw e;  // Re-throw to trigger error handler
        }
    }
}

/*
@Component
public class KafkaMessageConsumer {

    private static final Logger logger = LoggerFactory.getLogger(KafkaMessageConsumer.class);

    @KafkaListener(topics = "${spring.kafka.consumer.topic.goal-completed}",
                   groupId = "${spring.kafka.consumer.group-id}")
    public void listen(Message message) {
        try {
            logger.info("Received message: Title = {}, Description = {}", message.getTitle(), message.getDescription());
            // Process the message
        } catch (Exception e) {
            logger.error("Error processing message: {}", e.getMessage(), e);
            throw e;  // Re-throw to trigger error handler
        }
    }
}
*/
