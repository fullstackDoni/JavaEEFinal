package kz.bitlab.models;

import java.sql.Timestamp;

public class Comments {
    private Long id;
    private Users users;
    private News news;
    private String comment;
    private Timestamp post_date;

    public Comments() {
    }

    public Comments(Long id, Users users, News news, String comment, Timestamp post_date) {
        this.id = id;
        this.users = users;
        this.news = news;
        this.comment = comment;
        this.post_date = post_date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Timestamp getPost_date() {
        return post_date;
    }

    public void setPost_date(Timestamp post_date) {
        this.post_date = post_date;
    }
}
