package com.ibm.demo.redis.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.demo.redis.dto.ChatMessageDto;
import com.ibm.demo.redis.service.RedisPubService;
import com.ibm.demo.redis.vo.ChatMessage;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class PubSubController {
    private final RedisPubService redisPubService;

    @PostMapping("/api/chat")
    public String pubSub(@RequestBody ChatMessageDto chatMessageDto) {
        //메시지 보내기
    	ChatMessage chatMessage = new ChatMessage();
    	BeanUtils.copyProperties(chatMessageDto, chatMessage);
    	redisPubService.sendMessage(chatMessage);

        return "success";
    }
}
