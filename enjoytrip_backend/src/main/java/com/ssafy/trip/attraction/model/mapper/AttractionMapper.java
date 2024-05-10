package com.ssafy.trip.attraction.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.trip.attraction.model.AreaCodeDto;
import com.ssafy.trip.attraction.model.AttractionInfoDto;

@Mapper
public interface AttractionMapper {
	List<AreaCodeDto> areaCode(int sidoCode);
	List<AttractionInfoDto> list(int sidoCode, int gugunCode, int[] contentTypeId); 
}
