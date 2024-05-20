package com.ssafy.trip.board.controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import javax.xml.transform.OutputKeys;

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
	public ResponseEntity<?> getAllBoards() throws SQLException {
		List<BoardDto> boardlist = boardservice.boardList();
		return ResponseEntity.ok().body(boardlist);
	}

	@RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json", consumes = "multipart/form-data")
	public ResponseEntity<?> writeBoard(@RequestParam(value = "title") String title,
										@RequestParam(value = "content") String content,
										@RequestParam(value = "type") int type,
										@RequestParam(value = "user_id") int user_id,
										@RequestPart(value = "img", required = false) MultipartFile img) throws SQLException, IOException {
		BoardDto board=new BoardDto(user_id, type, title, content);
		// 이미지 파일 처리
		String imagePath = saveImage(img);
		board.setImg(imagePath);
		boardservice.registBoard(board);
		return ResponseEntity.ok().build();
	}

	private String saveImage(MultipartFile image){
		if (image != null && !image.isEmpty()) {
			String uuid = UUID.randomUUID().toString();	//파일 이름 중복 방지
			String savedFilename = uuid;

			String savedPath = "C:/upload/" + savedFilename;

			File file = new File(savedPath);

			try {
				image.transferTo(file);
				return savedFilename;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getBoard(@PathVariable("id") int id) throws SQLException {
		BoardDto board = boardservice.getBoardWithHit(id);
		return ResponseEntity.ok().body(board);
	}

	@PutMapping("/")
	public ResponseEntity<?> updateBoard(@RequestBody BoardDto board) throws SQLException {
		boardservice.updateBoard(board);
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
