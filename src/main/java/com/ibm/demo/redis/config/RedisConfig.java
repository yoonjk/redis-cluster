package com.ibm.demo.redis.config;

import java.io.IOException;
import java.net.InetAddress;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.cache.CacheKeyPrefix;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.scripting.ScriptSource;
import org.springframework.scripting.support.ResourceScriptSource;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableCaching
@RequiredArgsConstructor
@Configuration
public class RedisConfig {    
	
	@Resource
	private StringRedisTemplate stringRedisTemplate;
	
	@Autowired
	private Environment env;

	@Value("${classpath:/scripts/transfer.lua}")
	private String location;

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
    	InetAddress address = null;
    	  try
    	  {
    	    address = InetAddress.getByName("::");
    	    // Should of connected with no exception thrown since we know this port was listening in netstat
    	    log.info("1.addresss:{}", address);
    	  }
    	  catch (Throwable t) {
    	    t.printStackTrace();
    	  }
    	  
    	  RedisSentinelConfiguration sentinelConfig = new RedisSentinelConfiguration()
    		.master("redismaster")
		    .sentinel("2401:c900:1101:17d::4", 5000)
		    .sentinel("2401:c900:1101:17d::4", 5001)
		    .sentinel("2401:c900:1101:17d::4", 5002);
   
        return new LettuceConnectionFactory(sentinelConfig);
    }
    
    /**
     * lua script
     * @return
     * @throws IOException
     */
    @Bean
    public RedisScript<Long> script() throws IOException {
    	ClassPathResource resource = new ClassPathResource("scripts/transfer.lua");
    	log.info("resourc lua:{}", resource);

    	ScriptSource scriptSource = new ResourceScriptSource(resource);

    	return RedisScript.of(scriptSource.getScriptAsString(), Long.class);
    }
    
    @Bean
    public CacheManager redisCacheManager(RedisConnectionFactory redisConnectionFactory){
        RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig()
                .disableCachingNullValues() 
                .entryTtl(Duration.ofSeconds(CacheKey.DEFAULT_EXPIRE_SEC)) 
                .computePrefixWith(CacheKeyPrefix.simple()) 
                .serializeKeysWith(
                        RedisSerializationContext.SerializationPair
                                .fromSerializer(new StringRedisSerializer())) 

                .serializeValuesWith(RedisSerializationContext
                        .SerializationPair
                        .fromSerializer(new GenericJackson2JsonRedisSerializer()));


       // 캐시키 별 default 유효시간 설정
       Map<String, RedisCacheConfiguration> cacheConfiguration = new HashMap<>();
       cacheConfiguration.put(CacheKey.ZONE,RedisCacheConfiguration.defaultCacheConfig()
               .entryTtl(Duration.ofSeconds(CacheKey.ZONE_EXPIRE_SEC)));

        return RedisCacheManager.RedisCacheManagerBuilder
                .fromConnectionFactory(redisConnectionFactory)
                .cacheDefaults(configuration)
                .withInitialCacheConfigurations(cacheConfiguration)
                .build();
    }    
  
    @Bean
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory){
    	StringRedisTemplate redisTemplate = new StringRedisTemplate();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        
        return redisTemplate;
    }
    
    private void loadRedisScript(RedisScript<Object> redisScript, String luaName) {
    	try {
    		List<Boolean> results = stringRedisTemplate.getConnectionFactory().getConnection().scriptExists(redisScript.getSha1());
    		
			log.info("lua:{}, sha=[{}]", luaName, results);
			
    		if (Boolean.FALSE.equals(results.get(0))) {
    			String sha = stringRedisTemplate.getConnectionFactory().getConnection().scriptLoad(scriptBytes(redisScript));
    			log.info("lua:{}, sha=[{}]", luaName, sha);
    		}
    	} catch(Exception e) {
    		log.error("{}", luaName, e);
    	}
    }
    
    private byte[] scriptBytes(RedisScript<?> script) {
    	return stringRedisTemplate.getStringSerializer().serialize(script.getScriptAsString());
    }
 
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer() );
        
        return redisTemplate;
    }      
}
