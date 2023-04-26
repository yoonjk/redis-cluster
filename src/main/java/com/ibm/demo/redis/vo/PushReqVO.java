package com.ibm.demo.redis.vo;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class PushReqVO {
	private String key;
	private List<String> data;
}
