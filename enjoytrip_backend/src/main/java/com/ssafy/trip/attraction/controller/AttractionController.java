package com.ssafy.trip.attraction.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.trip.attraction.model.service.AttractionService;

@RestController
@RequestMapping("/attraction")
public class AttractionController {
	
	private final AttractionService attractionService;
	
	public AttractionController(AttractionService attractionService) {
		super();
		this.attractionService = attractionService;
	}
	
	@GetMapping("/areacode/{sido}")
	public ResponseEntity<?> areaCode(@PathVariable("sido") int sidoCode) {
		return ResponseEntity.ok().body(attractionService.areaCode(sidoCode));
	}
	
	@GetMapping("/list")
	public ResponseEntity<?> attractionList(@RequestParam(value = "sidocode", required = false, defaultValue="0") int sidoCode,
			@RequestParam(value = "guguncode", required = false, defaultValue="0") int gugunCode,
			@RequestParam(value = "contenttypeid", required = false) int[] contentTypeId) {
		return ResponseEntity.ok().body(attractionService.list(sidoCode, gugunCode, contentTypeId));
	}
}
