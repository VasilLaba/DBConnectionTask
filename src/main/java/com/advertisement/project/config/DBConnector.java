package com.advertisement.project.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.DriverManager;
import org.postgresql.Driver;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;


public class DBConnector {
    public static Connection getConnection() throws SQLException {
        Properties properties = new Properties();
        try(FileInputStream fis =  new FileInputStream("db.properties")){
            properties.load(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String url = properties.getProperty("url");
        String username = properties.getProperty("username");
        String password = properties.getProperty("password");

        DriverManager.registerDriver(new Driver());

        return DriverManager.getConnection(url, username, password);
    }
}
