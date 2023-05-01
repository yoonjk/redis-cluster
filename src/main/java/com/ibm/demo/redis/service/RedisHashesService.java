package com.ibm.demo.redis.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.ibm.demo.redis.vo.HashesReqVO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RedisHashesService {
    private final RedisTemplate<String, Object> redisTemplate;
    private final StringRedisTemplate stringRedisTemplate;
    
    public Object setItems(HashesReqVO hashReqVO) {
    	redisTemplate.opsForHash().put(hashReqVO.getKey(), hashReqVO.getHashKey(), hashReqVO.getValues());
    	
    	return null;
    }
    
    public Object getEntries(HashesReqVO hashReqVO) {
    	Object ret = redisTemplate.opsForHash().entries(hashReqVO.getKey());
    	
    	return ret;
    }
    
    public Object delete(HashesReqVO hashReqVO) {
    	Long ret = redisTemplate.opsForHash().delete(hashReqVO.getKey(), hashReqVO.getHashKey()[0]);
    	
    	return ret;
    }
    
    public Object values(HashesReqVO hashReqVO) {
    	Object ret = redisTemplate.opsForHash().values(hashReqVO.getKey());
    	
    	return ret;
    }
    
    public Object keys(HashesReqVO hashReqVO) {
    	Object ret = redisTemplate.opsForHash().keys(hashReqVO.getKey());
    	
    	return ret;
    }    
    
    public Object setPut(HashesReqVO hashReqVO) {
    	redisTemplate.opsForHash().put(hashReqVO.getKey(), hashReqVO.getHashKey()[0], hashReqVO.getValues()[0]);

    	String ret = "Success";
    	
    	return ret;
    }
    
    public Object getValue(HashesReqVO hashReqVO) {
    	Object ret = redisTemplate.opsForHash().get(hashReqVO.getKey(), hashReqVO.getHashKey()[0]);

    	return ret;
    }    
    
    public Object hasKey(HashesReqVO hashReqVO) {
    	Object ret = redisTemplate.opsForHash().hasKey(hashReqVO.getKey(), hashReqVO.getHashKey()[0]);

    	return ret;
    }   
    
    public Object increment(HashesReqVO hashReqVO) {
    	Long ret = redisTemplate.opsForHash().increment(hashReqVO.getKey(), hashReqVO.getHashKey()[0], hashReqVO.getStart());

    	return ret;
    }

    public Object size(HashesReqVO hashReqVO) {
    	Long ret = redisTemplate.opsForHash().size(hashReqVO.getKey());

    	return ret;
    } 
    
    public Object multiGet(HashesReqVO hashReqVO) {
    	List<Object> hashKeys = Collections.singletonList(Arrays.toString(hashReqVO.getHashKey()));
    	Object ret = redisTemplate.opsForHash().multiGet(hashReqVO.getKey(), hashKeys);

    	return ret;
    }      
}
