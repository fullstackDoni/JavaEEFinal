package kz.bitlab.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kz.bitlab.db.DBConnection;
import kz.bitlab.models.Users;

import java.io.IOException;

@WebServlet(value = "/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String re_password = req.getParameter("re_password");
        String fullName = req.getParameter("full_name");


            Users users1 = new Users();
            users1.setFull_name(fullName);
            users1.setPassword(password);
            users1.setEmail(email);
            Users u = DBConnection.createUser(users1);
            resp.sendRedirect("/login");


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/register.jsp").forward(req,resp);
    }
}
