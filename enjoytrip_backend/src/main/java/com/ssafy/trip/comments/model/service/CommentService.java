package com.ssafy.trip.comments.model.service;

import com.ssafy.trip.comments.model.CommentDto;
import java.sql.SQLException;
import java.util.List;

public interface CommentService {

    List<CommentDto> commentList(int id)  throws SQLException;
    //CommentDto getComment(int id)  throws SQLException ;
    void registComment(CommentDto comment) throws SQLException ;
    void updateComment(CommentDto comment) throws SQLException ;
    void deleteComment(int id) throws SQLException ;

}
