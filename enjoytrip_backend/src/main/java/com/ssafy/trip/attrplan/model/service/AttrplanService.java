package com.ssafy.trip.attrplan.model.service;

import com.ssafy.trip.attrplan.model.AttrplanDto;
import com.ssafy.trip.attrplan.model.AttrplanLikeDto;
import com.ssafy.trip.attrplan.model.AttrplanOrderDto;
import org.apache.ibatis.jdbc.SQL;

import java.sql.SQLException;
import java.util.List;

public interface AttrplanService {
	List<AttrplanDto> AttrplanList()  throws SQLException ;
	List<AttrplanDto> AttrplanListbyId(int user_id) throws SQLException ;

	AttrplanDto getAttrplan(int id)  throws SQLException ;
	void registAttrplan(AttrplanDto Attrplan) throws SQLException ;
	void updateAttrplan(AttrplanDto Attrplan) throws SQLException ;
	void deleteAttrplan(int id) throws SQLException ;

	void likeAttrplan(AttrplanLikeDto AttrplanLike) throws SQLException;
	void dislikeAttrplan(AttrplanLikeDto AttrplanLike) throws SQLException;
	int getAttrplanLike(AttrplanLikeDto AttrplanLike) throws SQLException;
//	void updateHit(int id)  throws SQLException ;

	int getUserid(String user_id) throws SQLException;

	void Add2Attrplan(AttrplanOrderDto AttrplanOrder) throws SQLException;
	void Del2Attrplan(int attr_id) throws SQLException;
}