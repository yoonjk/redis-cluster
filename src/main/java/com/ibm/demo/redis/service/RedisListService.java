package com.ibm.demo.redis.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.ibm.demo.redis.vo.PushReqVO;
import com.ibm.demo.redis.vo.PushResVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class RedisListService {
    private final RedisTemplate<String, Object> redisTemplate;
    private final StringRedisTemplate stringRedisTemplate;
    public PushResVO addRightPush(PushReqVO pushReqVO) {
    	
    	long ret = 0;
    	
    	for(int i = 0; i < pushReqVO.getData().length; i++) {
    		ret = redisTemplate.opsForList().rightPush(pushReqVO.getKey(), pushReqVO.getData()[i]);
    	}
    	
    	PushResVO pushResVO = new PushResVO();
    	
    	pushResVO.setMessage("Right Push Succeed");
    	
    	return pushResVO;
    }
    
    /**
     * lpush
     * @param pushReqVO
     * @return
     */
    public long addLeftPushAll(PushReqVO pushReqVO) {
    	
    	long ret = 0;

    	List items = Arrays.asList(pushReqVO.getData());
    	log.info("items:{}", items);
    	ret = redisTemplate.opsForList().leftPushAll(pushReqVO.getKey(), items);

    	return ret;
    } 
    
    public long addRightPushAll(PushReqVO pushReqVO) {
    	
    	long ret = 0;

    	List items = Arrays.asList(pushReqVO.getData());
    	log.info("items:{}", items);
    	ret = redisTemplate.opsForList().rightPush(pushReqVO.getKey(), items);

    	return ret;
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
    public Object lrange(PushReqVO pushReqVO) {
    	Object items = stringRedisTemplate.opsForList().range(pushReqVO.getKey(), pushReqVO.getStart(), pushReqVO.getEnd());
    	
    	return items;
    }    
    
    /**
     * length
     * @param pushReqVO
     * @return
     */
    public long llen(PushReqVO pushReqVO) {
    	Long len = stringRedisTemplate.opsForList().size(pushReqVO.getKey());
    	
    	return len;
    }    
    
    /**
     * item의 index를 반환 
     * @param pushReqVO
     * @return
     */
    public long lpos(PushReqVO pushReqVO) {
    	Long len = stringRedisTemplate.opsForList().indexOf(pushReqVO.getKey(), pushReqVO.getData()[0]);
    	
    	return len;
    } 
    
    /**
     * index 를 받아서 Item을 반환 
     * @param pushReqVO
     * @return
     */
    public String lindex(PushReqVO pushReqVO) {
    	String item = stringRedisTemplate.opsForList().index(pushReqVO.getKey(), pushReqVO.getStart());
    	
    	return item;
    }      
    
    /**
     * index 를 받아서 Item을 반환 
     * @param pushReqVO
     * @return
     */
    public PushResVO trim(PushReqVO pushReqVO) {
    	stringRedisTemplate.opsForList().trim(pushReqVO.getKey(), pushReqVO.getStart(), pushReqVO.getEnd());
    	PushResVO pushResVO = new PushResVO();
    	pushResVO.setMessage("Succeed");
    	return pushResVO;
    } 
    
    /**
     * Left Item을 반환 
     * @param pushReqVO
     * @return
     */
    public String lpop(PushReqVO pushReqVO) {
    	String item = stringRedisTemplate.opsForList().leftPop(pushReqVO.getKey());

    	return item;
    }   
    
    /**
     * Right Item을  반환 
     * @param pushReqVO
     * @return
     */
    public String rpop(PushReqVO pushReqVO) {
    	String item = stringRedisTemplate.opsForList().rightPop(pushReqVO.getKey());

    	return item;
    } 
    
    /**
     * Left Item을 Set
     * @param pushReqVO
     * @return
     */
    public PushResVO lset(PushReqVO pushReqVO) {
    	stringRedisTemplate.opsForList().set(pushReqVO.getKey(), pushReqVO.getStart(), pushReqVO.getData()[0]);

    	PushResVO pushResVO = new PushResVO();
    	pushResVO.setMessage("Succeed");
    	
    	return pushResVO;  	
    }   
    
    /**
     * Left Item을 삭제 
     * @param pushReqVO
     * @return
     */
    public long lrem(PushReqVO pushReqVO) {
    	long ret = stringRedisTemplate.opsForList().remove(pushReqVO.getKey(), pushReqVO.getStart(), pushReqVO.getData()[0]);
    	
    	return ret;  	
    }      
}
