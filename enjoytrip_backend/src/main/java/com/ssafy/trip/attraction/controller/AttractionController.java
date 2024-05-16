package com.ssafy.trip.attraction.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.trip.attraction.model.AreaCodeDto;
import com.ssafy.trip.attraction.model.AttractionInfoDto;
import com.ssafy.trip.attraction.model.service.AttractionService;

@RestController
@CrossOrigin
@RequestMapping("/attraction")
public class AttractionController {
	
	private final AttractionService attractionService;
	
	public AttractionController(AttractionService attractionService) {
		super();
		this.attractionService = attractionService;
	}
	
	@GetMapping("/areacode/")
	public ResponseEntity<?> getSido() {
		List<AreaCodeDto> sido = attractionService.sidoCode();
		return ResponseEntity.ok().body(sido);
	}
	
	@GetMapping("/areacode/{sido}")
	public ResponseEntity<?> getGugun(@PathVariable("sido") int sidoCode) {
		List<AreaCodeDto> gugun = attractionService.gugunCode(sidoCode);
		return ResponseEntity.ok().body(gugun);
	}
	
	@GetMapping("/")
	public ResponseEntity<?> getAttraictionInfos(@RequestParam(value = "sidocode", required = false, defaultValue="0") int sidoCode,
			@RequestParam(value = "guguncode", required = false, defaultValue="0") int gugunCode,
			@RequestParam(value = "contenttypeid", required = false) int[] contentTypeId) {
		List<AttractionInfoDto> attractionInfoList = attractionService.list(sidoCode, gugunCode, contentTypeId);
		return ResponseEntity.ok().body(attractionInfoList);
	}
	
	@GetMapping("/{content-id}")
	public ResponseEntity<?> getAttraictionInfos(@PathVariable("content-id") int contentId) {
		AttractionInfoDto attraction = attractionService.getAttractionWithDetail(contentId);
		return ResponseEntity.ok().body(attraction);
	}
}
