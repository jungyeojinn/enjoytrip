package com.ssafy.trip.board.controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.trip.board.model.BoardDto;
import com.ssafy.trip.board.model.service.BoardService;

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
