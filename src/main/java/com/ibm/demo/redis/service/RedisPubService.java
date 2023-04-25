package com.ibm.demo.redis.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.demo.redis.vo.ChatMessage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class RedisPubService {
    private final RedisTemplate<String, Object> redisTemplate;

	@Value("${spring.redis.topic}")
	private String topicName;
	
	ObjectMapper objectMapper = new ObjectMapper();
    public void sendMessage(ChatMessage chatMessage) {
    	log.info("topicName:{}, Chat Message:{}", topicName, chatMessage);
    	try {
            redisTemplate.convertAndSend(topicName, objectMapper.writeValueAsString(chatMessage));
    	} catch(JsonProcessingException jpe) {
    		jpe.printStackTrace();
    	}
    }
}
