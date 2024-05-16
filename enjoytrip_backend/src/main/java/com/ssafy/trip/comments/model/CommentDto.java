package com.ssafy.trip.comments.model;

public class CommentDto {
    private int id;
    private String content;

    //join
    private int board_id;
    private int user_id;

    public CommentDto(int id, String content, int board_id, int user_id) {
        this.id = id;
        this.content = content;
        this.board_id = board_id;
        this.user_id = user_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getBoard_id() {
        return board_id;
    }

    public void setBoard_id(int board_id) {
        this.board_id = board_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "CommentDto{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", board_id=" + board_id +
                ", user_id=" + user_id +
                '}';
    }
}
