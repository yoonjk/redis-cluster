package com.ibm.demo.redis.aop;

import java.util.Collections;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.ibm.demo.redis.util.IpUtils;

@Aspect
@Component
public class RequestLimitAspect {
//	@Autowired
//	private RedisTemplate<String, Object> redisTemplate;
//	
//	@Value("${classpath:/scripts/requestLimit.lua}")
//	private String requestLimitLua;
//	
//	@Pointcut("@annotation(com.ibm.demo.redis.aop.ResquestLimit)")
//	public void pointcut() {
//		
//	}
//	
//    @Before("pointcut() && @annotation(requestLimit)")
//    public void doBefore(JoinPoint joinPoint, RequestLimit requestLimit) {
//        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//    	DefaultRedisScript<Boolean> redisScript = new DefaultRedisScript<>();
//    	Resource resource = new ClassPathResource(requestLimitLua);
//    	redisScript.setScriptSource(new ResourceScriptSource(resource));
//    	redisScript.setResultType(Boolean.class);
//        
//        if (null == requestAttributes) {
//            return;
//        }
//        HttpServletRequest httpRequest = requestAttributes.getRequest();
//
//        String ip = IpUtils.getRealIP(httpRequest);
//        String key = ip;
//
//        Boolean allow = redisTemplate.execute(
//        		redisScript,
//                Collections.singletonList(key),
//                String.valueOf(requestLimit.count()), //limit
//                String.valueOf(requestLimit.time())); //expire
//
//        assert allow != null;
//        if (!allow) {
//            throw new RuntimeException("RequestLimit");
//        }
//
//        return;
//    }	
}
