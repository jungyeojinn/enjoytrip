package com.ssafy.trip.attrplan.model.mapper;

import com.ssafy.trip.attrplan.model.AttrplanDto;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface AttrplanMapper {
	List<AttrplanDto> AttrplanList()  throws SQLException ;
	AttrplanDto getAttrplan(String title)  throws SQLException ;
	void registAttrplan(AttrplanDto Attrplan) throws SQLException ;
	void updateAttrplan(AttrplanDto Attrplan) throws SQLException ;
	void deleteAttrplan(String title) throws SQLException ;

//	void updateHit(int id) throws SQLException ;
}
