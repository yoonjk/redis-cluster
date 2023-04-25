package com.ibm.demo.redis.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Service;

import com.ibm.demo.redis.dto.RequestLimitDto;
import com.ibm.demo.redis.dto.TransferDto;
import com.ibm.demo.redis.vo.Item;
import com.ibm.demo.redis.vo.LeaderBoardReqVO;
import com.ibm.demo.redis.vo.ZRangeByScoreVO;

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
	
	@Value("${classpath:/scripts/script.lua}")
	private String zrangeByScore;
	
	@Value("${classpath:/scripts/leaderBoard.lua}")
	private String leaderBoard;
	
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    
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
    
    /**
     * sorted set으로부터 min ~ max 사이의 value를 가진 list들 중, offset을 기준으로 count 수 만큼 추출하게 된다
     * @param zRangeByScoreVO
     * @return
     */
    public Object zrangeByScore(ZRangeByScoreVO zRangeByScoreVO) {
    	DefaultRedisScript<Long>  redisScript = new DefaultRedisScript<>();
    	Resource resource = new ClassPathResource(zrangeByScore);
    	redisScript.setScriptSource(new ResourceScriptSource(resource));
    	redisScript.setResultType(Long.class);
    	
    	log.info("zrangeByScore input:{}", zRangeByScoreVO);
    	Object ret = redisTemplate.execute(redisScript, Collections.singletonList(zRangeByScoreVO.getKey()), zRangeByScoreVO.getMin(), zRangeByScoreVO.getMax(), zRangeByScoreVO.getOffset(), zRangeByScoreVO.getCount());
    	log.info("zrangeByScore return:{}", ret);
    	
    	return ret;
    }
    
    /**
     * Transfer a b 10
     * @param transfer
     * @return
     */
    public Object runLua(TransferDto transfer) {
    	DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
    	Resource resource = new ClassPathResource(transferLua);
    	redisScript.setScriptSource(new ResourceScriptSource(resource));
    	redisScript.setResultType(Long.class);
    	
        String fromAccount = transfer.getFromAccount();
        String toAccount = transfer.getToAccount();
        Object[] args = new Object[] {transfer.getAmount()};
        
        Long ret = (Long)redisTemplate.execute(script, Arrays.asList(fromAccount, toAccount), args); 
        
        log.info("transfer ret:{}", ret);
        
    	return ret;
    }   
    
    /**
     * requestLimitRate
     * @param requestLimitDto
     * @return
     */
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
    
    public Object retrieveLeaderBoard(LeaderBoardReqVO leaderBoardReqVO) {
    	DefaultRedisScript<Object> redisScript = new DefaultRedisScript<>();
    	Resource resource = new ClassPathResource(leaderBoard);
    	redisScript.setScriptSource(new ResourceScriptSource(resource));
    	redisScript.setResultType(Object.class);
    	log.info("retrieveLeaderBoard:{}", leaderBoardReqVO);
    	Object[] args = new Object[] {leaderBoardReqVO.getUser()};
    	Object ret = redisTemplate.execute(redisScript,  Arrays.asList(leaderBoardReqVO.getKey(), leaderBoardReqVO.getUser()), leaderBoardReqVO.getCount());
    	
    	log.info("retrieveLeaderBoard ret:{}", ret);
    	
    	return ret;
    }
}
