package com.ssafy.trip.notice.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.trip.notice.model.NoticeDto;

@Mapper
public interface NoticeMapper {

	List<NoticeDto> getListNotice() throws SQLException;

	void writeNotice(NoticeDto noticeDto) throws SQLException;

	NoticeDto getNotice(int noticeId) throws SQLException;

	void modifyNotice(NoticeDto noticeDto) throws SQLException;

	void deleteNotice(int noticeId) throws SQLException;

}
