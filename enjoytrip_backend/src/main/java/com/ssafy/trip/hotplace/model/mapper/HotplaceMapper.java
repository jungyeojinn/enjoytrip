package com.ssafy.trip.hotplace.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.trip.hotplace.model.HotplaceDto;
import com.ssafy.trip.hotplace.model.HotplaceLikeDto;

@Mapper
public interface HotplaceMapper {
	int insertHotplace(HotplaceDto hotplace);
	List<HotplaceDto> listHotplaces();
	List<HotplaceDto> userHotplaces(int id);
	HotplaceDto getHotplace(int id);
	int updateHotplace(HotplaceDto hotplace);
	int deleteHotplace(int id);
	int plusLikeCount(int id);
	int minusLikeCount(int id);
	Integer getLike(HotplaceLikeDto hotplaceLike);
	int addLike(HotplaceLikeDto hotplaceLike);
	int deleteLike(HotplaceLikeDto hotplaceLike);
}
