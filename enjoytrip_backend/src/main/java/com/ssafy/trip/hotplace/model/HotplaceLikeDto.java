package com.ssafy.trip.hotplace.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class HotplaceLikeDto {
	int id;
	int userId;
	int hotplacesId;
	
	@Builder
	public HotplaceLikeDto(int id, int userId, int hotplacesId) {
		super();
		this.id = id;
		this.userId = userId;
		this.hotplacesId = hotplacesId;
	}
}
