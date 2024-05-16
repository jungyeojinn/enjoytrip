package com.ssafy.trip.attrplan.model.mapper;

import com.ssafy.trip.attrplan.model.AttrplanDto;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface AttrplanMapper {
	List<AttrplanDto> AttrplanList()  throws SQLException ;
	List<AttrplanDto> AttrplanListbyId(int user_id) throws SQLException;

	AttrplanDto getAttrplan(int plans_id)  throws SQLException ;
	void registAttrplan(AttrplanDto Attrplan) throws SQLException ;
	int updateAttrplan(AttrplanDto Attrplan) throws SQLException ;
	void deleteAttrplan(int plans_id) throws SQLException ;

//	void updateHit(int id) throws SQLException ;
}