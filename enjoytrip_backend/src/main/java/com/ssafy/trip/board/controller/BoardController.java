package com.ssafy.trip.board.controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import javax.xml.transform.OutputKeys;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.ssafy.trip.board.model.BoardDto;
import com.ssafy.trip.board.model.service.BoardService;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin
@RequestMapping("/board")
public class BoardController {

	private BoardService boardservice;

	public BoardController(BoardService boardservice) {
		super();
		this.boardservice = boardservice;
	}

	@GetMapping("/")
	public ResponseEntity<Page<BoardDto>> getAllBoards(@PageableDefault(size = 10) Pageable pageable, @RequestParam(value = "offset", defaultValue = "0") int offset, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) throws SQLException {
		return ResponseEntity.ok().body(boardservice.boardList(pageable, offset, pageSize));
	}

	@RequestMapping(value="/", method = RequestMethod.POST, produces =  "application/json", consumes = "multipart/form-data")
	public ResponseEntity<?> writeBoard(@RequestBody BoardDto board, @RequestPart(value = "img", required = false) MultipartFile img) throws SQLException {
		boardservice.registBoard(board, img);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getBoard(@PathVariable("id") int id) throws SQLException {
		BoardDto board = boardservice.getBoardWithHit(id);
		return ResponseEntity.ok().body(board);
	}

	@PutMapping("/")
	public ResponseEntity<?> updateBoard(@RequestBody BoardDto board, @RequestPart(value = "img", required = false) MultipartFile img) throws SQLException {
		boardservice.updateBoard(board, img);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteBoard(@PathVariable("id") int id)
			throws SQLException {
		boardservice.deleteBoard(id);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/")
	public ResponseEntity<?> deleteBoards(@RequestParam(value = "del-board", required = false) int[] ids)
			throws SQLException {
		boardservice.deleteBoards(ids);
		return ResponseEntity.ok().build();
	}

}
