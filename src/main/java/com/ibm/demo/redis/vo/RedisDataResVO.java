package com.ibm.demo.redis.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class RedisDataResVO {
    public String key;
    public Object value;
}
