package com.advertisement.project.repository;

import com.advertisement.project.config.DBConnector;
import com.advertisement.project.config.HikariCPDataSource;
import com.advertisement.project.model.Comment;
import com.advertisement.project.model.User;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class CommentRepository {

    private CommentRepository(){
    }

    private static final String CREATE_SQL = "INSERT INTO comments (created_at, text, user_id, advertisement_id) VALUES (?,?,?,?);";
    private static final String DELETE_SQL = "delete from comments WHERE id=?";
    private static final String UPDATE_SQL = "UPDATE comments SET created_at = ?,  text = ?  WHERE id = ?;";
    private static final String SELECT_BY_ID_SQL = "SELECT * FROM Comments JOIN users ON user_id  = users.id  WHERE  advertisement_id  = (?)";



    public static boolean setComment(Comment comment) throws SQLException {

        try (final Connection connection = HikariCPDataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_SQL)) {
            preparedStatement.setTimestamp(1,Timestamp.valueOf(comment.getCreatedAt()));
            preparedStatement.setString(2, comment.getText());
            preparedStatement.setLong(3, comment.getUser().getId());
            preparedStatement.setLong(4, comment.getAdvertisement().getId());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e){  //логування /прокидати вверх
            return false;
        }
    }

    public static boolean updateComment(Comment comment) throws SQLException {

        try (final Connection connection = HikariCPDataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setString(2, comment.getText());
            preparedStatement.setTimestamp(1, Timestamp.valueOf(comment.getCreatedAt()));
            preparedStatement.setLong(3, comment.getId());
            preparedStatement.executeUpdate();
            return true;
        }catch (SQLException e){
            return false;
        }
    }

    public static boolean deleteComment(Long idDelete) throws SQLException {

        try (final Connection connection = HikariCPDataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SQL)) {
            preparedStatement.setLong(1,idDelete);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static List<Comment> getCommentsByAdvertisementId(Long idSearch) throws SQLException {
        List<Comment> commentByUserIdList = new ArrayList<>();
        Comment comment;

        try (final Connection connection = HikariCPDataSource.getConnection();
             java.sql.PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID_SQL))
        {
            statement.setLong(1, idSearch);
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                comment = new Comment();
                comment.setId(resultSet.getLong(1));
                comment.setCreatedAt(resultSet.getDate(2).toLocalDate().atTime(LocalTime.now()));
                comment.setCreatedAt(resultSet.getTimestamp(2).toLocalDateTime());
                comment.setText(resultSet.getString(3));
                comment.setUser(new User(resultSet.getLong(6), resultSet.getString(7), resultSet.getString(8)));
                commentByUserIdList.add(comment);
            }

        }
        return commentByUserIdList;
    }


}
