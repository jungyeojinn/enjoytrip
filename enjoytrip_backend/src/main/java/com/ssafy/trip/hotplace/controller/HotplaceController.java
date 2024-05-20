package com.ssafy.trip.hotplace.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.trip.attrplan.model.AttrplanLikeDto;
import com.ssafy.trip.exception.AuthorizationFailedException;
import com.ssafy.trip.exception.InvalidInputException;
import com.ssafy.trip.exception.util.BaseResponseCode;
import com.ssafy.trip.hotplace.model.HotplaceDto;
import com.ssafy.trip.hotplace.model.HotplaceLikeDto;
import com.ssafy.trip.hotplace.model.service.HotplaceService;
import com.ssafy.trip.user.model.UserDto;
import com.ssafy.trip.user.model.service.UserService;

@RestController
@CrossOrigin
@RequestMapping("/hotplace")
public class HotplaceController {
	
	private final HotplaceService hotplaceService;
	private final UserService userService;

	public HotplaceController(HotplaceService hotplaceService, UserService userService) {
		super();
		this.hotplaceService = hotplaceService;
		this.userService = userService;
	}
	
	@PostMapping("/")
	public ResponseEntity<?> writeHotplace(@RequestBody HotplaceDto hotplace) {
		System.out.println(hotplace);
		hotplaceService.insertHotplace(hotplace);
		return ResponseEntity.ok().body("핫플레이스 등록 성공");
	}
	
	@GetMapping("/")
	public ResponseEntity<?> listHotplaces() {
		List<HotplaceDto> list = hotplaceService.listHotplaces();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<?> userHotplaces(@PathVariable("id") int id) {
		List<HotplaceDto> list = hotplaceService.userHotplaces(id);
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getHotplace(@PathVariable("id") int id) {
		HotplaceDto hotplace = hotplaceService.getHotplace(id);
		return ResponseEntity.ok().body(hotplace);
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<?> modifyHotplace(@PathVariable("id") int id, @RequestBody HotplaceDto hotplace) {
		if (id != hotplace.getId()) {
			throw new InvalidInputException(BaseResponseCode.INVALID_INPUT);
		}
		hotplaceService.updateHotplace(hotplace);
		return ResponseEntity.ok().body("핫플레이스 수정 성공");
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteHotplace(@PathVariable("id") int id) {
		hotplaceService.deleteHotplace(id);
		return ResponseEntity.ok().body("핫플레이스 삭제 성공");
	}
	
	@PostMapping("/like/{id}")
    public ResponseEntity<?> plusLike(
            @PathVariable("id") int hotplaceId,
            @RequestBody Map<String, String> userId
    ) throws SQLException {
        int id;
        try {
            id = userService.getUser(userId.get("userId")).getId();
        } catch (Exception e) {
            throw new AuthorizationFailedException(BaseResponseCode.AUTHORIZATION_FAILED);
        }
        HotplaceLikeDto hotplaceLike = HotplaceLikeDto.builder()
        		.userId(id)
        		.hotplacesId(hotplaceId)
        		.build();
        hotplaceService.like(hotplaceLike);
        return ResponseEntity.ok().build();
    }

	@PostMapping("/unlike/{id}")
    public ResponseEntity<?> minusLike(
            @PathVariable("id") int hotplaceId,
            @RequestBody Map<String, String> userId
    ) throws SQLException {
        int id;
        try {
            id = userService.getUser(userId.get("userId")).getId();
        } catch (Exception e) {
            throw new AuthorizationFailedException(BaseResponseCode.AUTHORIZATION_FAILED);
        }
        HotplaceLikeDto hotplaceLike = HotplaceLikeDto.builder()
        		.userId(id)
        		.hotplacesId(hotplaceId)
        		.build();
        hotplaceService.unlike(hotplaceLike);
        return ResponseEntity.ok().build();
    }
}
