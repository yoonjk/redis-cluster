package com.ibm.demo.redis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.ibm.demo.redis.vo.RedisDataVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RedisService {

    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate<String, Object> redisTemplate;
    
	public void registerData(RedisDataVO redisDataVO) {
        try{
            redisTemplate.opsForValue().set(redisDataVO.getKey(), redisDataVO);
        }catch (Exception e){
            log.error("### Redis Set Key Error !!! ::: {}", e.getMessage());
        }
	}
	
	public RedisDataVO retrieveData(String key) {
		RedisDataVO redisDataVO = null;
        try{
        	redisDataVO = (RedisDataVO)redisTemplate.opsForValue().get(key);
        }catch (Exception e){
            log.error("### Redis Set Key Error !!! ::: {}", e.getMessage());
        }
        
        return redisDataVO;
	}
}
