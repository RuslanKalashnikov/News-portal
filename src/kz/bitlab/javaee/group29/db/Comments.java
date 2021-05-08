package kz.bitlab.javaee.group29.db;

import java.sql.Timestamp;

public class Comments {

    private Long id;
    private AuthUser user;
    private News blog;
    private String comment;
    private Timestamp postDate;

    public Comments(){

    }

    public Comments(Long id, AuthUser user, News blog, String comment, Timestamp postDate) {
        this.id = id;
        this.user = user;
        this.blog = blog;
        this.comment = comment;
        this.postDate = postDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AuthUser getUser() {
        return user;
    }

    public void setUser(AuthUser user) {
        this.user = user;
    }

    public News getBlog() {
        return blog;
    }

    public void setBlog(News blog) {
        this.blog = blog;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Timestamp getPostDate() {
        return postDate;
    }

    public void setPostDate(Timestamp postDate) {
        this.postDate = postDate;
    }
}
