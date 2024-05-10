package com.ssafy.trip.notice;

import java.sql.SQLException;

import org.springframework.http.ResponseEntity;
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
@RequestMapping("/notice")
public class NoticeController {

	private final NoticeService noticeService;

	public NoticeController(NoticeService noticeService) {
		super();
		this.noticeService = noticeService;
	}
	
	@GetMapping("/list")
	public ResponseEntity<?> getNoticeList() throws SQLException {
		return ResponseEntity.ok().body(noticeService.getListNotice());
	}
	
	@GetMapping("/{noticeId}")
	public ResponseEntity<?> getNotice(@PathVariable("noticeId") int noticeId) throws SQLException {
		return ResponseEntity.ok().body(noticeService.getNotice(noticeId));
	}
	@PostMapping("/{noticeId}")
	public ResponseEntity<?> writeNotice(@ModelAttribute NoticeDto notice) throws SQLException {
		noticeService.writeNotice(notice);
		return ResponseEntity.ok().build();
	}
	@PatchMapping("/{noticeId}")
	public ResponseEntity<?> updateNoticeList(@ModelAttribute NoticeDto notice) throws SQLException {
		noticeService.modifyNotice(notice);
		return ResponseEntity.ok().build();
	}
	@DeleteMapping("/{noticeId}")
	public ResponseEntity<?> deleteNoticeList(@PathVariable("noticeId") int noticeId) throws SQLException {
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
