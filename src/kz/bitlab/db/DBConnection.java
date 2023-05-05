package kz.bitlab.db;

import kz.bitlab.models.Users;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBConnection {
    private static Connection connection;
    static{
     try {
         Class.forName("com.mysql.cj.jdbc.Driver");
         connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/finaljavaee","root","");
     }catch (Exception e){
         e.printStackTrace();
     }
    }

    public static Users getUser(String email){
        Users users = null;
        try {
            PreparedStatement statement = connection.prepareStatement(""+
                    "SELECT *FROM users WHERE email=?");
            statement.setString(1,email);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                Users user = new Users();
                user.setId(resultSet.getInt("id"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setFull_name(resultSet.getString("full_name"));
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return users;
    }
}
