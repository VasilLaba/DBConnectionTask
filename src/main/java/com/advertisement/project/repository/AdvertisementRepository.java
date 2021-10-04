package com.advertisement.project.repository;

import com.advertisement.project.config.HikariCPDataSource;
import com.advertisement.project.model.Advertisement;
import com.advertisement.project.model.User;
import com.google.gson.Gson;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AdvertisementRepository {

    private  AdvertisementRepository(){}

    private static final String CREATE_SQL =
            "INSERT INTO advertisement ( title, created_at ,description ,user_id ) VALUES (?,?,?,?);";
    private static final String DELETE_SQL =
            "delete from advertisement WHERE id=?";
    private static final String UPDATE_SQL =
            "UPDATE advertisement SET title = ?, created_at  = ?, description  = ? WHERE id = ?;";
    private static final String SELECT_SQL =
            "SELECT * FROM advertisement;";
    private static final String SELECT_BY_ID_SQL =
            "SELECT advertisement.id, advertisement.title, advertisement.created_at, advertisement.description," +
                    " advertisement.user_id, users.id, users.full_name , users.user_password FROM advertisement " +
                    "JOIN users ON advertisement.user_id = users.id WHERE advertisement.id = ?;";
    private static final String SELECT_BY_USER_ID_SQL =
            "SELECT Id, title, created_at , description FROM Advertisements WHERE AuthorId = (?)";

    public static Advertisement getAdvertisementById(Long idSearch) throws SQLException {

        Advertisement advert = new Advertisement();

        try (final Connection connection = HikariCPDataSource.getConnection();
                java.sql.PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID_SQL))
        {
            statement.setLong(1, idSearch);
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                advert.setId(resultSet.getLong(1));
                advert.setTitle(resultSet.getString(2));
                advert.setDateOfCreating(resultSet.getDate(3).toLocalDate());
                advert.setDescription(resultSet.getString(4));
                advert.setUser(new User(
                        resultSet.getLong(6),
                        resultSet.getString(7),
                        resultSet.getString(8)
                ));
            }
        }
        return advert;
    }
    public static List<Advertisement> getAdvertisementByUserId(Long idSearch) throws SQLException {
        List<Advertisement> advertisementByUserIdList = new ArrayList<>();
        Advertisement advertisement;

        try (final Connection connection = HikariCPDataSource.getConnection();
             java.sql.PreparedStatement statement = connection.prepareStatement(SELECT_BY_USER_ID_SQL))
        {
            statement.setLong(1, idSearch);
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                advertisement = new Advertisement();
                advertisement.setId(resultSet.getLong(1));
                advertisement.setTitle(resultSet.getString(2));
                advertisement.setDateOfCreating(resultSet.getDate(3).toLocalDate());
                advertisement.setDescription(resultSet.getString(4));
                advertisementByUserIdList.add(advertisement);
            }

        }
        return advertisementByUserIdList;
    }
    public static boolean setAdvertisement(Advertisement advertisement) throws SQLException {

        try ( final Connection connection = HikariCPDataSource.getConnection();
              PreparedStatement preparedStatement = connection.prepareStatement(CREATE_SQL)) {
            preparedStatement.setString(1, advertisement.getTitle());
            preparedStatement.setDate(2, Date.valueOf(LocalDate.now()));
            preparedStatement.setString(3, advertisement.getDescription());
            preparedStatement.setLong(4, advertisement.getUser().getId());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e){
            return false;
        }
    }
    public static boolean updateAdvert(Advertisement advertisement) throws SQLException {

        try ( final Connection connection = HikariCPDataSource.getConnection();
              PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setString(1, advertisement.getTitle());
            preparedStatement.setDate(2, Date.valueOf(LocalDate.now()));
            preparedStatement.setString(3, advertisement.getDescription());
            preparedStatement.executeUpdate();
            return true;
        }catch (SQLException e){
            return false;
        }
    }

    public static boolean deleteAdvert(Long idDelete) throws SQLException {

        try ( final Connection connection = HikariCPDataSource.getConnection();
              PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SQL)) {
            preparedStatement.setLong(1,idDelete);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static List<Advertisement> getAllAdverts() throws SQLException {
        List<Advertisement> advertisementList = new ArrayList<>();
       try( final Connection connection = HikariCPDataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(SELECT_SQL)) {
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Advertisement advertisement = new Advertisement();
                advertisement.setId(resultSet.getLong(1));
                advertisement.setTitle(resultSet.getString(2));
                advertisement.setDateOfCreating(resultSet.getDate(3).toLocalDate());
                advertisement.setDescription(resultSet.getString(4));
                advertisement.setUser(UserRepository.getById(resultSet.getLong(5)));
                advertisementList.add(advertisement);
            }

        }
        return advertisementList;
    }

    public static String getAllAdvertsJson() throws SQLException {
        Gson gson = new Gson();
        List<Advertisement> advertisementList = getAllAdverts();
        String json;
        StringBuilder stringBuilder = new StringBuilder();
        for (Advertisement advertisement: advertisementList) {
            json = gson.toJson(advertisement);
            stringBuilder.append(json);
        }
        return stringBuilder.toString();
    }

}
