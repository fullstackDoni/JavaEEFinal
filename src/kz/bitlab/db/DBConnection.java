package kz.bitlab.db;

import kz.bitlab.models.Users;

import java.sql.*;

public class DBConnection {
    private static Connection connection;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/finaljavaee", "root", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Users getUser(String email) {
        Users user = new Users();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE email=?");
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user.setId(resultSet.getInt("id"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setFull_name(resultSet.getString("full_name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public static Users createUser(Users user) {
        try {
            PreparedStatement statement = connection.prepareStatement(" INSERT INTO users (email,password,full_name) VALUES (? ,?, ?) ");
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFull_name());
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }


}
