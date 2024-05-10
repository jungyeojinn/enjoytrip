package com.ssafy.trip.board.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.trip.board.model.BoardDto;

public interface BoardService {
	List<BoardDto> boardList()  throws SQLException ;
	BoardDto getBoard(int id)  throws SQLException ;
	void registBoard(BoardDto board) throws SQLException ;
	void updateBoard(BoardDto board) throws SQLException ;
	void deleteBoard(int id) throws SQLException ;
	void deleteBoards(int[] ids)throws SQLException ;
	
	void updateHit(int id)  throws SQLException ;
}