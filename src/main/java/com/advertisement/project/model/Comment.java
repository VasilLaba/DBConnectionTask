package com.advertisement.project.model;

import java.time.LocalDate;

public class Comment {
    private long id;
    private User user;
    private Advertisement advertisement;
    private String text;
    private LocalDate createdAt; //+ Time

    public Comment() {
    }

    public Comment(String text, LocalDate createdAt) {
        this.text = text;
        this.createdAt = createdAt;
    }

    public Comment(User user, Advertisement advertisement, String text) {
        this(user,advertisement, text, LocalDate.now());
    }

    public Comment(User user, Advertisement advertisement, String text, LocalDate createdAt) {
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

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
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
