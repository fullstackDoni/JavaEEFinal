package kz.bitlab.db;

import kz.bitlab.models.Comments;
import kz.bitlab.models.News;
import kz.bitlab.models.Users;
import kz.bitlab.models.Category;
import java.sql.*;
import java.util.ArrayList;

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
                user.setRole(resultSet.getInt("role_id"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public static Users createUser(Users user) {
        try {
            PreparedStatement statement = connection.prepareStatement(" INSERT INTO users (email,password,full_name,role_id) VALUES (? ,?,?, ?) ");
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFull_name());
            statement.setInt(4,user.getRole());
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public static void AddNews(News news){
        try {
            PreparedStatement statement = connection.prepareStatement(""+
                    "INSERT INTO news(title,content,post_date,user_id,category_id)"+
                    "VALUES (?,?,NOW(),?,?)");
            statement.setString(1,news.getTitle());
            statement.setString(2,news.getContent());
            statement.setLong(3,news.getUsers().getId());
            statement.setLong(4,news.getCategory().getId());
            statement.executeUpdate();
            statement.close();


        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static ArrayList<News> getNews(){
        ArrayList<News> news = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(""+
                    "SELECT n.id,n.title,n.content,n.user_id,n.category_id"+
                    "FROM NEWS AS n"+
                    "INNER JOIN users u.id on n.user_id"+
                    "INNER JOIN news_categories cat on cat.id=n.category_id"+
                    "ORDER BY n.post_date desc");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                News news1 = new News();
                news1.setId(resultSet.getLong("id"));
                news1.setTitle(resultSet.getString("title"));
                news1.setContent(resultSet.getString("content"));
                news1.setPost_date(resultSet.getTimestamp("post_date"));
                news1.setCategory((new Category(resultSet.getLong("category_id"), resultSet.getString("name"))));
                news1.setUsers(new Users(resultSet.getInt("user_id"), resultSet.getString("full_name")));
                news.add(news1);
                statement.close();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return news;
    }
    public static News getNewsbyId(Long id){
        News news = null;
        try {
            PreparedStatement statement = connection.prepareStatement(""+
                    "SELECT n.id,n.title,n.content,n.user_id,n.category_id"+
                    "FROM NEWS AS n"+
                    "INNER JOIN users u.id on n.user_id"+
                    "INNER JOIN news_categories cat on cat.id=n.category_id"+
                    "WHERE n.id = ?");

            statement.setLong(1,id);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                news = new News();
                news.setId(resultSet.getLong("id"));
                news.setTitle(resultSet.getString("title"));
                news.setContent(resultSet.getString("content"));
                news.setPost_date(resultSet.getTimestamp("post_date"));
                news.setCategory((new Category(resultSet.getLong("category_id"), resultSet.getString("name"))));
                news.setUsers(new Users(resultSet.getInt("user_id"), resultSet.getString("full_name")));

                statement.close();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return news;
    }

    public static void updateNews(News news){
        try {
            PreparedStatement statement = connection.prepareStatement(""+
                    "update news set title=?,content=?"+
                    "where id=?");
            statement.setString(1,news.getTitle());
            statement.setString(2,news.getContent());
            statement.setLong(3,news.getCategory().getId());
            statement.setLong(3,news.getUsers().getId());
            statement.executeUpdate();
            statement.close();


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void addComment(Comments comments){
        try {

            PreparedStatement statement = connection.prepareStatement(""+
                    "INSERT INTO comment (comment,user_id,news_id,post_date)"+
                    "VALUES (?,?,?,NOW())");
            statement.setString(1,comments.getComment());
            statement.setLong(2,comments.getUsers().getId());
            statement.setLong(3,comments.getNews().getId());

            statement.executeUpdate();
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static ArrayList<Comments> getComments(Long newsId){

        ArrayList<Comments> comments = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(""+
                    "SELECT co.id,co.comment,co.post_date,co.news_id,co.user_id,u.full_name"+
                    "FROM comments co"+
                    "INNER JOIN users u on u.id=co.news_id"+
                    "WHERE co.news_id=?"+
                    "ORDER BY co.post_date desc");
            statement.setLong(1,newsId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Comments comments1 = new Comments();
                comments1.setId(resultSet.getLong("id"));
                comments1.setComment(resultSet.getString("comment"));
                comments1.setPost_date(resultSet.getTimestamp("post_date"));
                Users users = new Users();
                users.setId(resultSet.getInt("user_id"));
                users.setFull_name(resultSet.getString("full_name"));
                comments1.setUsers(users);
                News news = new News();
                news.setId(resultSet.getLong("news_id"));
                comments1.setNews(news);
                comments.add(comments1);
            }
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return comments;
    }

}
