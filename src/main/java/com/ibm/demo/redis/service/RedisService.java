package com.ibm.demo.redis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.ibm.demo.redis.vo.RedisDataVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RedisService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    
	public void registerData(RedisDataVO redisDataVO) {
        try{
        	log.info("registDataVO:{}", redisDataVO);
            redisTemplate.opsForValue().set(redisDataVO.getKey(), redisDataVO);
        }catch (Exception e){
            log.error("### Redis Set Key Error !!! ::: {}", e.getMessage());
        }
	}
	
	public Object retrieveData(String key) {
		Object redisDataVO = null;
        try{
        	log.info("1.retrieveData:{}", key);
        	redisDataVO = redisTemplate.opsForValue().get(key);
        	log.info("2.retrieveData:{}", redisDataVO);
        }catch (Exception e){
            log.error("### Redis Set Key Error !!! ::: {}", e.getMessage());
        }
        
        return redisDataVO;
	}
}
