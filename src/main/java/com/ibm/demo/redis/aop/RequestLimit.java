package com.ibm.demo.redis.aop;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
@Order(Ordered.HIGHEST_PRECEDENCE)
public @interface RequestLimit {
    /**
     * 허용되는 최대 방문 횟수
     */
    int count() default Integer.MAX_VALUE;

    /**
     * 기간(밀리초), 기본값은 1분입니다.
     */
    long time() default 60000;
}
