package kz.bitlab.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kz.bitlab.db.DBConnection;
import kz.bitlab.models.Category;
import kz.bitlab.models.Comments;
import kz.bitlab.models.News;
import kz.bitlab.models.Users;

import java.io.IOException;


@WebServlet(value = "/add-comment")
public class ToAddCommentServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Users users = (Users) request.getSession().getAttribute("currentUser");
        if(users!=null){
            String comment = request.getParameter("comment");
            Long newsId = Long.parseLong(request.getParameter("news_id"));

            News news = DBConnection.getNewsbyId(newsId);
            if(news!=null){
                Comments comments = new Comments();
                comments.setNews(news);
                comments.setUsers(users);
                comments.setComment(comment);
                DBConnection.addComment(comments);
            }
            response.sendRedirect("/news-details?id="+newsId);
        }else{
            response.sendRedirect("/login");
        }
    }
}
