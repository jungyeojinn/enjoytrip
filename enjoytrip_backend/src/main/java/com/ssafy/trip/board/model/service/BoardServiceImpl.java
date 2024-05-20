package com.ssafy.trip.board.model.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.trip.board.model.BoardDto;
import com.ssafy.trip.board.model.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {
	
	BoardMapper boardMapper;
	@Autowired
	public BoardServiceImpl(BoardMapper boardDao) {
		super();
		this.boardMapper = boardDao;
	}

	@Override
	public Page<BoardDto> boardList( Pageable pageable, int pageNum, int pageSize) throws SQLException {
		BoardDto board = new BoardDto();
		int offset=pageNum*pageSize;
		List<BoardDto> content = boardMapper.boardList(offset, pageSize);

		int total = boardMapper.getListBoardCount(board);
		return new PageImpl<>(content, pageable, total);
	}



	@Override
	public BoardDto getBoard(int id) throws SQLException {
		return boardMapper.getBoard(id);
	}

	@Override
	public void registBoard(BoardDto board) throws SQLException {
		boardMapper.registBoard(board);
	}

	@Override
	public void updateBoard(BoardDto board) throws SQLException {
		boardMapper.updateBoard(board);
	}

	@Override
	public void deleteBoard(int id) throws SQLException {
		boardMapper.deleteBoard(id);
	}

	@Override
	public void deleteBoards(int[] ids) throws SQLException {
		// ids 없을 때 체크
		if(ids.length==0 || ids==null) throw new SQLException();
		for (int i = 0; i < ids.length; i++) {
			deleteBoard(ids[i]);
		}
	}

	@Override
	public void updateHit(int id) throws SQLException {
		boardMapper.updateHit(id);
	}

	@Override
	@Transactional
	public BoardDto getBoardWithHit(int id) throws SQLException {
		updateHit(id);
		return getBoard(id);
	}
	
}
