package com.advertisement.project.repository;

import com.advertisement.project.config.JDBC;
import com.advertisement.project.model.User;
import com.advertisement.project.services.HashPassword;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.advertisement.project.services.HashPassword.mdFive;

public class UserRepository {
    public static User getById(Long idSearch) throws SQLException {
        User user = new User();
        try (final Connection connection = JDBC.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE id = ?")){
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

    public static boolean addUser(User user) throws IOException, SQLException, NoSuchAlgorithmException {
        final Connection connection = JDBC.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users  (full_name , user_password ) VALUES (?,?);")) {
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

    public static boolean updateUser(Long id, User user) throws IOException, SQLException, NoSuchAlgorithmException {
        final Connection connection = JDBC.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE users SET full_name  = ?  WHERE id = ?;")) {
            preparedStatement.setString(1, user.getFullName());
            preparedStatement.setLong(5, id);
            preparedStatement.executeUpdate();
            return true;
        }catch (SQLException e){
            return false;
        } finally {
            connection.close();
        }
    }

    public static boolean deleteUser(Long idDelete) throws IOException, SQLException {
        final Connection connection = JDBC.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement("delete from users WHERE Id=?")) {
            preparedStatement.setLong(1,idDelete);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static List<User> getAllUsers() throws SQLException, IOException {
        List<User> userList = new ArrayList<>();
        final Connection connection = JDBC.getConnection();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM users")) {
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

    /**@
     *
     * @param login
     * @param password
     * @return
     * @throws SQLException
     * @throws IOException
     */
//    public static User getUsersSingIn(String login, String password) throws SQLException, IOException {
//        User user = new User();
//        final Connection connection = JDBC.getConnection();
//        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE full_name  = (?) And user_password  = (?)")) {
//            statement.setString(1, login);
//            statement.setString(2, mdFive(password));
//            final ResultSet resultSet = statement.executeQuery();
//            while (resultSet.next()) {
//                user.setId(resultSet.getLong(1));
//                user.setFullName(resultSet.getString(2));
//                user.setPassword(resultSet.getString(3));
//            }
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        } finally {
//            connection.close();
//        }
//        return user;
//    }


}
