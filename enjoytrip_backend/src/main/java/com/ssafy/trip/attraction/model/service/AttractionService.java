package com.ssafy.trip.attraction.model.service;

import java.util.List;

import com.ssafy.trip.attraction.model.AreaCodeDto;
import com.ssafy.trip.attraction.model.AttractionInfoDto;

public interface AttractionService {
	List<AreaCodeDto> areaCode(int sidoCode);
	List<AttractionInfoDto> list(int sidoCode, int gugunCode, int[] contentTypeId); 
}
