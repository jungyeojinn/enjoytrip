package com.ssafy.trip.notice.model.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.trip.notice.model.NoticeDto;
import com.ssafy.trip.notice.model.mapper.NoticeMapper;

@Service
public class NoticeServiceImpl implements NoticeService{

	private final NoticeMapper noticeDao;

	
	public NoticeServiceImpl(NoticeMapper noticeDao) throws SQLException {
		super();
		this.noticeDao = noticeDao;
	}

	@Override
	@Transactional
	public void writeNotice(NoticeDto noticeDto) throws SQLException {
		noticeDao.writeNotice(noticeDto);
	}
	
	@Override
	public NoticeDto getNotice(int noticeId) throws SQLException {
		return noticeDao.getNotice(noticeId);
	}

	@Override
	public void modifyNotice(NoticeDto noticeDto) throws SQLException {
		noticeDao.modifyNotice(noticeDto);
	}

	@Override
	public void deleteNotice(int noticeId) throws SQLException {
		noticeDao.deleteNotice(noticeId);
	}

	@Override
	public List<NoticeDto> getListNotice() throws SQLException {
		return noticeDao.getListNotice();
	}
}
