package com.ssafy.trip.board.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.trip.board.model.BoardDto;
import com.ssafy.trip.board.model.service.BoardService;

@RestController
public class BoardController {
	
	private BoardService boardservice;

	public BoardController(BoardService boardservice) {
		super();
		this.boardservice = boardservice;
	}

	@GetMapping("/board/list")
	public ResponseEntity<?> board_list() throws SQLException {
		return ResponseEntity.ok().body(boardservice.boardList());
	}
	
	@GetMapping("/board/regist")
	public String board_regist() {
		return null;
	}
	
	@PostMapping("/board/regist")
	public ResponseEntity<?> board_registf(@RequestBody BoardDto board) throws SQLException {
		boardservice.registBoard(board);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/board/detail")
	public String board_detail(Model model, @RequestBody BoardDto board, @RequestParam(value="id") int id) throws SQLException {
		model.addAttribute("board", boardservice.getBoard(id));
		boardservice.updateHit(id);
		return "board/detail";
	}
	
	@GetMapping("/board/update")
	public String board_update(@RequestParam(value="id") int id, Model model) throws SQLException {
		model.addAttribute("board", boardservice.getBoard(id));
		return "board/update";
	}
	
	@PostMapping("/board/update")
	public String board_updatef(@RequestBody BoardDto board) throws SQLException {
		boardservice.updateBoard(board);
		return "redirect:/product/detail?code="+board.getId();
	}
	
	@PostMapping("/board/delete")
	public String board_delete(@RequestParam(value="del_board", required=false) int[] ids) throws SQLException {
		boardservice.deleteBoards(ids);
		return "redirect:/board/list";
	}
	
	
}
