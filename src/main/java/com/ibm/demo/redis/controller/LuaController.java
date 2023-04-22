package com.ibm.demo.redis.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.demo.redis.dto.TransferDto;
import com.ibm.demo.redis.service.LuaService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
public class LuaController {
	private final LuaService luaService;
	
	@PostMapping("/lua")
	@ApiOperation(value = "lua script 실행", notes="Lua script를 실행합니다.")
	public ResponseEntity<?> transfer(@RequestBody TransferDto transfer) {
		luaService.transfer(transfer);

		return ResponseEntity.status(HttpStatus.OK).body("success");
	}
	
	@PostMapping("/lua2")
	@ApiOperation(value = "다양한 Lua Script 실행", notes = "Lua script를 다향하게 실행하기 위한 방법입니다.")
	public ResponseEntity<?> runLua(@RequestBody TransferDto transfer) {
		Object ret = luaService.runLua(transfer);

		return ResponseEntity.status(HttpStatus.OK).body(ret);
	}	
}
