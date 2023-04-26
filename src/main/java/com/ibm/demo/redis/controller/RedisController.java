package com.ibm.demo.redis.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.demo.redis.dto.PushReqDto;
import com.ibm.demo.redis.dto.PushResDto;
import com.ibm.demo.redis.dto.RedisData;
import com.ibm.demo.redis.dto.RedisDataRes;
import com.ibm.demo.redis.service.RedisListService;
import com.ibm.demo.redis.service.RedisService;
import com.ibm.demo.redis.vo.PushReqVO;
import com.ibm.demo.redis.vo.PushResVO;
import com.ibm.demo.redis.vo.RedisDataResVO;
import com.ibm.demo.redis.vo.RedisDataVO;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class RedisController {

	private final RedisService redisService;
	private final RedisListService redisListService;
	@PostMapping("/register")
	@ApiOperation(value = "key/value를 등록", notes="Key/value(Object)를 등록합니다.")
	public ResponseEntity<?> registRedis(@RequestBody RedisData redisData) {
		RedisDataVO redisDataVO = new RedisDataVO();
		BeanUtils.copyProperties(redisData, redisDataVO);
		redisService.registerData(redisDataVO);
		return ResponseEntity.status(HttpStatus.OK).body("Success");
	}
	
	@GetMapping("/register/{key}")
	@ApiOperation(value = "key조회", notes="Key를 조회해서 Object를 반환합니다.")
	public ResponseEntity<?> getRedis(@PathVariable String key) {
		Object redisDataVO = redisService.retrieveData(key);
		Object redisData = new RedisData();
		BeanUtils.copyProperties(redisDataVO, redisData);
		return ResponseEntity.status(HttpStatus.OK).body(redisData);
	}
	
	@PostMapping("/set")
	@ApiOperation(value = "key등록", notes="Key를 등록합니다.")
	public ResponseEntity<?> setValue(@RequestBody RedisData redisData) {
		RedisDataVO redisDataVO = new RedisDataVO();
		BeanUtils.copyProperties(redisData, redisDataVO);
		RedisDataVO redisDataRes = redisService.setValue(redisDataVO);
		
		return ResponseEntity.status(HttpStatus.OK).body(redisDataRes);
	}
	
	@GetMapping("/get/{key}")
	@ApiOperation(value = "key조회", notes="Key를 조회합니다.")
	public ResponseEntity<?> getValue(@PathVariable String key) {
		RedisDataResVO redisDataResVO = redisService.getValue(key);
		RedisDataRes redisDataRes = new RedisDataRes();
		BeanUtils.copyProperties(redisDataResVO, redisDataRes);
		
		return ResponseEntity.status(HttpStatus.OK).body(redisDataRes);
	}	
	
	@PostMapping("/list/pushAll")
	@ApiOperation(value = "Item 목록을 push", notes="Item 목록을 push 합니다.")
	public ResponseEntity<?> addRightPushAll(@RequestBody PushReqDto pushReqDto) {
		PushReqVO pushReqVO = new PushReqVO();
		PushResVO pushResVO = new PushResVO();
		PushResDto pushResDto = new PushResDto();
		BeanUtils.copyProperties(pushReqDto, pushReqVO);
		
		for (int i = 0; i < pushReqDto.getData().size(); i++) {
			pushReqVO.getData().add(pushReqDto.getData().get(i));
		}
		
		pushResVO = redisListService.addPushAll(pushReqVO);
		BeanUtils.copyProperties(pushResVO, pushResDto);
		
		return ResponseEntity.status(HttpStatus.OK).body(pushResDto);
	}
}
