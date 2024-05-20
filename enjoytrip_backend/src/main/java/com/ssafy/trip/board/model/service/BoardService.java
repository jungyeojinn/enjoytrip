package com.ssafy.trip.board.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.trip.board.model.BoardDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardService {
	Page<BoardDto> boardList(Pageable pageable, int offset, int pageSize)  throws SQLException ;
	BoardDto getBoard(int id)  throws SQLException ;
	void registBoard(BoardDto board) throws SQLException ;
	void updateBoard(BoardDto board) throws SQLException ;
	void deleteBoard(int id) throws SQLException ;
	void deleteBoards(int[] ids)throws SQLException ;
	
	void updateHit(int id)  throws SQLException ;
	
	BoardDto getBoardWithHit(int id)  throws SQLException ;
}