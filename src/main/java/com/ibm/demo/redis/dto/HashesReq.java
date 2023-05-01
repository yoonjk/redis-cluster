package com.ibm.demo.redis.dto;

import com.ibm.demo.redis.common.vo.CommonReq;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class HashesReq extends CommonReq{
	private int start;
	private int end;
	private String[] hashKey;
	private String[] values;
}
