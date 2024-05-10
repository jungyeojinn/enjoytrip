package com.ssafy.trip.notice.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.trip.notice.model.NoticeDto;
import com.ssafy.trip.notice.model.service.NoticeService;

@RestController
@CrossOrigin
@RequestMapping("/notice")
public class NoticeController {

	private final NoticeService noticeService;

	public NoticeController(NoticeService noticeService) {
		super();
		this.noticeService = noticeService;
	}
	
	@GetMapping("/")
	public ResponseEntity<?> getAllNotices() throws SQLException {
		List<NoticeDto> noticeList = noticeService.getListNotice();
		return ResponseEntity.ok().body(noticeList);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getNotice(@PathVariable("id") int noticeId) throws SQLException {
		NoticeDto notice = noticeService.getNotice(noticeId);
		return ResponseEntity.ok().body(notice);
	}
	@PostMapping("/{id}")
	public ResponseEntity<?> writeNotice(@ModelAttribute NoticeDto notice) throws SQLException {
		noticeService.writeNotice(notice);
		return ResponseEntity.ok().build();
	}
	@PatchMapping("/{id}")
	public ResponseEntity<?> updateNotice(@ModelAttribute NoticeDto notice) throws SQLException {
		noticeService.modifyNotice(notice);
		return ResponseEntity.ok().build();
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteNotice(@PathVariable("id") int noticeId) throws SQLException {
		noticeService.deleteNotice(noticeId);
		return ResponseEntity.ok().build();
	}
	
	/*
	List<NoticeDto> getListNotice() throws SQLException;

	void writeNotice(NoticeDto noticeDto) throws SQLException;

	NoticeDto getNotice(int noticeId) throws SQLException;

	void updateHit(int noticeId) throws SQLException;

	void modifyNotice(NoticeDto noticeDto) throws SQLException;

	void deleteNotice(int noticeId) throws SQLException;
	 */
}
