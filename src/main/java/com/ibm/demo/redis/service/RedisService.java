package com.ibm.demo.redis.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.ibm.demo.redis.vo.RedisDataResVO;
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
	

	public RedisDataVO setValue(RedisDataVO redisDataVO) {
		RedisDataVO redisDataRes = new RedisDataVO();
        try{
        	log.info("1.setValue:{}", redisDataVO);
        	redisTemplate.opsForValue().set(redisDataVO.getKey(), redisDataVO.getValue());
    		BeanUtils.copyProperties(redisDataVO, redisDataRes);
   
        	log.info("2.getValue:{}", redisDataRes);
        }catch (Exception e){
            log.error("### Redis Set Key Error !!! ::: {}", e.getMessage());
        }
        
        return redisDataRes;
	}
	
	public RedisDataResVO getValue(String key) {
		RedisDataResVO redisDataResVO = new RedisDataResVO();
		Object val = null;
        try{
        	log.info("1.set key:{}", key);
        	val = redisTemplate.opsForValue().get(key);
    		
        	redisDataResVO.setKey(key);
        	redisDataResVO.setValue(val);
        	log.info("2.getValue:{}", redisDataResVO);
        }catch (Exception e){
            log.error("### Redis Set Key Error !!! ::: {}", e.getMessage());
        }
        
        return redisDataResVO;
	}
}
