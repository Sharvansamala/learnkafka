package com.sharvan.learnkafka.user_service.event;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserCreatedEvent {
    private Long id;
    private String name;
    private String email;
}
