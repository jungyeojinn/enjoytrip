package com.ssafy.trip.notice.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.trip.notice.model.NoticeDto;

public interface NoticeService {

	List<NoticeDto> getListNotice() throws SQLException;

	void writeNotice(NoticeDto noticeDto) throws SQLException;

	NoticeDto getNotice(int noticeId) throws SQLException;

	void modifyNotice(NoticeDto noticeDto) throws SQLException;

	void deleteNotice(int noticeId) throws SQLException;

}