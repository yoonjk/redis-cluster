package com.ibm.demo.redis.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Service;

import com.ibm.demo.redis.vo.RedisDataVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RedisService {
    @Autowired
    RedisScript<Object> script;
    
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
	
	/**
	 * test
	 * @return
	 */
    public String runLua() {
        List<String> spares = null;
        try {
            spares = (ArrayList <String>) redisTemplate.execute(script, Collections.singletonList("key"), "0", "4", "0", "1"); //min, max, offset, count
            
            return Optional.ofNullable(spares)
                           .orElseThrow(RuntimeException::new) // if spares null -> exception
                           .stream()
                           .findFirst()
                           .orElseThrow(RuntimeException::new); // if findFirst(isEmpty) null -> exception
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
