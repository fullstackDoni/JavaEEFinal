package kz.bitlab.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kz.bitlab.db.DBConnection;
import kz.bitlab.models.News;
import kz.bitlab.models.Category;
import kz.bitlab.models.Users;

import java.io.IOException;


@WebServlet(value = "/add-news")
public class ToAddNewsServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Users users = (Users) request.getSession().getAttribute("currentUser");
        if(users!=null){
            String title = request.getParameter("title");
            String content = request.getParameter("content");


            News news = new News();
            news.setTitle(title);
            news.setContent(content);
            Category category = new Category();
            news.setCategory(category);

            DBConnection.AddNews(news);
            response.sendRedirect("/add-news-page");

        }else{
            response.sendRedirect("/login");
        }
    }
}
