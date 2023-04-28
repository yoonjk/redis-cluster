package com.ibm.demo.redis.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class HashReqVO {
	private String key;
	private int start;
	private int end;
	private String[] items;
	private String[] values;	
}
