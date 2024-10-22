package org.example.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.history.Goal;
import org.example.history.GoalHistoryRepository;
import org.example.history.HistoryKafkaRequest;
import org.example.history.dto.GoalDto;
import org.example.history.dto.GoalMapper;
import org.example.section.SectionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class KafkaMessageConsumer {
    private static final Logger logger = LoggerFactory.getLogger(KafkaMessageConsumer.class);

    private final GoalHistoryRepository goalHistoryRepository;
    private final SectionService sectionService;

    private final KafkaMessageProducer kafkaMessageProducer;
    private final ObjectMapper objectMapper;

    public KafkaMessageConsumer(GoalHistoryRepository goalHistoryRepository, SectionService sectionService,
                                KafkaMessageProducer kafkaMessageProducer, ObjectMapper objectMapper) {
        this.goalHistoryRepository = goalHistoryRepository;
        this.sectionService = sectionService;
        this.kafkaMessageProducer = kafkaMessageProducer;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "${spring.kafka.consumers.completed-goals-consumer.topic}",
            groupId = "${spring.kafka.consumers.completed-goals-consumer.group-id}",
            containerFactory = "goalDtoKafkaListenerContainerFactory")
    public void listenForCompletedGoals(GoalDto goalCompleted) {
        try {
            logger.info("Received message: Title = {}, Description = {}", goalCompleted.getTitle(), goalCompleted.getDescription());
            Goal goal = GoalMapper.mapDtoToGoal(goalCompleted);
            goal.setSection(sectionService.getSectionByNameAndUsername(
                    goalCompleted.getSectionName(), goalCompleted.getUsername()));
            goalHistoryRepository.save(goal);
        } catch (Exception e) {
            logger.error("Error processing message: {}", e.getMessage(), e);
            throw e;
        }
    }

    @KafkaListener(topics = "${spring.kafka.consumers.history-consumer.topic}",
            groupId = "${spring.kafka.consumers.history-consumer.group-id}",
            containerFactory = "historyKafkaRequestKafkaListenerContainerFactory")
    public void listenForHistoryRequest(HistoryKafkaRequest request) throws JsonProcessingException {
        try {
            List<Goal> goals = goalHistoryRepository.findAllBySectionNameAndUser_Username(request.getSectionName(), request.getUsername());
            List<GoalDto> goalsDto = GoalMapper.goalsToDtoList(goals);
            String goalsJson = objectMapper.writeValueAsString(goalsDto);
            System.out.println("goals: " + goals.size());
            System.out.println("goalsJson: " + goalsJson);

            kafkaMessageProducer.sendGoals("retrieved-goals-topic", goalsJson);

            logger.info("Successfully sent goals for section: {}", request.getSectionName());
        } catch (Exception e) {
            logger.error("Error processing history request: {}", e.getMessage(), e);
            throw e;
        }
    }
}

