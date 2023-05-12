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

@WebServlet(name = "EditRoleServlet", value = "/editrole")
public class EditRoleServlet extends HttpServlet{
    String select_all_roles = "SELECT * FROM public.roles ORDER BY id ASC";
    String select_all_permitions = "SELECT * FROM public.permition ORDER BY id ASC;";
    String select_roles_byid = "SELECT * FROM public.roles WHERE id = ?;";
    String edit_roles = "UPDATE public.roles SET permitionid= ?, namerole= ?, descroption= ? WHERE id = ?;";
    ArrayList<Role> roles = new ArrayList<Role>();
    ArrayList<Role> editroles = new ArrayList<Role>();
    ArrayList<Permition> permitions = new ArrayList<Permition>();
    String rolesPath;

    private Permition FindById(Long id, ArrayList<Permition> permitions) {
        if(permitions != null) {
            for(Permition r: permitions) {
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
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(select_all_permitions);

                permitions.clear();
                while (rs.next()) {
                    permitions.add(new Permition(rs.getLong("id"),
                            rs.getString("namepermition"),
                            rs.getString("descroption"),
                            rs.getDate("dateCreate")));
                }
                stmt.close();
                request.setAttribute("permitions", permitions);


            Long idPermition;
            stmt = conn.createStatement();
            rs = stmt.executeQuery(select_all_roles);
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
            stmt.close();
            request.setAttribute("roles", roles);


            try (PreparedStatement preparedStatement = conn.prepareStatement(select_roles_byid)) {
                preparedStatement.setLong(1, id);
                rs = preparedStatement.executeQuery();
                if(rs != null) {
                    editroles.clear();
                    while (rs.next()) {
                        idPermition = rs.getLong("permitionid");
                        editroles.add(new Role(rs.getLong("id"),
                                idPermition,
                                rs.getString("namerole"),
                                rs.getString("descroption"),
                                FindById(idPermition, permitions)));
                    }
                    rs.close();
                    request.setAttribute("rolesEdit", editroles);
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

        rolesPath = request.getServletPath();
        if("/editrole".equals(rolesPath)) {
            request.getRequestDispatcher("/jspf/editrole.jsp").forward(request, response);

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

            //Long permitionid = Long.valueOf(request.getParameter("permitionid"));;
            String namerole = request.getParameter("namerole");
            String descroption = request.getParameter("descroption");

            String permition = request.getParameter("permitionid");
            int index1 = permition.indexOf('=');
            int index2 = permition.indexOf(",");
            String r1 = permition.substring(index1+1, index2);
            Long permitionid = Long.valueOf(r1.trim());

            try (PreparedStatement preparedStatement = conn.prepareStatement(edit_roles)){
                preparedStatement.setLong(1, permitionid);
                preparedStatement.setString(2, namerole);
                preparedStatement.setString(3, descroption);
                preparedStatement.setLong(4, id);
                int result = preparedStatement.executeUpdate();
            } catch (Exception e) {
                System.out.println(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            getServletContext().getRequestDispatcher("/jspf/editrole.jsp").forward(request, response);
        }

        doGet(request, response);
    }
}

