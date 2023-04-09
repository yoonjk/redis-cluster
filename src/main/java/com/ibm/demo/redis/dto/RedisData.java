package com.ibm.demo.redis.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class RedisData {
    public String key;
    public String value;
}
