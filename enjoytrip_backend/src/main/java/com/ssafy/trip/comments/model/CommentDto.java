package com.ssafy.trip.comments.model;

public class CommentDto {
    private int id;
    private String content;

    //join
    private int board_id;
    private int user_id;

    private String user_nickname;

    public CommentDto() {
    }

    public CommentDto(int id, String content, int board_id, int user_id) {
        this.id = id;
        this.content = content;
        this.board_id = board_id;
        this.user_id = user_id;
    }

    public CommentDto(int id, String content, int board_id, String user_nickname) {
        this.id = id;
        this.content = content;
        this.board_id = board_id;
        this.user_nickname = user_nickname;
    }

    public CommentDto(String content,int board_id, int user_id ) {
        this.board_id = board_id;
        this.user_id = user_id;
        this.content = content;
    }

    public CommentDto(int id, String content) {
        this.id = id;
        this.content = content;
    }

    public String getUser_nickname() {
        return user_nickname;
    }

    public void setUser_nickname(String user_nickname) {
        this.user_nickname = user_nickname;
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
