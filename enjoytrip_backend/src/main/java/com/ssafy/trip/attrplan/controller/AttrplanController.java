package com.ssafy.trip.attrplan.controller;

import com.ssafy.trip.attrplan.model.AttrplanDto;
import com.ssafy.trip.attrplan.model.service.AttrplanService;
import com.ssafy.trip.exception.InvalidInputException;
import com.ssafy.trip.exception.ResourceNotFoundException;
import com.ssafy.trip.exception.dto.BaseResponseDto;
import com.ssafy.trip.exception.util.BaseResponseCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/attrplan")
public class AttrplanController {
    private static final Logger log = LoggerFactory.getLogger(AttrplanController.class);

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

    @PostMapping("/")
    public ResponseEntity<?> registAttrplan(
            @RequestBody(required = true) AttrplanDto attrplanDto
    ) throws SQLException {
        attrplanService.registAttrplan(attrplanDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAttrplan(
            @PathVariable("id") int plans_id
    ) throws SQLException {
        AttrplanDto attrplanDto = attrplanService.getAttrplan(plans_id);
        return ResponseEntity.ok().body(attrplanDto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AttrplanDto> updateAttrplan(
            @PathVariable("id") int plans_id,
            @RequestBody Map<String, Object> updateAttrplanDto
    ) throws Exception {
        AttrplanDto attrplanDto = attrplanService.getAttrplan(plans_id);
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
            @PathVariable("id") int plans_id
    ) throws SQLException {
        attrplanService.deleteAttrplan(plans_id);
        return ResponseEntity.ok().build();
    }

}
