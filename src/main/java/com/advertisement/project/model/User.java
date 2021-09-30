package com.advertisement.project.model;


import com.advertisement.project.services.HashPassword;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Objects;


public class User {
    private long id;
    private String fullName;
    private String password;
    private List<Advertisement> advertsList;


    public User(){
    }

    public User(String fullName, String password) {
        this.fullName = fullName;
        this.password = password;
    }

    public User(long id, String fullName, String password) {
        this.id = id;
        this.fullName = fullName;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(fullName, user.fullName) && Objects.equals(password, user.password) && Objects.equals(advertsList, user.advertsList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName, password, advertsList);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", password='" + password + '\'' +
                ", advertsList=" + advertsList +
                '}';
    }
}
