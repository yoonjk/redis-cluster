package com.ibm.demo.redis.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.ibm.demo.redis.vo.PushReqVO;
import com.ibm.demo.redis.vo.PushResVO;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Service
public class RedisListService {
    private final RedisTemplate<String, Object> redisTemplate;
    
    public PushResVO addPushAll(PushReqVO pushReqVO) {
    	long ret = redisTemplate.opsForList().rightPushAll(pushReqVO.getKey(), pushReqVO.getData());
    	
    	PushResVO pushResVO = new PushResVO();
    	
    	pushResVO.setMessage("Success");
    	
    	return pushResVO;
    }
    
}
