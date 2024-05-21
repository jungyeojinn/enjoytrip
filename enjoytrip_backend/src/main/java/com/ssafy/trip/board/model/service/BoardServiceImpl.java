package com.ssafy.trip.board.model.service;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.trip.board.model.BoardDto;
import com.ssafy.trip.board.model.mapper.BoardMapper;
import org.springframework.web.multipart.MultipartFile;

@Service
public class BoardServiceImpl implements BoardService {

    BoardMapper boardMapper;

    @Autowired
    public BoardServiceImpl(BoardMapper boardDao) {
        super();
        this.boardMapper = boardDao;
    }

    @Transactional(readOnly = true)
    @Override
    public Page<BoardDto> boardList(Pageable pageable, int pageNum, int pageSize) throws SQLException {
        BoardDto board = new BoardDto();
        int offset = pageNum * pageSize;
        List<BoardDto> content = boardMapper.boardList(offset, pageSize);

        int total = boardMapper.getListBoardCount(board);
        return new PageImpl<>(content, pageable, total);
    }

    @Transactional(readOnly = true)
    @Override
    public BoardDto getBoard(int id) throws SQLException {
        return boardMapper.getBoard(id);
    }

    @Transactional
    @Override
    public void registBoard(BoardDto board, MultipartFile img) throws SQLException {
        String imgPath = "";
        if (img != null && !img.isEmpty()) imgPath = saveImage(img);
        board.setImg(imgPath);
        boardMapper.registBoard(board);
    }

    private String saveImage(MultipartFile img) {
        String uuid = UUID.randomUUID().toString();    //파일 이름 중복 방지
        String savedFilename = uuid;

        String savedPath = "C:/upload/" + savedFilename;

        File file = new File(savedPath);

        try {
            img.transferTo(file);
            return savedFilename;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void deleteImage(String imgPath) {
        File fileToDelete = new File("C:/upload/" + imgPath);
        boolean success = fileToDelete.delete();
    }

    @Transactional
    @Override
    public void updateBoard(BoardDto board, MultipartFile img) throws SQLException {
        String originPath="";
        if (img != null && !img.isEmpty()){
            originPath=boardMapper.getImg(board.getId());
            if(!originPath.equals("")){
                deleteImage(originPath);
            }
            String imgPath = saveImage(img);
            board.setImg(imgPath);
        }
        boardMapper.updateBoard(board);
    }

    @Transactional
    @Override
    public void deleteBoard(int id) throws SQLException {
        String originPath=boardMapper.getImg(id);
        if(!originPath.equals("")){
            deleteImage(originPath);
        }
        boardMapper.deleteBoard(id);
    }

    @Transactional
    @Override
    public void deleteBoards(int[] ids) throws SQLException {
        // ids 없을 때 체크
        if (ids.length == 0 || ids == null) throw new SQLException();
        for (int i = 0; i < ids.length; i++) {
            deleteBoard(ids[i]);
        }
    }

    @Transactional
    @Override
    public void updateHit(int id) throws SQLException {
        boardMapper.updateHit(id);
    }

    @Override
    @Transactional
    public BoardDto getBoardWithHit(int id) throws SQLException {
        updateHit(id);
        return getBoard(id);
    }

}
