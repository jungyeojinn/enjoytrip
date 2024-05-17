package com.ssafy.trip.attrplan.model.mapper;

import com.ssafy.trip.attrplan.model.AttrplanDto;
import com.ssafy.trip.attrplan.model.AttrplanLikeDto;
import com.ssafy.trip.attrplan.model.AttrplanOrderDto;
import org.apache.ibatis.annotations.Mapper;
import org.w3c.dom.Attr;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface AttrplanMapper {
	List<AttrplanDto> AttrplanList()  throws SQLException ;
	List<AttrplanDto> AttrplanListbyId(int user_id) throws SQLException;

	AttrplanDto getAttrplan(int id)  throws SQLException ;
	void registAttrplan(AttrplanDto Attrplan) throws SQLException ;
	int updateAttrplan(AttrplanDto Attrplan) throws SQLException ;
	void deleteAttrplan(int id) throws SQLException ;

	void likeAttrplan(AttrplanLikeDto AttrplanLike) throws SQLException;
	void AddLike(int id) throws SQLException;
	void dislikeAttrplan(AttrplanLikeDto AttrplanLike) throws SQLException;
	void SubLike(int id) throws SQLException;
	int getAttrplanLike(AttrplanLikeDto AttrplanLike) throws SQLException;

	int getUserid(String user_id) throws SQLException;
//	void updateHit(int id) throws SQLException ;

	// 순서..?
	// 1개의 plans 안에 n개의 attraction_info가 포함 -> 순서가 존재할 것이다.
	void Add2Attrplan(AttrplanOrderDto AttrplanOrder) throws SQLException;
	void Del2Attrplan(int attr_id) throws SQLException;
}