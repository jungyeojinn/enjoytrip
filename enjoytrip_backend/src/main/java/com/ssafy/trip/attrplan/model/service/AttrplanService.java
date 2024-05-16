package com.ssafy.trip.attrplan.model.service;

import com.ssafy.trip.attrplan.model.AttrplanDto;

import java.sql.SQLException;
import java.util.List;

public interface AttrplanService {
	List<AttrplanDto> AttrplanList()  throws SQLException ;
	AttrplanDto getAttrplan(int plans_id)  throws SQLException ;
	void registAttrplan(AttrplanDto Attrplan) throws SQLException ;
	void updateAttrplan(AttrplanDto Attrplan) throws SQLException ;
	void deleteAttrplan(int plans_id) throws SQLException ;

//	void updateHit(int id)  throws SQLException ;
	
}