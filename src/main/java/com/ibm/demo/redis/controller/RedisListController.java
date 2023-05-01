package com.ibm.demo.redis.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.demo.redis.dto.PushReq;
import com.ibm.demo.redis.dto.PushRes;
import com.ibm.demo.redis.service.RedisListService;
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
	public ResponseEntity<?> addRightPush(@RequestBody PushReq pushReqDto) {
		PushReqVO pushReqVO = new PushReqVO();
		PushResVO pushResVO = new PushResVO();
		PushRes pushResDto = new PushRes();
		BeanUtils.copyProperties(pushReqDto, pushReqVO);
		
		log.info("pushReqDto:{}", pushReqDto);
	
		pushReqVO.setData(pushReqDto.getData());

		log.info("pushReqVO:{}", pushReqDto);
		pushResVO = redisListService.addRightPush(pushReqVO);
		BeanUtils.copyProperties(pushResVO, pushResDto);
		
		return ResponseEntity.status(HttpStatus.OK).body(pushResDto);
	}
	
	@PostMapping("/list/rpushAll")
	@ApiOperation(value = "Items 목록을 Right push", notes="Items 목록을 Right push 합니다.")
	public ResponseEntity<?> addRightPushAll(@RequestBody PushReq pushReqDto) {
		PushReqVO pushReqVO = new PushReqVO();
		PushResVO pushResVO = new PushResVO();
		PushRes pushResDto = new PushRes();
		BeanUtils.copyProperties(pushReqDto, pushReqVO);
		
		log.info("pushReqDto:{}", pushReqDto);
	
		pushReqVO.setData(pushReqDto.getData());

		log.info("pushReqVO:{}", pushReqDto);
		long ret = redisListService.addRightPushAll(pushReqVO);
		
		return ResponseEntity.status(HttpStatus.OK).body(String.valueOf(ret));
	}	
	
	@PostMapping("/list/lpushAll")
	@ApiOperation(value = "Items 목록을 Left push", notes="Items 목록을 Left push 합니다.")
	public ResponseEntity<?> addLeftPushAll(@RequestBody PushReq pushReqDto) {
		PushReqVO pushReqVO = new PushReqVO();
		PushResVO pushResVO = new PushResVO();
		PushRes pushResDto = new PushRes();
		BeanUtils.copyProperties(pushReqDto, pushReqVO);
		
		log.info("pushReqDto:{}", pushReqDto);
	
		pushReqVO.setData(pushReqDto.getData());

		log.info("pushReqVO:{}", pushReqDto);
		long ret = redisListService.addLeftPushAll(pushReqVO);
		
		return ResponseEntity.status(HttpStatus.OK).body(String.valueOf(ret));
	}	
	
	@PostMapping("/list/lpush")
	@ApiOperation(value = "Item 목록을 Left push", notes="Item 목록을 Left push 합니다.")
	public ResponseEntity<?> addLeftPushl(@RequestBody PushReq pushReqDto) {
		PushReqVO pushReqVO = new PushReqVO();
		PushResVO pushResVO = new PushResVO();
		PushRes pushResDto = new PushRes();
		BeanUtils.copyProperties(pushReqDto, pushReqVO);
		
		log.info("pushReqDto:{}", pushReqDto);
	
		pushReqVO.setData(pushReqDto.getData());

		log.info("pushReqVO:{}", pushReqDto);
		pushResVO = redisListService.addLeftPush(pushReqVO);
		BeanUtils.copyProperties(pushResVO, pushResDto);
		
		return ResponseEntity.status(HttpStatus.OK).body(pushResDto);
	}
	
	@PostMapping("/list/lrange")
	@ApiOperation(value = "Item 목록을 조회", notes="Item 목록을 조회합니다.")
	public ResponseEntity<?> lrange(@RequestBody PushReq pushReqDto) {
		PushReqVO pushReqVO = new PushReqVO();

		BeanUtils.copyProperties(pushReqDto, pushReqVO);
		
		log.info("pushReqDto:{}", pushReqDto);
		log.info("pushReqVO:{}", pushReqDto);
		pushReqVO.setData(pushReqDto.getData());

		Object items = redisListService.lrange(pushReqVO);

		
		return ResponseEntity.status(HttpStatus.OK).body(items);
	}
	
	@PostMapping("/list/llen")
	@ApiOperation(value = "Item 목록 개수를 조회", notes="Item 목록 개수를 조회합니다.")
	public ResponseEntity<?> getListSize(@RequestBody PushReq pushReqDto) {
		PushReqVO pushReqVO = new PushReqVO();

		BeanUtils.copyProperties(pushReqDto, pushReqVO);
		
		log.info("pushReqDto:{}", pushReqDto);
		log.info("pushReqVO:{}", pushReqDto);
		pushReqVO.setData(pushReqDto.getData());

		long len = redisListService.llen(pushReqVO);

		return ResponseEntity.status(HttpStatus.OK).body(String.valueOf(len));
	}
	
	@PostMapping("/list/lindex")
	@ApiOperation(value = "Index 위치의 Item을 조회 ", notes="Index 위치의 Item을 조회합니다.")
	public ResponseEntity<?> getIndex(@RequestBody PushReq pushReqDto) {
		PushReqVO pushReqVO = new PushReqVO();

		BeanUtils.copyProperties(pushReqDto, pushReqVO);
		
		log.info("pushReqDto:{}", pushReqDto);
		log.info("pushReqVO:{}", pushReqDto);
		pushReqVO.setData(pushReqDto.getData());

		String item = redisListService.lindex(pushReqVO);

		return ResponseEntity.status(HttpStatus.OK).body(item);
	}
	
	@PostMapping("/list/lpos")
	@ApiOperation(value = "Item 의 Index 위치를 조회 ", notes="Item 의 Index 위치를 조회합니다.")
	public ResponseEntity<?> getIndexOf(@RequestBody PushReq pushReqDto) {
		PushReqVO pushReqVO = new PushReqVO();

		BeanUtils.copyProperties(pushReqDto, pushReqVO);
		
		log.info("pushReqDto:{}", pushReqDto);
		log.info("pushReqVO:{}", pushReqDto);
		pushReqVO.setData(pushReqDto.getData());

		long index = redisListService.lpos(pushReqVO);

		return ResponseEntity.status(HttpStatus.OK).body(String.valueOf(index));
	}
	
	@PostMapping("/list/ltrim")
	@ApiOperation(value = "Item을 trim", notes="Item을 trim 합니다.")
	public ResponseEntity<?> trim(@RequestBody PushReq pushReqDto) {
		PushReqVO pushReqVO = new PushReqVO();
		PushRes pushRes = new PushRes();
		BeanUtils.copyProperties(pushReqDto, pushReqVO);
		
		log.info("pushReqDto:{}", pushReqDto);
		log.info("pushReqVO:{}", pushReqDto);
		pushReqVO.setData(pushReqDto.getData());

		PushResVO pushResVO = redisListService.trim(pushReqVO);
		BeanUtils.copyProperties(pushResVO, pushRes);
		
		return ResponseEntity.status(HttpStatus.OK).body(String.valueOf(pushRes));
	}
	
	@PostMapping("/list/lpop")
	@ApiOperation(value = "Left item을 반환", notes="Left item을 반환합니다.")
	public ResponseEntity<?> lpop(@RequestBody PushReq pushReqDto) {
		PushReqVO pushReqVO = new PushReqVO();
		BeanUtils.copyProperties(pushReqDto, pushReqVO);
		
		log.info("pushReqDto:{}", pushReqDto);
		log.info("pushReqVO:{}", pushReqDto);
		pushReqVO.setData(pushReqDto.getData());

		String item = redisListService.lpop(pushReqVO);
		
		return ResponseEntity.status(HttpStatus.OK).body(item);
	}	

	@PostMapping("/list/rpop")
	@ApiOperation(value = "Right item을 반환", notes="Left item을 반환합니다.")
	public ResponseEntity<?> rpop(@RequestBody PushReq pushReqDto) {
		PushReqVO pushReqVO = new PushReqVO();
		BeanUtils.copyProperties(pushReqDto, pushReqVO);
		
		log.info("pushReqDto:{}", pushReqDto);
		log.info("pushReqVO:{}", pushReqDto);
		pushReqVO.setData(pushReqDto.getData());

		String item = redisListService.rpop(pushReqVO);
		
		return ResponseEntity.status(HttpStatus.OK).body(item);
	}

	@PostMapping("/list/lset")
	@ApiOperation(value = "Right item을 반환", notes="Left item을 반환합니다.")
	public ResponseEntity<?> lset(@RequestBody PushReq pushReqDto) {
		PushReqVO pushReqVO = new PushReqVO();
		PushRes pushRes = new PushRes();
		BeanUtils.copyProperties(pushReqDto, pushReqVO);
		
		log.info("pushReqDto:{}", pushReqDto);
		log.info("pushReqVO:{}", pushReqDto);
		pushReqVO.setData(pushReqDto.getData());

		PushResVO pushResVO = redisListService.lset(pushReqVO);
		BeanUtils.copyProperties(pushResVO, pushRes);
		
		return ResponseEntity.status(HttpStatus.OK).body(String.valueOf(pushRes));
	}
	
	@PostMapping("/list/lrem")
	@ApiOperation(value = "Left item을 삭제", notes="Left item을 삭제합니다.")
	public ResponseEntity<?> lrem(@RequestBody PushReq pushReqDto) {
		PushReqVO pushReqVO = new PushReqVO();
		PushRes pushRes = new PushRes();
		BeanUtils.copyProperties(pushReqDto, pushReqVO);
		
		log.info("pushReqDto:{}", pushReqDto);
		log.info("pushReqVO:{}", pushReqDto);
		pushReqVO.setData(pushReqDto.getData());

		long ret = redisListService.lrem(pushReqVO);
	
		return ResponseEntity.status(HttpStatus.OK).body(String.valueOf(ret));
	}
		
}
