package com.ssafy.trip.attrplan.model.service;

import com.ssafy.trip.attrplan.model.AttrplanDto;
import com.ssafy.trip.attrplan.model.mapper.AttrplanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
public class AttrplanServiceImpl implements AttrplanService {

	private static final Logger log = LoggerFactory.getLogger(AttrplanServiceImpl.class);
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
	public List<AttrplanDto> AttrplanListbyId(int user_id) throws SQLException{
		return attrplanMapper.AttrplanListbyId(user_id);
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
	@Transactional
	public void updateAttrplan(AttrplanDto Attrplan) throws SQLException {
		attrplanMapper.updateAttrplan(Attrplan);
	}

	@Override
	public void deleteAttrplan(int plans_id) throws SQLException {
		attrplanMapper.deleteAttrplan(plans_id);
	}
}
