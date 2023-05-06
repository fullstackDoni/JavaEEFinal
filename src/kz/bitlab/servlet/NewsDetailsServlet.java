package kz.bitlab.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kz.bitlab.db.DBConnection;
import kz.bitlab.models.Comments;
import kz.bitlab.models.News;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value = "/news-details")
public class NewsDetailsServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        News news = DBConnection.getNewsbyId(id);
        req.setAttribute("news",news);
        if(news!=null){
            ArrayList<Comments> comments = DBConnection.getComments(news.getId());
            req.setAttribute("comments",comments);
        }
        req.getRequestDispatcher("/newsdetails.jsp").forward(req,resp);
    }
}
