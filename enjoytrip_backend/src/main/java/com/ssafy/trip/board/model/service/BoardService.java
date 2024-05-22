package com.ssafy.trip.board.model.service;

import java.sql.SQLException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.trip.board.model.BoardDto;

public interface BoardService {
	Page<BoardDto> boardList( int offset, int pageSize)  throws SQLException ;
	BoardDto getBoard(int id) throws Exception;
	void registBoard(BoardDto board, MultipartFile img) throws SQLException ;
	void updateBoard(BoardDto board, MultipartFile img) throws Exception;
	void deleteBoard(int id) throws Exception;
	void deleteBoards(int[] ids) throws Exception;

	void updateHit(int id) throws Exception;

	BoardDto getBoardWithHit(int id)  throws SQLException, Exception;
}
