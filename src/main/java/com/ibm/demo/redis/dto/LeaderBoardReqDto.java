package com.ibm.demo.redis.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class LeaderBoardReqDto {
	private String key;
	private String user;
	private int count;
}
