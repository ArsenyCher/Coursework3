package com.example.coursework3.controller;

import com.example.coursework3.Permition;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

@WebServlet(name = "DeletePermitionServlet", value = "/deletepermition")
public class DeletePermitionServlet extends HttpServlet{
    String select_all_permitions = "SELECT * FROM public.permition ORDER BY id ASC;";
    String select_permition_byid = "SELECT * FROM public.permition WHERE id = ?;";
    String delete_permition = "DELETE FROM public.permition WHERE id = ?;";
    ArrayList<Permition> permitions = new ArrayList<Permition>();
    ArrayList<Permition> deletepermitions = new ArrayList<Permition>();
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
            ResultSet rs = stmt.executeQuery(select_all_permitions);
            permitions.clear();
            while (rs.next()) {
                permitions.add(new Permition(rs.getLong("id"),
                        rs.getString("namepermition"),
                        rs.getString("descroption"),
                        rs.getDate("datecreate")));
            }
            stmt.close();
            request.setAttribute("permitions", permitions);


            try (PreparedStatement preparedStatement = conn.prepareStatement(select_permition_byid)) {
                preparedStatement.setLong(1, id);
                rs = preparedStatement.executeQuery();
                if(rs != null) {
                    deletepermitions.clear();
                    while (rs.next()) {
                        deletepermitions.add(new Permition(rs.getLong("id"),
                                rs.getString("namepermition"),
                                rs.getString("descroption"),
                                rs.getDate("datecreate")));
                    }
                    rs.close();
                    request.setAttribute("permitionsDelete", deletepermitions);
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
        if("/deletepermition".equals(userPath)) {
            request.getRequestDispatcher("/jspf/deletepermition.jsp").forward(request, response);

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

            try (PreparedStatement preparedStatement =
                         conn.prepareStatement(delete_permition)) {
                preparedStatement.setLong(1, id);
                int result = preparedStatement.executeUpdate();
            } catch (Exception e) {
                System.out.println(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            getServletContext().getRequestDispatcher("/jspf/deletepermition.jsp").forward(request, response);
        }

        doGet(request, response);
    }
}
