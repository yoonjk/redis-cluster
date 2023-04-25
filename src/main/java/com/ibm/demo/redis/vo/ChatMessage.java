package com.ibm.demo.redis.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@ToString
@Setter
@Getter
public class ChatMessage {
    private String sender;
    private String context;
}
