package com.ssafy.trip.comments.model.service;

import com.ssafy.trip.comments.model.CommentDto;
import com.ssafy.trip.comments.model.mapper.CommentMapper;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{

    CommentMapper commentMapper;

    public CommentServiceImpl(CommentMapper commentDao) {
        this.commentMapper = commentDao;
    }

    @Override
    public List<CommentDto> commentList() throws SQLException {
        return commentMapper.commentList();
    }

    @Override
    public CommentDto getComment(int id) throws SQLException {
        return commentMapper.getComment(id);
    }

    @Override
    public void registComment(CommentDto comment) throws SQLException {
        commentMapper.registComment(comment);
    }

    @Override
    public void updateComment(CommentDto comment) throws SQLException {
        commentMapper.updateComment(comment);
    }

    @Override
    public void deleteComment(int id) throws SQLException {
        commentMapper.deleteComment(id);
    }
}
