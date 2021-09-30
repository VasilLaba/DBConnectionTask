package com.advertisement.project.model;

import java.time.LocalDate;

public class Advertisement {
    private long id;
    private User user;
    private String title;
    private String description;
    private LocalDate createdAt;

    public Advertisement() {
    }

    public Advertisement(String title, String description, LocalDate createdAt) {
        this.title = title;
        this.description = description;
        this.createdAt = createdAt;
    }

    public Advertisement(long id, User user, String title, String description, LocalDate dateOfCreating) {
        this.id = id;
        this.user = user;
        this.title = title;
        this.description = description;
        this.createdAt = dateOfCreating;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDateOfCreating() {
        return createdAt;
    }

    public void setDateOfCreating(LocalDate dateOfCreating) {
        this.createdAt = dateOfCreating;
    }

    @Override
    public String toString() {
        return "Advertisement{" +
                "id=" + id +
                ", user=" + user +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", dateOfCreating=" + createdAt +
                '}';
    }
}
