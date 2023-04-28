package com.ibm.demo.redis.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.ibm.demo.redis.vo.HashReqVO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RedisHashesService {
    private final RedisTemplate<String, Object> redisTemplate;
    private final StringRedisTemplate stringRedisTemplate;
    
    public Object setItems(HashReqVO hashReqVO) {
    	redisTemplate.opsForHash().put(hashReqVO.getKey(), hashReqVO.getItems(), hashReqVO.getValues());
    	return null;
    }
}
