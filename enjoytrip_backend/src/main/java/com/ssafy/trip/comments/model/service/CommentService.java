package com.ssafy.trip.comments.model.service;

import com.ssafy.trip.comments.model.CommentDto;
import java.sql.SQLException;
import java.util.List;

public interface CommentService {

    List<CommentDto> commentList(int id) throws Exception;
    void registComment(CommentDto comment) throws SQLException ;
    void updateComment(CommentDto comment) throws Exception;
    void deleteComment(int id) throws Exception;
	void deleteByBoardId(int id);
	void deleteByUserId(int id);
}
