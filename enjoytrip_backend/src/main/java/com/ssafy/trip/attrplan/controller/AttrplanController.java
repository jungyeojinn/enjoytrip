package com.ssafy.trip.attrplan.controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.trip.attrplan.model.AttrplanDto;
import com.ssafy.trip.attrplan.model.AttrplanLikeDto;
import com.ssafy.trip.attrplan.model.AttrplanOrderDto;
import com.ssafy.trip.attrplan.model.service.AttrplanService;
import com.ssafy.trip.exception.AuthorizationFailedException;
import com.ssafy.trip.exception.InvalidInputException;
import com.ssafy.trip.exception.ResourceNotFoundException;
import com.ssafy.trip.exception.util.BaseResponseCode;

@RestController
@CrossOrigin
@RequestMapping("/attrplan")
public class AttrplanController {
    //빌더 패턴 사용
//    AttrplanDto attrplan = AttrplanDto.builder()
//            .img("/diflskdfj")
//            .build();

    private final AttrplanService attrplanService;

    public AttrplanController(AttrplanService attractionService) {
        super();
        this.attrplanService = attractionService;
    }

    @GetMapping("/")
    public ResponseEntity<?> AttrplanList() throws SQLException {
        List<AttrplanDto> attrplanDtoList = attrplanService.AttrplanList();
        return ResponseEntity.ok().body(attrplanDtoList);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getAttrplanByUserId(
            @PathVariable("id") int user_id
    ) throws SQLException {
        List<AttrplanDto> attrplanDtoList = attrplanService.AttrplanListbyId(user_id);
        return ResponseEntity.ok().body(attrplanDtoList);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json", consumes = "multipart/form-data")
    public ResponseEntity<?> registAttrplan(@RequestParam(value = "title") String title,
                                            @RequestParam(value = "start_date") String start_date,
                                            @RequestParam(value = "end_date") String end_date,
                                            @RequestParam(value = "user_id") int user_id,
                                            @RequestPart(value = "img", required = false) MultipartFile img) throws SQLException, IOException {
        AttrplanDto attrplanDto = new AttrplanDto(title,start_date,end_date,user_id);
        int id = attrplanService.registAttrplan(attrplanDto, img);
        return ResponseEntity.ok().body(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAttrplan(
            @PathVariable("id") int id
    ) throws SQLException {
        AttrplanDto attrplanDto = attrplanService.getAttrplan(id);
        return ResponseEntity.ok().body(attrplanDto);
    }


    @PatchMapping("/{id}")
    public ResponseEntity<AttrplanDto> updateAttrplan(
            @PathVariable("id") int id,
            @RequestBody Map<String, Object> updateAttrplanDto
    ) throws SQLException {
        AttrplanDto attrplanDto = attrplanService.getAttrplan(id);
        if(attrplanDto != null){
            if(updateAttrplanDto.containsKey("title")){
                attrplanDto.setTitle((String)updateAttrplanDto.get("title"));
            }
            if(updateAttrplanDto.containsKey("start_date")){
                attrplanDto.setStart_date((String)updateAttrplanDto.get("start_date"));
            }
            if(updateAttrplanDto.containsKey("end_date")){
                attrplanDto.setEnd_date((String)updateAttrplanDto.get("end_date"));
            }
            if(updateAttrplanDto.containsKey("img")){
                attrplanDto.setImg((String)updateAttrplanDto.get("img"));
            }
            attrplanService.updateAttrplan(attrplanDto);
        } else throw new ResourceNotFoundException(BaseResponseCode.RESOURCE_NOT_FOUND);
        return new ResponseEntity<>(attrplanDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAttrplan(
            @PathVariable("id") int id
    ) throws SQLException {
        attrplanService.deleteAttrplan(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/like/{id}")
    public ResponseEntity<?> addLikeAttrplan(
            @PathVariable("id") int plans_id,
            @RequestBody Map<String, String> user_id
    ) throws SQLException {
        int id;
        try {
            id = attrplanService.getUserid(user_id.get("user_id"));
        } catch (Exception e) {
            throw new AuthorizationFailedException(BaseResponseCode.AUTHORIZATION_FAILED);
        }
        AttrplanLikeDto attrplanLike = new AttrplanLikeDto(id, plans_id);
        if(attrplanService.getAttrplanLike(attrplanLike)>0){
            throw new InvalidInputException(BaseResponseCode.INVALID_INPUT);
        } else
            attrplanService.likeAttrplan(attrplanLike);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/dislike/{id}")
    public ResponseEntity<?> subLikeAttrplan(
            @PathVariable("id") int plans_id,
            @RequestBody Map<String, String> user_id
    ) throws SQLException {
        int id;
        try {
            id = attrplanService.getUserid(user_id.get("user_id"));
        } catch (Exception e) {
            throw new AuthorizationFailedException(BaseResponseCode.AUTHORIZATION_FAILED);
        }
        AttrplanLikeDto attrplanLike = new AttrplanLikeDto(id, plans_id);
        if(attrplanService.getAttrplanLike(attrplanLike)>0){
            attrplanService.dislikeAttrplan(attrplanLike);
        } else throw new InvalidInputException(BaseResponseCode.INVALID_INPUT);
        return ResponseEntity.ok().build();
    }
    
    @PostMapping("/{id}/add")
    public ResponseEntity<?> addAttr2plan(
            @PathVariable("id") int plans_id,
            @RequestBody AttrplanOrderDto[] attrInfos
    ) throws SQLException {
        attrplanService.Add2Attrplan(attrInfos, plans_id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/sub")
    public ResponseEntity<?> subAttr2plan(
            @PathVariable("id") int plans_id
    ) throws SQLException {
        attrplanService.Del2Attrplan(plans_id);
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/{id}/plans")
	public ResponseEntity<?> getPlanOrder(@PathVariable("id") int id) {
		AttrplanOrderDto[] lists = attrplanService.getPlanOrder(id);
		return ResponseEntity.ok().body(lists);
	}
}
