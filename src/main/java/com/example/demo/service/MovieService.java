package com.example.demo.service;

import com.example.demo.movie.Movie;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;

@Service
public class MovieService {
    public void create(Movie movie) {
        try (Connection conn = DriverManager.getConnection(MariaDbConstant.DB_URL, MariaDbConstant.USER, MariaDbConstant.PASS)) {
                String query = "INSERT INTO kino(name,genre,duration) VALUES(?,?,?);";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setString(1, movie.getName());
                preparedStatement.setString(2, movie.getGenre());
                preparedStatement.setString(3, movie.getDuration());
                preparedStatement.executeQuery();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<Movie> selectAllMovies() {
        ArrayList<Movie> movies = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(MariaDbConstant.DB_URL, MariaDbConstant.USER, MariaDbConstant.PASS)) {
            String query = "SELECT * FROM kino";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Movie movie = new Movie();
                movie.setName(resultSet.getString("name"));
                movie.setGenre(resultSet.getString("genre"));
                movie.setDuration(resultSet.getString("duration"));
                movies.add(movie);
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return movies;
    }

    public Movie findMovieById(int id) {
        Movie movie = new Movie();
        try (Connection conn = DriverManager.getConnection(MariaDbConstant.DB_URL, MariaDbConstant.USER, MariaDbConstant.PASS)) {
                String query = "SELECT * FROM kino WHERE id = ?";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setInt(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    movie.setName(resultSet.getNString("name"));
                    movie.setGenre(resultSet.getString("genre"));
                    movie.setDuration(resultSet.getString("duration"));
                }
                resultSet.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return movie;
    }

    public void update(Movie movie, int id) {
        try (Connection conn = DriverManager.getConnection(MariaDbConstant.DB_URL, MariaDbConstant.USER, MariaDbConstant.PASS)) {
                String query = "UPDATE kino SET name = ?,genre = ?,duration = ? WHERE id = ?";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setString(1, movie.getName());
                preparedStatement.setString(2, movie.getGenre());
                preparedStatement.setString(3, movie.getDuration());
                preparedStatement.setInt(4, id);
                preparedStatement.executeQuery();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void delete(int id) {
        try (Connection conn = DriverManager.getConnection(MariaDbConstant.DB_URL, MariaDbConstant.USER, MariaDbConstant.PASS)) {
                String query = "Delete FROM kino WHERE id = ?";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setInt(1,id);
                preparedStatement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
