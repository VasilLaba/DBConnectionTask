package com.advertisement.project.model;


import com.advertisement.project.services.HashPassword;

import java.security.NoSuchAlgorithmException;
import java.util.List;


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

//    private String getHashPass(){
//        try {
//            return HashPassword.mdFive(password);
//        } catch (NoSuchAlgorithmException e) { e.printStackTrace(); }
//        return "hash error";
//    }

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
