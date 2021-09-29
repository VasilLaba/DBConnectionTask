package com.advertisement.project.repository;

import com.advertisement.project.config.DBConnector;
import com.advertisement.project.model.Advertisement;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AdvertisementRepository {

    public static Advertisement getAdvertisementById(Long idSearch) throws SQLException, IOException {
        Advertisement advert = new Advertisement();
        final Connection connection = DBConnector.getConnection();
        try (java.sql.PreparedStatement statement = connection.prepareStatement("SELECT id, title, created_at, description, user_id FROM advertisement WHERE advertisement.id = (?)"))
        {
            statement.setLong(1, idSearch);
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                advert.setId(resultSet.getLong(1));
                advert.setTitle(resultSet.getString(2));
                advert.setDateOfCreating(resultSet.getDate(3).toLocalDate());
                advert.setDescription(resultSet.getString(4));
                advert.setUser(UserRepository.getById(resultSet.getLong(5)));
            }
        } finally{
            connection.close();
        }
        return advert;
    }
    public static List<Advertisement> getAdvertisementByUserId(Long idSearch) throws SQLException, IOException {
        List<Advertisement> advertisementByUserIdList = new ArrayList<>();
        Advertisement advertisement;
        final Connection connection = DBConnector.getConnection();
        try (java.sql.PreparedStatement statement = connection.prepareStatement("" +
                "SELECT Id, title, created_at , description FROM Advertisements WHERE AuthorId = (?)"))
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

        } finally {
            connection.close();
        }
        return advertisementByUserIdList;
    }
    public static boolean setAdvertisement(Advertisement advertisement) throws IOException, SQLException {
        final Connection connection = DBConnector.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO advertisement ( title, created_at ,description ,user_id ) VALUES (?,?,?,?);")) {
            preparedStatement.setString(1, advertisement.getTitle());
            preparedStatement.setDate(2, Date.valueOf(LocalDate.now()));
            preparedStatement.setString(3, advertisement.getDescription());
            preparedStatement.setLong(4, advertisement.getUser().getId());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e){
            return false;
        }finally {
            connection.close();
        }
    }
    public static boolean updateAdvert(Advertisement advertisement) throws IOException, SQLException {
        final Connection connection = DBConnector.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE advertisement SET title = ?, created_at  = ?, description  = ? WHERE id = ?;")) {
            preparedStatement.setString(1, advertisement.getTitle());
            preparedStatement.setDate(2, Date.valueOf(LocalDate.now()));
            preparedStatement.setString(3, advertisement.getDescription());
            preparedStatement.executeUpdate();
            return true;
        }catch (SQLException e){
            return false;
        } finally {
            connection.close();
        }
    }

    public static boolean deleteAdvert(Long idDelete) throws IOException, SQLException {
        final Connection connection = DBConnector.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement("delete from advertisement WHERE id=?")) {
            preparedStatement.setLong(1,idDelete);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static List<Advertisement> getAllAdverts() throws SQLException, IOException {
        List<Advertisement> advertisementList = new ArrayList<>();
        final Connection connection = DBConnector.getConnection();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM advertisement;")) {
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

        } finally {
            connection.close();
        }
        return advertisementList;
    }

}
