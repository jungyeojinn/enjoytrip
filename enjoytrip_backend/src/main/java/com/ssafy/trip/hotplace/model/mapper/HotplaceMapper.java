package com.ssafy.trip.hotplace.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.trip.hotplace.model.HotplaceDto;

@Mapper
public interface HotplaceMapper {
	int insertHotplace(HotplaceDto hotplace);
	List<HotplaceDto> list();
	List<HotplaceDto> userHotplace();
	HotplaceDto getHotplace(int id);
	int updateHotplace(HotplaceDto hotplace);
	int deleteHotplace(int id);
	int addLikeCount(int id);
}
