package com.ibm.demo.redis.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class ZRangeByScoreReq {
	private String key;
	private int min;
	private int max;
	private int offset;
	private int count;
}