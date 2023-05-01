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
    
    public Object getEntries(HashReqVO hashReqVO) {
    	Object ret = redisTemplate.opsForHash().entries(hashReqVO.getKey());
    	
    	return ret;
    }
    
    public Object delete(HashReqVO hashReqVO) {
    	Long ret = redisTemplate.opsForHash().delete(hashReqVO.getKey(), hashReqVO.getItems()[0]);
    	
    	return ret;
    }
    
    public Object values(HashReqVO hashReqVO) {
    	Object ret = redisTemplate.opsForHash().values(hashReqVO.getKey());
    	
    	return ret;
    }
    
    public Object keys(HashReqVO hashReqVO) {
    	Object ret = redisTemplate.opsForHash().keys(hashReqVO.getKey());
    	
    	return ret;
    }    
    
    public Object setPut(HashReqVO hashReqVO) {
    	redisTemplate.opsForHash().put(hashReqVO.getKey(), hashReqVO.getItems()[0], hashReqVO.getValues()[0]);

    	return null;
    }
}
