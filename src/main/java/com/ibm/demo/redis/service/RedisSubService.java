package com.ibm.demo.redis.service;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class RedisSubService implements MessageListener {
    @Override
    public void onMessage(Message message, byte[] pattern) {
    	log.info("받은 메시지 = {}, body={}, pattern:{}", message, new String(message.getBody()),  new String(pattern));
    }
}
