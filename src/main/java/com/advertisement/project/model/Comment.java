package com.advertisement.project.model;

import java.time.LocalDateTime;

public class Comment {
    private long id;
    private User user;
    private Advertisement advertisement;
    private String text;
    private LocalDateTime createdAt; //+ Time

    public Comment() {
    }

    public Comment(String text, LocalDateTime createdAt) {
        this.text = text;
        this.createdAt = createdAt;
    }

    public Comment(User user, Advertisement advertisement, String text) {
        this(user,advertisement, text, LocalDateTime.now());
    }

    public Comment(User user, Advertisement advertisement, String text, LocalDateTime createdAt) {
        this.user = user;
        this.advertisement = advertisement;
        this.text = text;
        this.createdAt = createdAt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Advertisement getAdvertisement() {
        return advertisement;
    }

    public void setAdvertisement(Advertisement advertisement) {
        this.advertisement = advertisement;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", user=" + user +
                ", advertisement=" + advertisement +
                ", text='" + text + '\'' +
                ", dateOfCreating=" + createdAt +
                '}';
    }
}
