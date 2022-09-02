package dev.muldev.pockafka.orch_listeners;

import dev.muldev.pockafka.config.ApplicationConstant;
import dev.muldev.pockafka.events.UserCreated;
import dev.muldev.pockafka.greetings.GreetingService;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class Listener {

    private final GreetingService greetingService;

    @KafkaListener(groupId = "groupId", topics = ApplicationConstant.TOPIC_NAME, containerFactory = ApplicationConstant.KAFKA_LISTENER_CONTAINER_FACTORY)
    public void receivedMessage(UserCreated message){
        this.greetingService.sendGreetingEmail(message);
    }
}
