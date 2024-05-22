package com.ssafy.trip.comments.model.service;

import com.ssafy.trip.comments.model.CommentDto;
import com.ssafy.trip.comments.model.mapper.CommentMapper;
import com.ssafy.trip.exception.ResourceNotFoundException;
import com.ssafy.trip.exception.util.BaseResponseCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{

    CommentMapper commentMapper;

    public CommentServiceImpl(CommentMapper commentDao) {
        this.commentMapper = commentDao;
    }

    @Transactional(readOnly = true)
    @Override
    public List<CommentDto> commentList(int id) throws Exception {
        if(existsById(id)) throw new ResourceNotFoundException(BaseResponseCode.RESOURCE_NOT_FOUND);
        else return commentMapper.commentList(id);
    }

    @Transactional
    @Override
    public void registComment(CommentDto comment) throws SQLException {
        commentMapper.registComment(comment);
    }

    @Transactional
    @Override
    public void updateComment(CommentDto comment) throws Exception {
        if(existsById(comment.getId())) throw new ResourceNotFoundException(BaseResponseCode.RESOURCE_NOT_FOUND);
        else commentMapper.updateComment(comment);
    }

    @Transactional
    @Override
    public void deleteComment(int id) throws Exception {
        if(existsById(id)) throw new ResourceNotFoundException(BaseResponseCode.RESOURCE_NOT_FOUND);
        else commentMapper.deleteComment(id);
    }

    @Transactional(readOnly = true)
    public boolean existsById(int id) throws Exception {
        return commentMapper.existsById(id);
    }

}
