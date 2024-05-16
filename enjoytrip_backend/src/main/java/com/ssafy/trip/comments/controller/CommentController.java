package com.ssafy.trip.comments.controller;

import com.ssafy.trip.comments.model.CommentDto;
import com.ssafy.trip.comments.model.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/comment")
public class CommentController {

    private CommentService commentservice;

    public CommentController(CommentService commentservice) {
        this.commentservice = commentservice;
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllComments() throws SQLException {
        List<CommentDto> boardlist = commentservice.commentList();
        return ResponseEntity.ok().body(boardlist);
    }

    @PostMapping("/")
    public ResponseEntity<?> writeComment(@RequestBody CommentDto comment) throws SQLException {
        commentservice.registComment(comment);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getComment(@PathVariable("id") int id) throws SQLException {
        CommentDto comment = commentservice.getComment(id);
        return ResponseEntity.ok().body(comment);
    }

    @PutMapping("/")
    public ResponseEntity<?> updateComment(@RequestBody CommentDto comment) throws SQLException {
        commentservice.updateComment(comment);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable("id") int id) throws SQLException {
        commentservice.deleteComment(id);
        return ResponseEntity.ok().build();
    }
}
