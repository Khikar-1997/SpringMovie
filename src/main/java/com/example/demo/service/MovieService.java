package com.example.demo.service;

import com.example.demo.movie.Movie;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;

@Service
public class MovieService {
    public void create(Movie movie) {
        try (Connection conn = DriverManager.getConnection(MariaDbConstant.DB_URL, MariaDbConstant.USER, MariaDbConstant.PASS)) {
            if (conn != null) {
                String query = "INSERT INTO movie(name,genre,duration) VALUES(?,?,?);";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setString(1, movie.getName());
                preparedStatement.setString(2, movie.getGenre());
                preparedStatement.setString(3, movie.getDuretion());
                preparedStatement.executeQuery();
            }
        } catch (SQLException ex) {
            System.out.println("!!!!!!!!");
        }
    }

    public ArrayList<Movie> selectAllMovies() {
        ArrayList<Movie> movies = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(MariaDbConstant.DB_URL, MariaDbConstant.USER, MariaDbConstant.PASS)) {
            String query = "SELECT * FROM movie";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Movie movie = new Movie();
                movie.setName(resultSet.getString("name"));
                movie.setGenre(resultSet.getString("genre"));
                movie.setDuretion(resultSet.getString("duretion"));
                movies.add(movie);
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return movies;
    }

    public Movie findById(int id) {
        Movie movie = new Movie();
        try (Connection conn = DriverManager.getConnection(MariaDbConstant.DB_URL, MariaDbConstant.USER, MariaDbConstant.PASS)) {
            if (conn != null) {
                String query = "SELECT * FROM movie WHERE id = ?";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setInt(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    movie.setName(resultSet.getNString("name"));
                    movie.setGenre(resultSet.getString("genre"));
                    movie.setDuretion(resultSet.getString("duration"));
                }
                resultSet.close();
            }
        } catch (SQLException ex) {
            System.out.println("!!!!");
        }
        return movie;
    }

    public void update(Movie movie, int id) {
        try (Connection conn = DriverManager.getConnection(MariaDbConstant.DB_URL, MariaDbConstant.USER, MariaDbConstant.PASS)) {
            if (conn != null) {
                String query = "UPDATE movie SET name = ?,genre = ?,duration = ? WHERE id = ?";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setString(1, movie.getName());
                preparedStatement.setString(2, movie.getGenre());
                preparedStatement.setString(3, movie.getDuretion());
                preparedStatement.setInt(4, id);
                preparedStatement.executeQuery();
            }
        } catch (SQLException ex) {
            System.out.println("!!!!!");
        }
    }

    public void delete(int id) {
        try (Connection conn = DriverManager.getConnection(MariaDbConstant.DB_URL, MariaDbConstant.USER, MariaDbConstant.PASS)) {
            if (conn != null){
                String query = "Delete FROM movie WHERE id = ?";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setInt(1,id);
                preparedStatement.execute();
            }
        } catch (SQLException ex) {
            System.out.println("!!!!!!");
        }
    }
}
