package com.ssafy.trip.board.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.ssafy.trip.board.model.BoardDto;
import com.ssafy.trip.board.model.BoardWithUserDto;

@Mapper
public interface BoardMapper {
	List<BoardWithUserDto> boardList(int offset, int pageSize)  throws SQLException ;
	BoardWithUserDto getBoard(int id)  throws SQLException ;
	void registBoard(BoardDto board) throws SQLException ;
	void updateBoard(BoardDto board) throws SQLException ;
	String getImg(int id) throws SQLException ;
	void deleteBoard(int id) throws SQLException ;
	int getListBoardCount(BoardDto board);
	void updateHit(int id) throws SQLException ;
	boolean existsById(int id) throws Exception;
	int[] getBoardIdsByUserId(int id);
}

