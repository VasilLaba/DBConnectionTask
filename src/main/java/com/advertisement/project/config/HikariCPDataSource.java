package com.advertisement.project.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class HikariCPDataSource {
    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource ds;


    static {
        Properties properties = new Properties();
        try(FileInputStream fis =  new FileInputStream("db.properties")){
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }

        config.setJdbcUrl(properties.getProperty("dataSource.url"));
        config.setUsername(properties.getProperty("dataSource.username"));
        config.setPassword(properties.getProperty("dataSource.password"));
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        ds = new HikariDataSource(config);
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    private HikariCPDataSource(){}
}