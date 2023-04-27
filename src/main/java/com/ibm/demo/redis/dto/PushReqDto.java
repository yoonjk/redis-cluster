package com.ibm.demo.redis.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class PushReqDto {
	private String key;
	private int start;
	private int end;	
	private String[] data;
}
