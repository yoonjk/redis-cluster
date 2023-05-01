package com.ibm.demo.redis.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.demo.redis.dto.ChatMessageReq;
import com.ibm.demo.redis.service.RedisPubService;
import com.ibm.demo.redis.vo.ChatMessageVO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class PubSubController {
    private final RedisPubService redisPubService;

    @PostMapping("/api/chat")
    public String pubSub(@RequestBody ChatMessageReq chatMessageReq) {
        //메시지 보내기
    	ChatMessageVO chatMessage = new ChatMessageVO();
    	BeanUtils.copyProperties(chatMessageReq, chatMessage);
    	redisPubService.sendMessage(chatMessage);

        return "success";
    }
}
