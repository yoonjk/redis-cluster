package com.ibm.demo.redis.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class PushReqDto {
	private String key;
	private List<String> data;
}
