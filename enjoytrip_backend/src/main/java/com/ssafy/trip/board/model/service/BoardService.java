package com.ssafy.trip.board.model.service;

import java.sql.SQLException;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.trip.board.model.BoardDto;
import com.ssafy.trip.board.model.BoardWithUserDto;

public interface BoardService {
	Page<BoardWithUserDto> boardList( int offset, int pageSize)  throws Exception;
	BoardWithUserDto getBoard(int id) throws Exception;
	void registBoard(BoardDto board, MultipartFile img) throws SQLException ;
	void updateBoard(BoardDto board, MultipartFile img) throws Exception;
	void deleteBoard(int id) throws Exception;
	void deleteBoards(int[] ids) throws Exception;

	void updateHit(int id) throws Exception;

	BoardWithUserDto getBoardWithHit(int id)  throws Exception;
	void deActivateFromUser(int id) throws Exception;
}
