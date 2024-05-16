package com.ssafy.trip.hotplace.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.trip.exception.InvalidInputException;
import com.ssafy.trip.exception.ResourceNotFoundException;
import com.ssafy.trip.exception.util.BaseResponseCode;
import com.ssafy.trip.hotplace.model.HotplaceDto;
import com.ssafy.trip.hotplace.model.mapper.HotplaceMapper;

@Service
public class HotplaceServiceImpl implements HotplaceService {

	private final HotplaceMapper hotplaceMapper;

	public HotplaceServiceImpl(HotplaceMapper hotplaceMapper) {
		this.hotplaceMapper = hotplaceMapper;
	}

	@Override
	public void insertHotplace(HotplaceDto hotplace) {
		int result = hotplaceMapper.insertHotplace(hotplace);
		if (result == 0) {
			throw new InvalidInputException(BaseResponseCode.INVALID_INPUT);
		}
	}

	@Override
	public List<HotplaceDto> listHotplaces() {
		List<HotplaceDto> list = hotplaceMapper.listHotplaces();
		if (list == null || list.size() == 0) {
			throw new ResourceNotFoundException(BaseResponseCode.RESOURCE_NOT_FOUND);
		} else {
			return list;
		}
	}

	@Override
	public List<HotplaceDto> userHotplaces(int id) {
		List<HotplaceDto> list = hotplaceMapper.userHotplaces(id);
		if (list == null || list.size() == 0) {
			throw new ResourceNotFoundException(BaseResponseCode.RESOURCE_NOT_FOUND);
		} else {
			return list;
		}
	}

	@Override
	public HotplaceDto getHotplace(int id) {
		HotplaceDto hotplace = hotplaceMapper.getHotplace(id);
		if (hotplace == null) {
			throw new ResourceNotFoundException(BaseResponseCode.RESOURCE_NOT_FOUND);
		} else {
			return hotplace;
		}
	}

	@Override
	public void updateHotplace(HotplaceDto hotplace) {
		int result = hotplaceMapper.updateHotplace(hotplace);
		if (result == 0) {
			throw new InvalidInputException(BaseResponseCode.INVALID_INPUT);
		}
	}

	@Override
	public void deleteHotplace(int id) {
		int result = hotplaceMapper.deleteHotplace(id);
		if (result == 0) {
			throw new InvalidInputException(BaseResponseCode.INVALID_INPUT);
		}
	}

	@Override
	public void like(int id) {
		int result = hotplaceMapper.plusLikeCount(id);
		if (result == 0) {
			throw new InvalidInputException(BaseResponseCode.INVALID_INPUT);
		}
	}

	@Override
	public void unkike(int id) {
		int result = hotplaceMapper.minusLikeCount(id);
		if (result == 0) {
			throw new InvalidInputException(BaseResponseCode.INVALID_INPUT);
		}
	}

	
}
