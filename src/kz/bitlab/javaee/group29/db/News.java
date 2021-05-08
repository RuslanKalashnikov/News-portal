package kz.bitlab.javaee.group29.db;

public class News {
    private Long id;
    private String title;
    private String short_content;
    private String content;
    private String post_date;
    private String picture_url;
    private Languages language;
    private  Publications publication;
    private Categories category;
    private int likeAmount;

    public News(String title, String short_content, String content, String post_date, String picture_url, Languages language, Publications publication, Categories category) {
        this.title = title;
        this.short_content = short_content;
        this.content = content;
        this.post_date = post_date;
        this.picture_url = picture_url;
        this.language = language;
        this.publication = publication;
        this.category = category;
    }

    public News(Long id, String title, String short_content, String content, String post_date, String picture_url, Languages language, Publications publication, Categories category) {
        this.id = id;
        this.title = title;
        this.short_content = short_content;
        this.content = content;
        this.post_date = post_date;
        this.picture_url = picture_url;
        this.language = language;
        this.publication = publication;
        this.category = category;

    }

    public News(Long id, String title, String short_content, String content, String post_date, String picture_url, Languages language, Publications publication, Categories category, int likeAmount) {
        this.id = id;
        this.title = title;
        this.short_content = short_content;
        this.content = content;
        this.post_date = post_date;
        this.picture_url = picture_url;
        this.language = language;
        this.publication = publication;
        this.category = category;
        this.likeAmount = likeAmount;
    }

    public News(Long id){
        this.id = id;
    }

    public News(){}

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

    public String getShort_content() {
        return short_content;
    }

    public void setShort_content(String short_content) {
        this.short_content = short_content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPost_date() {
        return post_date;
    }

    public void setPost_date(String post_date) {
        this.post_date = post_date;
    }

    public String getPicture_url() {
        return picture_url;
    }

    public void setPicture_url(String picture_url) {
        this.picture_url = picture_url;
    }

    public Languages getLanguage() {
        return language;
    }

    public void setLanguage(Languages language) {
        this.language = language;
    }

    public Publications getPublication() {
        return publication;
    }

    public void setPublication(Publications publication) {
        this.publication = publication;
    }

    public Categories getCategory() {
        return category;
    }

    public void setCategory(Categories category) {
        this.category = category;
    }

    public int getLikeAmount() {
        return likeAmount;
    }

    public void setLikeAmount(int likeAmount) {
        this.likeAmount = likeAmount;
    }
}
