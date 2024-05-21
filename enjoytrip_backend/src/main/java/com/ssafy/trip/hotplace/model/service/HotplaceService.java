package com.ssafy.trip.hotplace.model.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ssafy.trip.hotplace.model.HotplaceDto;
import com.ssafy.trip.hotplace.model.HotplaceLikeDto;

public interface HotplaceService {
	void insertHotplace(HotplaceDto hotplace, MultipartFile img);
	List<HotplaceDto> listHotplaces();
	List<HotplaceDto> userHotplaces(int id);
	HotplaceDto getHotplace(int id);
	void updateHotplace(int id, HotplaceDto hotplace, MultipartFile img);
	void deleteHotplace(int id);
	
	void like(HotplaceLikeDto hotplaceLike);
	void unlike(HotplaceLikeDto hotplaceLike);
	void plusLikeCount(int id);
	void minusLikeCount(int id);
	
}
