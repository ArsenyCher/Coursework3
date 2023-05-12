package com.example.coursework3.controller;

import com.example.coursework3.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

@WebServlet(name = "UserServlet", value = "/user")
public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String select_all_user = "SELECT id, username, password, email, status FROM public.users ORDER BY id ASC";
        response.setContentType("text/html");
        ArrayList<User> users = new ArrayList<User>();
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/user_accounting_applications", "postgres", "a53mx0z29_-");

            Statement stmn = conn.createStatement();
            ResultSet rs = stmn.executeQuery(select_all_user);
            if(rs != null) {
                users.clear();
                while (rs.next()) {
                    users.add(new User(rs.getLong("id"),
                            rs.getString("username"),
                            rs.getString("password"),
                            rs.getString("email"),
                            rs.getBoolean("status")));
                }
                stmn.close();
                request.setAttribute("users", users);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        if("/user".equals(request.getServletPath())){
            request.getRequestDispatcher("/jspf/user.jsp").forward(request, response);
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
        String insert_user = "INSERT INTO public.users(username, password, email, status)VALUES (?, ?, ?, ?)";

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/user_accounting_applications", "postgres", "a53mx0z29_-");
            String username =request.getParameter("username");;
            String password = request.getParameter("password");
            String email = request.getParameter("email");
            Boolean status = Boolean.valueOf(request.getParameter("status"));

            User newuser = new User(username, password, email, status);

            try (PreparedStatement preparedStatement = conn.prepareStatement(insert_user)){
                preparedStatement.setString(1, newuser.getUserName());
                preparedStatement.setString(2, newuser.getPassword());
                preparedStatement.setString(3, newuser.getEmail());
                preparedStatement.setBoolean(4, newuser.getStatus());
                int result = preparedStatement.executeUpdate();
            } catch (Exception e) {
                System.out.println(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        doGet(request, response);
    }
}