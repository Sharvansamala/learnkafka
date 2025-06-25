package com.sharvan.learnkafka.notification_service.consumer;

import com.sharvan.learnkafka.user_service.event.UserCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserKafkaConsumer {

    @KafkaListener(topics = "user-created-topic")
    public void handleUserCreatedTopic(UserCreatedEvent userCreatedEvent){
        log.info("Handle User created : "+userCreatedEvent.toString());
    }


    @KafkaListener(topics = "user-random-topic")
    public void handleUserRandomTopic(String message){
        log.info("message received by 1: "+message);
    }

    @KafkaListener(topics = "user-random-topic")
    public void handleUserRandomTopic1(String message){
        log.info("message received by 2: "+message);
    }

    @KafkaListener(topics = "user-random-topic")
    public void handleUserRandomTopic2(String message){
        log.info("message received by 3: "+message);
    }

}
