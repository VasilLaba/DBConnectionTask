package com.advertisement.project.repository;

import com.advertisement.project.config.DBConnector;
import com.advertisement.project.model.User;
import com.advertisement.project.services.HashPassword;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    private static final String CREATE_SQL = "INSERT INTO users  (full_name , user_password ) VALUES (?,?);";
    private static final String DELETE_SQL = "delete from users WHERE Id=?;";
    private static final String UPDATE_SQL = "UPDATE users SET full_name  = ?  WHERE id = ?;";
    private static final String SELECT_SQL = "SELECT * FROM users";
    private static final String SELECT_BY_ID_SQL = "SELECT * FROM users WHERE id = ?";


    public static User getById(Long idSearch) throws SQLException {
        User user = new User();
        try (final Connection connection = DBConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID_SQL)){
            statement.setLong(1, idSearch);
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                user.setId(resultSet.getLong(1));
                user.setFullName(resultSet.getString(2));
                user.setPassword(resultSet.getString(3));
            }
        }
        return user;
    }

    public static boolean addUser(User user) throws SQLException {
        final Connection connection = DBConnector.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE_SQL)) {
            preparedStatement.setString(1, user.getFullName());
            preparedStatement.setString(2,  HashPassword.mdFive(user.getPassword()));
            preparedStatement.executeUpdate();
            return true;
        }  catch (SQLException e){
            return false;
        }finally {
            connection.close();
        }
    }

    public static boolean updateUser(Long id, User user) throws SQLException {
        final Connection connection = DBConnector.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setString(1, user.getFullName());
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
            return true;
        }catch (SQLException e){
            return false;
        } finally {
            connection.close();
        }
    }

    public static boolean deleteUser(Long idDelete) throws SQLException {
        final Connection connection = DBConnector.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SQL)) {
            preparedStatement.setLong(1,idDelete);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static List<User> getAllUsers() throws SQLException {
        List<User> userList = new ArrayList<>();
        final Connection connection = DBConnector.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_SQL)) {
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong(1));
                user.setFullName(resultSet.getString(2));
                user.setPassword(resultSet.getString(3));
                userList.add(user);
            }

        } finally {
            connection.close();
        }
        return userList;
    }

}
