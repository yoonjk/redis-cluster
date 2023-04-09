package com.ibm.demo.redis.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class RedisDataVO {
    private String key;
    private String value;
}
