package com.ssafy.trip.attrplan.model.service;

import com.ssafy.trip.attrplan.model.AttrplanDto;
import com.ssafy.trip.attrplan.model.AttrplanLikeDto;
import com.ssafy.trip.attrplan.model.AttrplanOrderDto;
import com.ssafy.trip.attrplan.model.mapper.AttrplanMapper;
import com.ssafy.trip.common.ImgUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.sql.SQLException;
import java.util.List;

@Service
public class AttrplanServiceImpl implements AttrplanService {

	private static final Logger log = LoggerFactory.getLogger(AttrplanServiceImpl.class);
	AttrplanMapper attrplanMapper;
	ImgUtils imgUtils;

	@Autowired
	public AttrplanServiceImpl(AttrplanMapper attrplanMapper, ImgUtils imgUtils) {
		super();
		this.attrplanMapper = attrplanMapper;
		this.imgUtils = imgUtils;
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
	public AttrplanDto getAttrplan(int id) throws SQLException {
		return attrplanMapper.getAttrplan(id);
	}

	@Override
	public void registAttrplan(AttrplanDto Attrplan, MultipartFile img) throws SQLException {
		String imgPath = "";
		if (img != null && !img.isEmpty()) {
			imgPath = imgUtils.saveImage(img, "board");
		}
		Attrplan.setImg(imgPath);
		attrplanMapper.registAttrplan(Attrplan);
	}

	@Override
	@Transactional
	public void updateAttrplan(AttrplanDto Attrplan) throws SQLException {
		attrplanMapper.updateAttrplan(Attrplan);
	}

	@Override
	public void deleteAttrplan(int id) throws SQLException {
		attrplanMapper.deleteAttrplan(id);
	}

	@Override
	public void likeAttrplan(AttrplanLikeDto AttrplanLike) throws SQLException {
		attrplanMapper.likeAttrplan(AttrplanLike);
		attrplanMapper.AddLike(AttrplanLike.getPlans_id());
	}

	@Override
	public void dislikeAttrplan(AttrplanLikeDto AttrplanLike) throws SQLException {
		attrplanMapper.dislikeAttrplan(AttrplanLike);
		attrplanMapper.SubLike(AttrplanLike.getPlans_id());
	}

	@Override
	public int getAttrplanLike(AttrplanLikeDto AttrplanLike) throws SQLException {
		return attrplanMapper.getAttrplanLike(AttrplanLike);
	}

	@Override
	public int getUserid(String user_id) throws SQLException {
		return attrplanMapper.getUserid(user_id);
	}

	@Override
	@Transactional
	public void Add2Attrplan(AttrplanOrderDto[] AttrplanOrder, int plans_id) throws SQLException {
		for (int i = 0; i < AttrplanOrder.length; i++) {
			AttrplanOrderDto attrplanOrder = AttrplanOrderDto.builder()
					.order(AttrplanOrder[i].getOrder())
					.plans_id(plans_id)
					.attraction_info_id(AttrplanOrder[i].getAttraction_info_id())
					.build();
			attrplanMapper.Add2Attrplan(attrplanOrder);
		}
	}

	@Override
	public void Del2Attrplan(int plans_id) throws SQLException {
		attrplanMapper.Del2Attrplan(plans_id);
	}
}