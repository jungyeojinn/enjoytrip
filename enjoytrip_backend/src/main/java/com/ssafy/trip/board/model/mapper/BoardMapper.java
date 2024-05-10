package com.ssafy.trip.board.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.ssafy.trip.board.model.BoardDto;

@Mapper
public interface BoardMapper {
	List<BoardDto> boardList()  throws SQLException ;
	BoardDto getBoard(int id)  throws SQLException ;
	void registBoard(BoardDto board) throws SQLException ;
	void updateBoard(BoardDto board) throws SQLException ;
	void deleteBoard(int id) throws SQLException ;
	
	void updateHit(int id) throws SQLException ;
}
