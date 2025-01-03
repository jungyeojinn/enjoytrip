package com.ssafy.trip.attrplan.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.trip.attrplan.model.AttrplanDto;
import com.ssafy.trip.attrplan.model.AttrplanLikeDto;
import com.ssafy.trip.attrplan.model.AttrplanOrderDto;
import org.springframework.web.multipart.MultipartFile;

public interface AttrplanService {
	List<AttrplanDto> AttrplanList()  throws SQLException ;
	List<AttrplanDto> AttrplanListbyId(int user_id) throws SQLException ;

	AttrplanDto getAttrplan(int id)  throws SQLException ;
	int registAttrplan(AttrplanDto Attrplan, MultipartFile img) throws SQLException ;
	void updateAttrplan(AttrplanDto Attrplan) throws SQLException ;
	void deleteAttrplan(int id) throws SQLException ;

	void likeAttrplan(AttrplanLikeDto AttrplanLike) throws SQLException;
	void dislikeAttrplan(AttrplanLikeDto AttrplanLike) throws SQLException;
	int getAttrplanLike(AttrplanLikeDto AttrplanLike) throws SQLException;
//	void updateHit(int id)  throws SQLException ;

	int getUserid(String user_id) throws SQLException;

	void Add2Attrplan(AttrplanOrderDto[] AttrplanOrder, int plans_id) throws SQLException;
	void Del2Attrplan(int plans_id) throws SQLException;
	
	AttrplanOrderDto[] getPlanOrder(int plansId);
}