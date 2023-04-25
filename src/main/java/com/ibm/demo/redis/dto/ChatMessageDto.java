package com.ibm.demo.redis.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class ChatMessageDto {
    private String sender;
    private String context;
}
