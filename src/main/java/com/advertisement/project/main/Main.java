package com.advertisement.project.main;

import com.advertisement.project.json.JsonReader;
import com.advertisement.project.model.Advertisement;
import com.advertisement.project.model.Comment;
import com.advertisement.project.model.User;
import com.advertisement.project.repository.AdvertisementRepository;
import com.advertisement.project.repository.CommentRepository;
import com.advertisement.project.repository.UserRepository;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static com.advertisement.project.repository.AdvertisementRepository.getAllAdvertsJson;

public class Main {
    public static void main(String[] args) throws IOException, SQLException {


        System.out.println(UserRepository.getById(1l));
    }
}
