package com.advertisement.project.repository;

import com.advertisement.project.config.JDBC;
import com.advertisement.project.model.Comment;
import com.advertisement.project.model.User;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CommentRepository {

    public static boolean setComment(Comment comment) throws IOException, SQLException {
        final Connection connection = JDBC.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO comments (created_at, text, user_id, advertisement_id) VALUES (?,?,?,?);")) {
            preparedStatement.setDate(1, Date.valueOf(LocalDate.now()) /*Date.valueOf( advert.getDateOfCreating())*/);
            preparedStatement.setString(2, comment.getText());
            preparedStatement.setLong(3, comment.getUser().getId());
            preparedStatement.setLong(4, comment.getAdvertisement().getId());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e){  //логування /прокидати вверх
            return false;
        }finally {
            connection.close();
        }
    }

    public static boolean updateComment(Comment comment) throws IOException, SQLException {
        final Connection connection = JDBC.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE comments SET created_at,  text = ?  WHERE id = ?;")) {
            preparedStatement.setString(1, comment.getText());
            preparedStatement.setDate(2, Date.valueOf(LocalDate.now()));
            preparedStatement.setLong(3, comment.getId());
            preparedStatement.executeUpdate();
            return true;
        }catch (SQLException e){
            return false;
        } finally {
            connection.close();
        }
    }

    public static boolean deleteComment(Long idDelete) throws IOException, SQLException {
        final Connection connection = JDBC.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement("delete from comments WHERE id=?")) {
            preparedStatement.setLong(1,idDelete);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static List<Comment> getCommentsByAdvertisementId(Long idSearch) throws SQLException, IOException {
        List<Comment> commentByUserIdList = new ArrayList<>();
        Comment comment;
        final Connection connection = JDBC.getConnection();
        try (java.sql.PreparedStatement statement = connection.prepareStatement("" +
                "SELECT * FROM Comments JOIN users ON user_id  = users.id  WHERE  advertisement_id  = (?)"))
        {
            statement.setLong(1, idSearch);
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                comment = new Comment();
                comment.setId(resultSet.getLong(1));
                comment.setCreatedAt(resultSet.getDate(2).toLocalDate());
                comment.setText(resultSet.getString(3));
                comment.setUser(new User(resultSet.getLong(6), resultSet.getString(7), resultSet.getString(8)));
                commentByUserIdList.add(comment);
            }

        } finally {
            connection.close();
        }
        return commentByUserIdList;
    }


}
