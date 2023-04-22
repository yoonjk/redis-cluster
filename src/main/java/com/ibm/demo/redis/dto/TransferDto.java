package com.ibm.demo.redis.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class TransferDto {
	private String fromAccount;
    private String toAccount;
	private int amount;
}
