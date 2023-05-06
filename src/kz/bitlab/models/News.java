package kz.bitlab.models;

import java.sql.Timestamp;

public class News {
        private Long id;

        private String title;

        private String content;

        private Users users;

        private Category category;

        private Timestamp post_date;

    public News() {
    }

    public News(Long id, String title, String content, Users users, Category category, Timestamp post_date) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.users = users;
        this.category = category;
        this.post_date = post_date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Timestamp getPost_date() {
        return post_date;
    }

    public void setPost_date(Timestamp post_date) {
        this.post_date = post_date;
    }
}
