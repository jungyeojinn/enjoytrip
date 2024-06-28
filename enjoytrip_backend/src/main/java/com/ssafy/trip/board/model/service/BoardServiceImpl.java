package com.ssafy.trip.board.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.trip.common.ImgUtils;
import com.ssafy.trip.exception.ResourceNotFoundException;
import com.ssafy.trip.exception.util.BaseResponseCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.trip.board.model.BoardDto;
import com.ssafy.trip.board.model.BoardWithUserDto;
import com.ssafy.trip.board.model.mapper.BoardMapper;
import com.ssafy.trip.comments.model.service.CommentService;

import org.springframework.web.multipart.MultipartFile;

@Service
public class BoardServiceImpl implements BoardService {
    private static final String BOARD_IMAGE_PATH = "board";

    private final BoardMapper boardMapper;
    private final CommentService commentService;
    private final ImgUtils imgUtils;
    public BoardServiceImpl(BoardMapper boardMapper, CommentService commentService, ImgUtils imgUtils) {
		super();
		this.boardMapper = boardMapper;
		this.commentService = commentService;
		this.imgUtils = imgUtils;
	}

	@Transactional(readOnly = true)
    @Override
    public Page<BoardWithUserDto> boardList(int pageNum, int pageSize) throws Exception {
        //pageNum은 0부터 시작함
        BoardDto board = new BoardDto();
        int offset = pageNum * pageSize;
        List<BoardWithUserDto> content = boardMapper.boardList(offset, pageSize);
        int total = boardMapper.getListBoardCount(board);

        if(offset<0||offset>total) throw new ResourceNotFoundException(BaseResponseCode.RESOURCE_NOT_FOUND);
        return new PageImpl<>(content, Pageable.ofSize(pageSize), total);
    }

    @Transactional(readOnly = true)
    @Override
    public BoardWithUserDto getBoard(int id) throws Exception {
        if(!existsById(id)) throw new ResourceNotFoundException(BaseResponseCode.RESOURCE_NOT_FOUND);
        return boardMapper.getBoard(id);
    }

    @Transactional
    @Override
    public void registBoard(BoardDto board, MultipartFile img) throws SQLException {
        String imgPath = "";
        if (img != null && !img.isEmpty()) {
            imgPath = imgUtils.saveImage(img, BOARD_IMAGE_PATH);
        }
        board.setImg(imgPath);
        boardMapper.registBoard(board);
    }

    @Transactional
    @Override
    public void updateBoard(BoardDto board, MultipartFile img) throws Exception {
        if(!existsById(board.getId())) throw new ResourceNotFoundException(BaseResponseCode.RESOURCE_NOT_FOUND);
        else {
            String originPath = "";
            if (img != null && !img.isEmpty()) {
                originPath = boardMapper.getImg(board.getId());
                if (originPath != null && !originPath.isEmpty()) {
                    imgUtils.deleteImage(originPath, BOARD_IMAGE_PATH);
                }
                String imgPath = imgUtils.saveImage(img, BOARD_IMAGE_PATH);
                board.setImg(imgPath);
            }
            boardMapper.updateBoard(board);
        }
    }

    @Transactional
    @Override
    public void deleteBoard(int id) throws Exception {
        if(!existsById(id)) throw new ResourceNotFoundException(BaseResponseCode.RESOURCE_NOT_FOUND);
        else {
            String originPath = boardMapper.getImg(id);
            if (!originPath.isEmpty()) {
                imgUtils.deleteImage(originPath, BOARD_IMAGE_PATH);
            }
            commentService.deleteByBoardId(id);
            boardMapper.deleteBoard(id);
        }
    }

    @Transactional
    @Override
    public void deleteBoards(int[] ids) throws Exception {
        // ids 없을 때 체크
        for (int i = 0; i < ids.length; i++) {
            if(!existsById(ids[i])) throw new ResourceNotFoundException(BaseResponseCode.RESOURCE_NOT_FOUND);
            else deleteBoard(ids[i]);
        }
    }

    @Transactional
    @Override
    public void updateHit(int id) throws Exception {
        if(!existsById(id)) throw new ResourceNotFoundException(BaseResponseCode.RESOURCE_NOT_FOUND);
        else boardMapper.updateHit(id);
    }

    @Override
    @Transactional
    public BoardWithUserDto getBoardWithHit(int id) throws Exception {
        if(!existsById(id)) throw new ResourceNotFoundException(BaseResponseCode.RESOURCE_NOT_FOUND);
        else {
            updateHit(id);
            return getBoard(id);
        }
    }

    @Transactional(readOnly = true)
    public boolean existsById(int id) throws Exception {
        return boardMapper.existsById(id);
    }

	@Override
	@Transactional
	public void deActivateFromUser(int id) throws Exception {
		deleteBoards(boardMapper.getBoardIdsByUserId(id));
	}
}
