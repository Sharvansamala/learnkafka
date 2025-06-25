package com.sharvan.learnkafka.user_service.service;

import com.sharvan.learnkafka.user_service.dto.UserRequest;
import com.sharvan.learnkafka.user_service.entity.UserEntity;
import com.sharvan.learnkafka.user_service.event.UserCreatedEvent;
import com.sharvan.learnkafka.user_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Value("${kafka.topic.user-created-topic}")
    private String KAFKA_USER_CREATED_TOPIC;

    private final KafkaTemplate<Long, UserCreatedEvent> kafkaTemplate;


    public void ceateUser(UserRequest userRequest) {
        UserEntity userEntity = UserEntity.builder()
                .id(userRequest.getId())
                .name(userRequest.getName())
                .email(userRequest.getEmail())
                .build();

        userEntity= userRepository.save(userEntity);

        UserCreatedEvent userCreatedEvent = UserCreatedEvent.builder()
                .id(userEntity.getId())
                .name(userEntity.getName())
                .email(userEntity.getEmail())
                .build();

        kafkaTemplate.send(KAFKA_USER_CREATED_TOPIC,userCreatedEvent.getId(), userCreatedEvent);
    }
}
