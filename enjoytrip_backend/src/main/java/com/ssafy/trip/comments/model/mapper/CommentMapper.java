package com.ssafy.trip.comments.model.mapper;

import com.ssafy.trip.comments.model.CommentDto;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface CommentMapper {

    List<CommentDto> commentList(int id)  throws SQLException ;
    void registComment(CommentDto comment) throws SQLException ;
    void updateComment(CommentDto comment) throws SQLException ;
    void deleteComment(int id) throws SQLException ;
    boolean existsById(int id);
}