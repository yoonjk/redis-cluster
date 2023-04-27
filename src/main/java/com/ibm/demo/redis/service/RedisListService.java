package com.ibm.demo.redis.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.ibm.demo.redis.vo.PushReqVO;
import com.ibm.demo.redis.vo.PushResVO;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Service
public class RedisListService {
    private final RedisTemplate<String, Object> redisTemplate;
    private final StringRedisTemplate StringRedisTemplate;
    public PushResVO addRightPush(PushReqVO pushReqVO) {
    	
    	long ret = 0;
    	
    	for(int i = 0; i < pushReqVO.getData().length; i++) {
    		ret = redisTemplate.opsForList().rightPush(pushReqVO.getKey(), pushReqVO.getData()[i]);
    	}
    	
    	PushResVO pushResVO = new PushResVO();
    	
    	pushResVO.setMessage("Right Push Succeed");
    	
    	return pushResVO;
    }
    
    public PushResVO addLeftPush(PushReqVO pushReqVO) {
    	
    	long ret = 0;
    	
    	for(int i = 0; i < pushReqVO.getData().length; i++) {
    		ret = redisTemplate.opsForList().leftPush(pushReqVO.getKey(), pushReqVO.getData()[i]);
    	}
    	
    	PushResVO pushResVO = new PushResVO();
    	
    	pushResVO.setMessage("Left Push Succeed");
    	
    	return pushResVO;
    } 
    
    /**
     * range
     * @param pushReqVO
     * @return
     */
    public Object range(PushReqVO pushReqVO) {
    	Object items = StringRedisTemplate.opsForList().range(pushReqVO.getKey(), pushReqVO.getStart(), pushReqVO.getEnd());
    	
    	return items;
    }    
    
    /**
     * length
     * @param pushReqVO
     * @return
     */
    public long itemLength(PushReqVO pushReqVO) {
    	Long len = StringRedisTemplate.opsForList().size(pushReqVO.getKey());
    	
    	return len;
    }    
    
    /**
     * item의 index를 반환 
     * @param pushReqVO
     * @return
     */
    public long indexOf(PushReqVO pushReqVO) {
    	Long len = StringRedisTemplate.opsForList().indexOf(pushReqVO.getKey(), pushReqVO.getData()[0]);
    	
    	return len;
    } 
    
    /**
     * index 를 받아서 Item을 반환 
     * @param pushReqVO
     * @return
     */
    public String index(PushReqVO pushReqVO) {
    	String item = StringRedisTemplate.opsForList().index(pushReqVO.getKey(), pushReqVO.getStart());
    	
    	return item;
    }      
}
