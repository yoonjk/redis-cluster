package com.ibm.demo.redis.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.demo.redis.dto.LeaderBoardReqDto;
import com.ibm.demo.redis.dto.RequestLimitDto;
import com.ibm.demo.redis.dto.TransferDto;
import com.ibm.demo.redis.dto.ZRangeByScoreDto;
import com.ibm.demo.redis.service.LuaService;
import com.ibm.demo.redis.vo.LeaderBoardReqVO;
import com.ibm.demo.redis.vo.ZRangeByScoreVO;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
public class LuaController {
	private final LuaService luaService;
	
	@PostMapping("/transfer")
	@ApiOperation(value = "Lua를 이용한 이체 ", notes = "Lua를 이용한 이체하는 방법입니다.")
	public ResponseEntity<?> runLua(@RequestBody TransferDto transfer) {
		Object ret = luaService.runLua(transfer);

		return ResponseEntity.status(HttpStatus.OK).body(ret);
	}	
	
	@PostMapping("/requestLimit")
	@ApiOperation(value = "requestLimit", notes="requestLimit 실행합니다.")
	public ResponseEntity<?> requestLimitRate(@RequestBody RequestLimitDto requestLimitDto) {
		Object ret = luaService.requestLimitRate(requestLimitDto);
		
		return ResponseEntity.status(HttpStatus.OK).body(ret);
	}
	
	@PostMapping("/sortedsets/zrangebyscore")
	@ApiOperation(value = "zrangebyscore", notes="score기준으로 min/max 범위내에서 지정한 개수만큼 조회합니다.")
	public ResponseEntity<?> getZRangeByScore(@RequestBody ZRangeByScoreDto zrangeByScoreDto) {
		ZRangeByScoreVO zrangeByScoreVO = new ZRangeByScoreVO();
		
		BeanUtils.copyProperties(zrangeByScoreDto, zrangeByScoreVO);
		Object ret = luaService.zrangeByScore(zrangeByScoreVO);
		
		return ResponseEntity.status(HttpStatus.OK).body(ret);
	}
	
	@PostMapping("/sortedsets/leaderBoard")
	@ApiOperation(value = "leaderBoard 조회", notes="Lua를 사용하여 leaderBoard 조회 합니다.")
	public ResponseEntity<?> retrieveLeaderBoard(@RequestBody LeaderBoardReqDto leaderBoardReqDto) {

		LeaderBoardReqVO leaderBoardReqVO = new LeaderBoardReqVO();
		BeanUtils.copyProperties(leaderBoardReqDto, leaderBoardReqVO);
		Object ret = luaService.retrieveLeaderBoard(leaderBoardReqVO);
		
		return ResponseEntity.status(HttpStatus.OK).body(ret);
	}	
}
