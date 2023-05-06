package kz.bitlab.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kz.bitlab.db.DBConnection;
import kz.bitlab.models.Category;
import kz.bitlab.models.News;
import kz.bitlab.models.Users;

import java.io.IOException;


@WebServlet(value = "/save-news")
public class ToUpdateNewsServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Users users = (Users) request.getSession().getAttribute("currentUser");
        if (users != null) {
            String title = request.getParameter("title");
            String content = request.getParameter("content");
            Long id = Long.parseLong(request.getParameter("id"));

            News news = DBConnection.getNewsbyId(id);
            if (news != null) {
                news.setTitle(title);
                news.setContent(content);
                Category category = new Category();
                news.setCategory(category);

                DBConnection.AddNews(news);
                response.sendRedirect("/news-details?id=" + id);

            } else {
                response.sendRedirect("/login");
            }
        }
    }
}
