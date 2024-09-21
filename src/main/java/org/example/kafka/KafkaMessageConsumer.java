package org.example.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.history.Goal;
import org.example.history.GoalHistoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Component
public class KafkaMessageConsumer {
    private static final Logger logger = LoggerFactory.getLogger(KafkaMessageConsumer.class);

    private final GoalHistoryRepository goalHistoryRepository;
    private final KafkaMessageProducer kafkaMessageProducer;
    private final ObjectMapper objectMapper;

    public KafkaMessageConsumer(GoalHistoryRepository goalHistoryRepository, KafkaMessageProducer kafkaMessageProducer, ObjectMapper objectMapper) {
        this.goalHistoryRepository = goalHistoryRepository;
        this.kafkaMessageProducer = kafkaMessageProducer;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "${spring.kafka.consumer.topic.goal-completed}",
            groupId = "${spring.kafka.consumer.group-id}",
            containerFactory = "kafkaListenerContainerFactory")
    public void listenForCompletedGoals(Goal goalCompleted) {
        try {
            logger.info("Received message: Title = {}, Description = {}", goalCompleted.title, goalCompleted.description);
            goalHistoryRepository.save(goalCompleted);
            // dodac date complete
        } catch (Exception e) {
            logger.error("Error processing message: {}", e.getMessage(), e);
            throw e;
        }
    }

    @KafkaListener(topics = "${spring.kafka.consumer.topic.history}",
            groupId = "${spring.kafka.consumer.group-id}",
            containerFactory = "stringKafkaListenerContainerFactory")
    public void listenForHistoryRequest(String sectionName) throws JsonProcessingException {
        try {
            List<Goal> goals = goalHistoryRepository.findAllBySectionName(sectionName);
            String goalsJson = objectMapper.writeValueAsString(goals);
            System.out.println("goals: " + goals.size());
            System.out.println("goalsJson: " + goalsJson);

            kafkaMessageProducer.sendGoals("retrieved-goals-topic", goalsJson);

            logger.info("Successfully sent goals for section: {}", sectionName);
        } catch (Exception e) {
            logger.error("Error processing history request: {}", e.getMessage(), e);
            throw e;
        }
    }
}

