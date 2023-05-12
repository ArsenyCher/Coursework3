package com.example.coursework3.controller;

import com.example.coursework3.Permition;
import com.example.coursework3.Role;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

@WebServlet(name = "RoleServlet", value = "/role")
public class RoleServlet extends HttpServlet {

    // Поиск голоса по id
    private Permition FindById(Long id, ArrayList<Permition> votes) {
        if(votes != null) {
            for(Permition r: votes) {
                if((r.getId()).equals(id)) {
                    return r;
                }
            }
        }
        else {
            return null;
        }
        return null;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String select_all_role = "SELECT id, permitionid, namerole, descroption FROM roles ORDER BY id ASC";
        String select_all_permition = "SELECT id, namepermition, descroption, datecreate FROM public.permition ORDER BY id ASC";
        response.setContentType("text/html");
        ArrayList<Role> roles = new ArrayList<Role>();
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

            Long idPermition;
            stmn = conn.createStatement();
            rs = stmn.executeQuery(select_all_role);
            if(rs != null) {
                roles.clear();
                while (rs.next())
                {
                    idPermition = rs.getLong("permitionid");
                    roles.add(new Role(rs.getLong("id"),
                            idPermition,
                            rs.getString("namerole"),
                            rs.getString("descroption"),
                            FindById(idPermition, permitions)));
                }
                stmn.close();
                request.setAttribute("roles", roles);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if("/role".equals(request.getServletPath())){
            request.getRequestDispatcher("/jspf/role.jsp").forward(request, response);
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);

        String insert_role = "INSERT INTO public.roles(permitionid, namerole, descroption)VALUES (?, ?, ?)";

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/user_accounting_applications", "postgres", "a53mx0z29_-");
            String permition =request.getParameter("permition");;
            String namerole = request.getParameter("namerole");
            String descroption = request.getParameter("descroption");
            int index1 = permition.indexOf('=');
            int index2 = permition.indexOf(",");
            String r1 = permition.substring(index1+1, index2);
            long permitionid = Long.parseLong(r1.trim());

            Role newrole = new Role(permitionid, namerole, descroption);

            try (PreparedStatement preparedStatement = conn.prepareStatement(insert_role)){
                preparedStatement.setLong(1, permitionid);
                preparedStatement.setString(2, newrole.getNameRole());
                preparedStatement.setString(3, newrole.getDescription());
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