package org.example.kafka;

import org.example.history.Goal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/goals")
public class GoalController {

    private final KafkaMessageProducer kafkaMessageProducer;

    public GoalController(KafkaMessageProducer kafkaMessageProducer) {
        this.kafkaMessageProducer = kafkaMessageProducer;
    }

    @PostMapping("/complete")
    public String completeGoal(@RequestBody Goal goalMessage) {
        String topic = "completed-goals-topic";
        kafkaMessageProducer.sendGoals(topic, goalMessage);
        return "Goal completion message sent successfully!";
    }

    @GetMapping("/history/{sectionName}")
    public String requestHistory(@PathVariable String sectionName) {
        String topic = "history-topic";
        kafkaMessageProducer.sendGoals(topic, sectionName);
        return "History request message sent successfully!";
    }
}
