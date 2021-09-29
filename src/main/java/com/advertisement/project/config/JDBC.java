package com.advertisement.project.config;

import java.sql.DriverManager;
import org.postgresql.Driver;
import java.sql.Connection;
import java.sql.SQLException;


public class JDBC {
    public static Connection getConnection() throws SQLException {
//        Properties props = new Properties();
//        try(InputStream in = Files.newInputStream(Paths.get("src/main/resources/templates/database.properties"))){
//            props.load(in);
//        }

        String url = "jdbc:postgresql://localhost/advertisement_db";//props.getProperty("url");
        String username = "postgres";//props.getProperty("username");
        String password = "postgres";//props.getProperty("password");

        DriverManager.registerDriver(new Driver());


        return DriverManager.getConnection(url, username, password);
    }
}
