package com.ibm.demo.redis.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Service;

import com.ibm.demo.redis.dto.RequestLimitDto;
import com.ibm.demo.redis.dto.TransferDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LuaService {
    @Autowired
    RedisScript<Long> script;
    
	@Value("${classpath:/scripts/transfer.lua}")
	private String transferLua;
	
	@Value("${classpath:/scripts/requestLimit.lua}")
	private String requestLimit;
	
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    
	/**
	 * 이
	 * @return
	 */
    public void transfer(TransferDto transfer ) {
        List<String> spares = null;

        String fromAccount = transfer.getFromAccount();
        String toAccount = transfer.getToAccount();
        Object[] args = new Object[] {transfer.getAmount()};
        try {
        	log.info("script:{}",script.getScriptAsString());
        	log.info("account:{}", transfer);
            Long ret = (Long)redisTemplate.execute(script, Arrays.asList(fromAccount, toAccount), args); //min, max, offset, count
            
            log.info("transfer ret:{}", ret);
        } catch (Exception e) {
        	log.error("error:", e);
            throw new RuntimeException();
        }
    }
    
    public Object runLua(TransferDto transfer) {
    	DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
    	Resource resource = new ClassPathResource(transferLua);
    	redisScript.setScriptSource(new ResourceScriptSource(resource));
    	redisScript.setResultType(Long.class);
    	
        String fromAccount = transfer.getFromAccount();
        String toAccount = transfer.getToAccount();
        Object[] args = new Object[] {transfer.getAmount()};
        
        Long ret = (Long)redisTemplate.execute(script, Arrays.asList(fromAccount, toAccount), args); //min, max, offset, count
        
        log.info("transfer ret:{}", ret);
        
    	return ret;
    }   
    
    public Object requestLimitRate(RequestLimitDto requestLimitDto) {
    	DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
    	Resource resource = new ClassPathResource(requestLimit);
    	redisScript.setScriptSource(new ResourceScriptSource(resource));
    	redisScript.setResultType(Long.class);
    	log.info("requestLimit:{}", requestLimitDto);
    	Object[] args = new Object[] {requestLimitDto.getLimit(),requestLimitDto.getExpire()};
    	Long ret = (Long) redisTemplate.execute(redisScript, Collections.singletonList(requestLimitDto.getKey()), args);
    	
    	log.info("requestLimit ret:{}", ret);
    	
    	return ret;
    }
}
