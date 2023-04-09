package com.ibm.demo.redis.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.demo.redis.dto.RedisData;
import com.ibm.demo.redis.service.RedisService;
import com.ibm.demo.redis.vo.RedisDataVO;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class RedisController {

	private final RedisService redisService;
	
	@PostMapping("/register")
	@ApiOperation(value = "key등", notes="Key를 등록합니다.")
	public ResponseEntity<?> registRedis(@RequestBody RedisData redisData) {
		RedisDataVO redisDataVO = new RedisDataVO();
		BeanUtils.copyProperties(redisData, redisDataVO);
		redisService.registerData(redisDataVO);
		return ResponseEntity.status(HttpStatus.OK).body("Success");
	}
	
	@GetMapping("/register/{key}")
	public ResponseEntity<?> getRedis(@PathVariable String key) {
		RedisDataVO redisDataVO = redisService.retrieveData(key);
		RedisData redisData = new RedisData();
		BeanUtils.copyProperties(redisDataVO, redisData);
		return ResponseEntity.status(HttpStatus.OK).body(redisData);
	}
}
