package com.ibm.demo.redis.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.demo.redis.dto.PushReqDto;
import com.ibm.demo.redis.dto.PushResDto;
import com.ibm.demo.redis.service.RedisListService;
import com.ibm.demo.redis.service.RedisService;
import com.ibm.demo.redis.vo.PushReqVO;
import com.ibm.demo.redis.vo.PushResVO;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
public class RedisListController {
	private final RedisListService redisListService;
	@PostMapping("/list/rpush")
	@ApiOperation(value = "Item 목록을 Right push", notes="Item 목록을 Right push 합니다.")
	public ResponseEntity<?> addRightPushAll(@RequestBody PushReqDto pushReqDto) {
		PushReqVO pushReqVO = new PushReqVO();
		PushResVO pushResVO = new PushResVO();
		PushResDto pushResDto = new PushResDto();
		BeanUtils.copyProperties(pushReqDto, pushReqVO);
		
		log.info("pushReqDto:{}", pushReqDto);
	
		pushReqVO.setData(pushReqDto.getData());

		log.info("pushReqVO:{}", pushReqDto);
		pushResVO = redisListService.addRightPush(pushReqVO);
		BeanUtils.copyProperties(pushResVO, pushResDto);
		
		return ResponseEntity.status(HttpStatus.OK).body(pushResDto);
	}
	
	@PostMapping("/list/lpush")
	@ApiOperation(value = "Item 목록을 Left push", notes="Item 목록을 Left push 합니다.")
	public ResponseEntity<?> addLeftPushAll(@RequestBody PushReqDto pushReqDto) {
		PushReqVO pushReqVO = new PushReqVO();
		PushResVO pushResVO = new PushResVO();
		PushResDto pushResDto = new PushResDto();
		BeanUtils.copyProperties(pushReqDto, pushReqVO);
		
		log.info("pushReqDto:{}", pushReqDto);
	
		pushReqVO.setData(pushReqDto.getData());

		log.info("pushReqVO:{}", pushReqDto);
		pushResVO = redisListService.addLeftPush(pushReqVO);
		BeanUtils.copyProperties(pushResVO, pushResDto);
		
		return ResponseEntity.status(HttpStatus.OK).body(pushResDto);
	}
	
	@PostMapping("/list/range")
	@ApiOperation(value = "Item 목록을 조회", notes="Item 목록을 조회합니다.")
	public ResponseEntity<?> getList(@RequestBody PushReqDto pushReqDto) {
		PushReqVO pushReqVO = new PushReqVO();

		BeanUtils.copyProperties(pushReqDto, pushReqVO);
		
		log.info("pushReqDto:{}", pushReqDto);
		log.info("pushReqVO:{}", pushReqDto);
		pushReqVO.setData(pushReqDto.getData());

		Object items = redisListService.range(pushReqVO);

		
		return ResponseEntity.status(HttpStatus.OK).body(items);
	}
	
	@PostMapping("/list/itemSize")
	@ApiOperation(value = "Item 목록 개수를 조회", notes="Item 목록 개수를 조회합니다.")
	public ResponseEntity<?> getListSize(@RequestBody PushReqDto pushReqDto) {
		PushReqVO pushReqVO = new PushReqVO();

		BeanUtils.copyProperties(pushReqDto, pushReqVO);
		
		log.info("pushReqDto:{}", pushReqDto);
		log.info("pushReqVO:{}", pushReqDto);
		pushReqVO.setData(pushReqDto.getData());

		long len = redisListService.itemLength(pushReqVO);

		return ResponseEntity.status(HttpStatus.OK).body(String.valueOf(len));
	}
	
	@PostMapping("/list/index")
	@ApiOperation(value = "Index 위치의 Item을 조회 ", notes="Index 위치의 Item을 조회합니다.")
	public ResponseEntity<?> getIndex(@RequestBody PushReqDto pushReqDto) {
		PushReqVO pushReqVO = new PushReqVO();

		BeanUtils.copyProperties(pushReqDto, pushReqVO);
		
		log.info("pushReqDto:{}", pushReqDto);
		log.info("pushReqVO:{}", pushReqDto);
		pushReqVO.setData(pushReqDto.getData());

		String item = redisListService.index(pushReqVO);

		return ResponseEntity.status(HttpStatus.OK).body(item);
	}
	
	@PostMapping("/list/indexOf")
	@ApiOperation(value = "Item 의 Index 위치를 조회 ", notes="Item 의 Index 위치를 조회합니다.")
	public ResponseEntity<?> getIndexOf(@RequestBody PushReqDto pushReqDto) {
		PushReqVO pushReqVO = new PushReqVO();

		BeanUtils.copyProperties(pushReqDto, pushReqVO);
		
		log.info("pushReqDto:{}", pushReqDto);
		log.info("pushReqVO:{}", pushReqDto);
		pushReqVO.setData(pushReqDto.getData());

		long index = redisListService.indexOf(pushReqVO);

		return ResponseEntity.status(HttpStatus.OK).body(String.valueOf(index));
	}	
}
