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
	public List<AreaCodeDto> sidoCode() {
		List<AreaCodeDto> sido = attractionDao.sidoCode();
		if (sido == null || sido.size() == 0) {
			throw new ResourceNotFoundException(BaseResponseCode.RESOURCE_NOT_FOUND);
		}
		return sido;
	}


	@Override
	public List<AreaCodeDto> gugunCode(int sidoCode) {
		List<AreaCodeDto> gugun = attractionDao.gugunCode(sidoCode);
		if (gugun == null || gugun.size() == 0) {
			throw new ResourceNotFoundException(BaseResponseCode.RESOURCE_NOT_FOUND);
		}
		return gugun;
	}

	@Override
	public List<AttractionInfoDto> list(int sidoCode, int gugunCode, int[] contentTypeId) {
		List<AttractionInfoDto> attractionInfoList = attractionDao.list(sidoCode, gugunCode, contentTypeId);
		if (attractionInfoList == null || attractionInfoList.size() == 0) {
			throw new ResourceNotFoundException(BaseResponseCode.RESOURCE_NOT_FOUND);
		}
		return attractionInfoList;
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
