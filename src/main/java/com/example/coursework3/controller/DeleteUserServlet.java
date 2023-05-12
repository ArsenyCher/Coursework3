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

@WebServlet(name = "DeleteUserServlet", value = "/deleteuser")
public class DeleteUserServlet extends HttpServlet{
    String select_all_users = "SELECT * FROM public.users ORDER BY id ASC;";
    String select_user_byid = "SELECT * FROM public.users WHERE id = ?;";
    String delete_user = "DELETE FROM public.users WHERE id = ?;";
    ArrayList<User> users = new ArrayList<User>();
    ArrayList<User> deleteusers = new ArrayList<User>();
    String userPath;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/user_accounting_applications", "postgres", "a53mx0z29_-");

            String strId = request.getParameter("id");
            Long id = null; // id редактируемой должности
            if(strId != null) {
                id = Long.valueOf(strId);
            }
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(select_all_users);
            users.clear();
            while (rs.next()) {
                users.add(new User(rs.getLong("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getBoolean("status")
                ));
            }
            stmt.close();
            request.setAttribute("users", users);


            try (PreparedStatement preparedStatement = conn.prepareStatement(select_user_byid)) {
                preparedStatement.setLong(1, id);
                rs = preparedStatement.executeQuery();
                if(rs != null) {
                    deleteusers.clear();
                    while (rs.next()) {
                        deleteusers.add(new User(rs.getLong("id"),
                                rs.getString("username"),
                                rs.getString("password"),
                                rs.getString("email"),
                                rs.getBoolean("status")
                        ));
                    }
                    rs.close();
                    request.setAttribute("usersDelete", deleteusers);
                }
                else
                {
                    System.out.println("Ошибка загрузки user");
                }
            } catch (Exception e) {
                System.out.println(e);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        userPath = request.getServletPath();
        if("/deleteuser".equals(userPath)) {
            request.getRequestDispatcher("/jspf/deleteuser.jsp").forward(request, response);

        }
        //getServletContext().getRequestDispatcher("/jspf/users.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/user_accounting_applications", "postgres", "a53mx0z29_-");


            String strId = request.getParameter("id");
            Long id = null;
            if(strId != null) {
                id = Long.valueOf(strId);
            }

            try (PreparedStatement preparedStatement = conn.prepareStatement(delete_user)) {
                preparedStatement.setLong(1, id);
                int result = preparedStatement.executeUpdate();
            } catch (Exception e) {
                System.out.println(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            getServletContext().getRequestDispatcher("/jspf/deleteuser.jsp").forward(request, response);
        }

        doGet(request, response);
    }
}
