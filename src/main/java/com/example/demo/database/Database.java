package com.example.demo.database;

import com.example.demo.service.MariaDbConstant;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Database {
    private static Database instance = new Database();

    private Database() {
    }

    private void createDatabase() {
        try (Connection conn = DriverManager.getConnection(MariaDbConstant.DB_URL_SEREVER, MariaDbConstant.USER, MariaDbConstant.PASS)) {
            String query = "CREATE DATABASE kino";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.execute();
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }

    private void createMovieTable() {
        try (Connection conn = DriverManager.getConnection(MariaDbConstant.DB_URL, MariaDbConstant.USER, MariaDbConstant.PASS)) {
            String query = "CREATE TABLE kino" +
                    "(" +
                    "id INT NOT NULL AUTO_INCREMENT," +
                    "name VARCHAR(255) NOT NULL ," +
                    "genre ENUM('COMEDY'," +
                    "    'FANTASY'," +
                    "    'THRILLER'," +
                    "    'HORROR'," +
                    "    'BIOGRAPHY')," +
                    "duration VARCHAR(255) NOT NULL ," +
                    "PRIMARY KEY (id)" +
                    ");";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.execute();
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }

    public static void databaseInitializer() {
        Database database = Database.instance;
        database.createDatabase();
        database.createMovieTable();
    }
}
