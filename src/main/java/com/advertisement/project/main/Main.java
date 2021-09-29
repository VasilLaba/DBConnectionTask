package com.advertisement.project.main;

import com.advertisement.project.model.Comment;
import com.advertisement.project.model.User;
import com.advertisement.project.repository.AdvertisementRepository;
import com.advertisement.project.repository.CommentRepository;
import com.advertisement.project.repository.UserRepository;

import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws IOException, SQLException {
//       // System.out.println(UserRepository.getUserById(1l));
//        System.out.println(AdvertisementRepository.getAdvertisementById(1l));
//      //  Comment com1 = new Comment(UserRepository.getUserById(1l), AdvertisementRepository.getAdvertisementById(1l), "Some text");
//        Comment com2 = new Comment(UserRepository.getUserById(1l), AdvertisementRepository.getAdvertisementById(1l), "Some new text");
//   //     CommentRepository.setComment(com1);
//        CommentRepository.setComment(com2);
//        System.out.println(CommentRepository.getCommentsByAdvertisementId(1l));
//        User user = new User("Some User", "pasword");
//        UserRepository.setUser(user);
        System.out.println(UserRepository.getUsersSingIn("sad","das"));
    }
}
