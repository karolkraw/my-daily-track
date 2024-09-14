package org.example.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class TaskEventListener {

    @KafkaListener(topics = "${spring.kafka.topic.task-events}", groupId = "history-service-group")
    public void handleTaskEvent(String event) {
        System.out.println("Received task event: " + event);
        // Save event to the database (history logging)
    }
}
