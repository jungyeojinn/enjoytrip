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
	
	@GetMapping("/areacode/{sido}")
	public ResponseEntity<?> getAreaCode(@PathVariable("sido") int sidoCode) {
		List<AreaCodeDto> areaCodes = attractionService.areaCode(sidoCode);
		return ResponseEntity.ok().body(areaCodes);
	}
	
	@GetMapping("/")
	public ResponseEntity<?> getAttraictionInfos(@RequestParam(value = "sidocode", required = false, defaultValue="0") int sidoCode,
			@RequestParam(value = "guguncode", required = false, defaultValue="0") int gugunCode,
			@RequestParam(value = "contenttypeid", required = false) int[] contentTypeId) {
		List<AttractionInfoDto> attractionInfoList = attractionService.list(sidoCode, gugunCode, contentTypeId);
		return ResponseEntity.ok().body(attractionInfoList);
	}
}
