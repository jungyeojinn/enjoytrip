package com.ssafy.trip.attrplan.model.service;

import com.ssafy.trip.attrplan.model.AttrplanDto;
import com.ssafy.trip.attrplan.model.mapper.AttrplanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
public class AttrplanServiceImpl implements AttrplanService {
	
	AttrplanMapper attrplanMapper;
	@Autowired
	public AttrplanServiceImpl(AttrplanMapper attrplanMapper) {
		super();
		this.attrplanMapper = attrplanMapper;
	}

	@Override
	public List<AttrplanDto> AttrplanList() throws SQLException {
		return attrplanMapper.AttrplanList();
	}

	@Override
	public AttrplanDto getAttrplan(int plans_id) throws SQLException {
		return attrplanMapper.getAttrplan(plans_id);
	}

	@Override
	public void registAttrplan(AttrplanDto Attrplan) throws SQLException {
		attrplanMapper.registAttrplan(Attrplan);
	}

	@Override
	public void updateAttrplan(AttrplanDto Attrplan) throws SQLException {
		attrplanMapper.updateAttrplan(Attrplan);
	}

	@Override
	public void deleteAttrplan(int plans_id) throws SQLException {
		attrplanMapper.deleteAttrplan(plans_id);
	}
}
