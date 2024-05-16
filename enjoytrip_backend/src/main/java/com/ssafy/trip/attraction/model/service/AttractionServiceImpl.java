package com.ssafy.trip.attraction.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.trip.attraction.model.AreaCodeDto;
import com.ssafy.trip.attraction.model.AttractionInfoDto;
import com.ssafy.trip.attraction.model.mapper.AttractionMapper;
import com.ssafy.trip.exception.ResourceNotFoundException;
import com.ssafy.trip.exception.util.BaseResponseCode;

@Service
public class AttractionServiceImpl implements AttractionService {
	
	private final AttractionMapper attractionDao;
	
	public AttractionServiceImpl(AttractionMapper attractionDao) {
		super();
		this.attractionDao = attractionDao;
	}
	
	@Override
	public List<AreaCodeDto> areaCode(int sidoCode) {
		System.out.println("areaCode call!!!");
		return attractionDao.areaCode(sidoCode);
	}

	@Override
	public List<AttractionInfoDto> list(int sidoCode, int gugunCode, int[] contentTypeId) {
		return attractionDao.list(sidoCode, gugunCode, contentTypeId);
	}

	@Override
	public AttractionInfoDto getAttractionWithDetail(int contentId) {
		AttractionInfoDto attractionInfo = attractionDao.getAttractionInfoWithDescription(contentId);
		if (attractionInfo == null) {
			throw new ResourceNotFoundException(BaseResponseCode.RESOURCE_NOT_FOUND);
		} else {
			return attractionInfo;
		}
	}
}
