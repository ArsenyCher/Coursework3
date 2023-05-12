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

@WebServlet(name = "PermitionServlet", value = "/permition")
public class PermitionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String select_all_permition = "SELECT id, namepermition, descroption, datecreate FROM public.permition ORDER BY id ASC";
        response.setContentType("text/html");
        ArrayList<Permition> permitions = new ArrayList<Permition>();
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/user_accounting_applications", "postgres", "a53mx0z29_-");

            Statement stmn = conn.createStatement();
            ResultSet rs = stmn.executeQuery(select_all_permition);
            if(rs != null) {
                permitions.clear();
                while (rs.next()) {
                    permitions.add(new Permition(rs.getLong("id"),
                            rs.getString("namepermition"),
                            rs.getString("descroption"),
                            rs.getDate("dateCreate")));
                }
                stmn.close();
                request.setAttribute("permitions", permitions);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if("/permition".equals(request.getServletPath())){
            request.getRequestDispatcher("/jspf/permition.jsp").forward(request, response);
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
        String insert_permition = "INSERT INTO public.permition(namepermition, descroption, datecreate)VALUES (?, ?, ?)";

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/user_accounting_applications", "postgres", "a53mx0z29_-");
            String namepermition =request.getParameter("namepermition");;
            String descroption = request.getParameter("descroption");
            Date datecreate = Date.valueOf(request.getParameter("datecreate"));

            Permition permition = new Permition(namepermition, descroption, datecreate);

            try (PreparedStatement preparedStatement = conn.prepareStatement(insert_permition)){
                preparedStatement.setString(1, permition.getPermitionName());
                preparedStatement.setString(2, permition.getDescription());
                preparedStatement.setDate(3, permition.getDateCreate());
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