package com.ibm.demo.redis.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.demo.redis.dto.HashesReq;
import com.ibm.demo.redis.service.RedisHashesService;
import com.ibm.demo.redis.vo.HashesReqVO;
import com.ibm.demo.redis.vo.HashesResVO;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
public class RedisHashesController {
	private final RedisHashesService redisHashesService;
	@PostMapping("/hashes/entries")
	@ApiOperation(value = "Item 목록을 Right push", notes="Item 목록을 Right push 합니다.")
	public ResponseEntity<?> getEntries(@RequestBody HashesReq hashesReq) {
		HashesReqVO hashesReqVO = new HashesReqVO();
		HashesResVO hashesResVO = new HashesResVO();
		BeanUtils.copyProperties(hashesReq, hashesReqVO);
		
		Object ret = redisHashesService.getEntries(hashesReqVO);
		
		return ResponseEntity.status(HttpStatus.OK).body(ret);
	}
	
	@PostMapping("/hashes/hset")
	@ApiOperation(value = "Item Put", notes="Item Put합니다.")
    public Object setPut(@RequestBody HashesReq hashesReq) {
		HashesReqVO hashesReqVO = new HashesReqVO();
		HashesResVO hashesResVO = new HashesResVO();
		BeanUtils.copyProperties(hashesReq, hashesReqVO);
		
		Object ret = redisHashesService.setPut(hashesReqVO);
		
		return ResponseEntity.status(HttpStatus.OK).body(ret);
    }
	
	@PostMapping("/hashes/hdel")
	@ApiOperation(value = "Item 삭제", notes="Item 삭제합니다.")
    public Object delete(@RequestBody HashesReq hashesReq) {
		HashesReqVO hashesReqVO = new HashesReqVO();
		HashesResVO hashesResVO = new HashesResVO();
		BeanUtils.copyProperties(hashesReq, hashesReqVO);
		
		Object ret = redisHashesService.delete(hashesReqVO);
		
		return ResponseEntity.status(HttpStatus.OK).body(ret);
    }
	
	@PostMapping("/hashes/keys")
	@ApiOperation(value = "HashKey 목록을 조회", notes="HashKey 목록을 조회")
    public Object values(@RequestBody HashesReq hashesReq) {
		HashesReqVO hashesReqVO = new HashesReqVO();
		HashesResVO hashesResVO = new HashesResVO();
		BeanUtils.copyProperties(hashesReq, hashesReqVO);
		
		Object ret = redisHashesService.keys(hashesReqVO);
		
		return ResponseEntity.status(HttpStatus.OK).body(ret);
    }
    
	@PostMapping("/hashes/values")
	@ApiOperation(value = "HashKey의 values 목록을 조회", notes="HashKey의 values 목록을 조회")
    public Object keys(@RequestBody HashesReq hashesReq) {
		HashesReqVO hashesReqVO = new HashesReqVO();
		HashesResVO hashesResVO = new HashesResVO();
		BeanUtils.copyProperties(hashesReq, hashesReqVO);
		
		Object ret = redisHashesService.values(hashesReqVO);
		
		return ResponseEntity.status(HttpStatus.OK).body(ret);
    }  
	
	@PostMapping("/hashes/hget")
	@ApiOperation(value = "HashKey의 value를 조회", notes="HashKey의 value를 조회")
    public Object get(@RequestBody HashesReq hashesReq) {
		HashesReqVO hashesReqVO = new HashesReqVO();
		HashesResVO hashesResVO = new HashesResVO();
		BeanUtils.copyProperties(hashesReq, hashesReqVO);
		
		Object ret = redisHashesService.getValue(hashesReqVO);
		
		return ResponseEntity.status(HttpStatus.OK).body(ret);
    }  

	@PostMapping("/hashes/hmget")
	@ApiOperation(value = "HashKey의 value를 조회", notes="HashKey의 value를 조회")	
    public Object multiGet(@RequestBody HashesReq hashesReq) {
		HashesReqVO hashesReqVO = new HashesReqVO();
		HashesResVO hashesResVO = new HashesResVO();
		BeanUtils.copyProperties(hashesReq, hashesReqVO);
		hashesReq.setHashKey(hashesReq.getHashKey());
		log.info("hashesReqVO:{}", hashesReqVO);
		Object ret = redisHashesService.multiGet(hashesReqVO);
		
		return ResponseEntity.status(HttpStatus.OK).body(ret);
    }  
}
