package com.ibm.demo.redis.vo;

import com.ibm.demo.redis.common.vo.CommonReq;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class HashesReqVO extends CommonReq{
	private int start;
	private int end;
	private String[] hashKey;
	private String[] values;	
}
