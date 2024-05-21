package com.ssafy.trip.comments.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.trip.comments.model.CommentDto;
import com.ssafy.trip.comments.model.service.CommentService;

@RestController
@CrossOrigin
@RequestMapping("/comment")
public class CommentController {

    private CommentService commentservice;

    public CommentController(CommentService commentservice) {
        super();
        this.commentservice = commentservice;
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getAllComments(@PathVariable("id") int id) throws SQLException {
        List<CommentDto> boardlist = commentservice.commentList(id);
        return ResponseEntity.ok().body(boardlist);
    }

    @RequestMapping(value="/", method = RequestMethod.POST)
    public ResponseEntity<?> writeComment(@RequestBody CommentDto comment) throws SQLException {
        commentservice.registComment(comment);
        return ResponseEntity.status(HttpStatus.OK).body("댓글 등록 성공");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateComment(@RequestBody CommentDto comment) throws SQLException {
        commentservice.updateComment(comment);
        return ResponseEntity.status(HttpStatus.OK).body("댓글 수정 성공");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable("id") int id) throws SQLException {
        commentservice.deleteComment(id);
        return ResponseEntity.status(HttpStatus.OK).body("댓글 삭제 성공");
    }
}