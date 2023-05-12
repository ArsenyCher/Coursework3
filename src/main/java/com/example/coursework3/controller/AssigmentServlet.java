package com.example.coursework3.controller;

import com.example.coursework3.Assigment;
import com.example.coursework3.Permition;
import com.example.coursework3.Role;
import com.example.coursework3.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

@WebServlet(name = "AssigmentServlet", value = "/assigment")
public class AssigmentServlet extends HttpServlet {

    private Permition FindByIdPermition(Long id, ArrayList<Permition> permitions) {
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

    private Role FindByIdRole(Long id, ArrayList<Role> roles) {
        if(roles != null) {
            for(Role r: roles) {
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

    private User FindByIdUser(Long id, ArrayList<User> users) {
        if(users != null) {
            for(User r: users) {
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
        String select_all_role = "SELECT * FROM public.roles ORDER BY id ASC";
        String select_all_user = "SELECT * FROM public.users ORDER BY id ASC";
        String select_all_assigment = "SELECT * FROM public.assigment ORDER BY id";
        String select_all_permition = "SELECT * FROM public.permition ORDER BY id";
        response.setContentType("text/html");
        ArrayList<Assigment> assigments = new ArrayList<Assigment>();
        ArrayList<Role> roles = new ArrayList<Role>();
        ArrayList<User> users = new ArrayList<User>();
        ArrayList<Permition> permitions = new ArrayList<Permition>();
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            Connection conn = DriverManager.getConnection
                    ("jdbc:postgresql://localhost:5432/user_accounting_applications", "postgres", "a53mx0z29_-");

            Statement stmn = conn.createStatement();
            ResultSet rs = stmn.executeQuery(select_all_permition);

            permitions.clear();
            while (rs.next()) {
                permitions.add(new Permition(rs.getLong("id"),
                        rs.getString("namepermition"),
                        rs.getString("descroption"),
                        rs.getDate("datecreate")));
            }
            stmn.close();
            request.setAttribute("permitions", permitions);

            stmn = conn.createStatement();
            rs = stmn.executeQuery(select_all_role);
            Long idPermition;
            roles.clear();
                while (rs.next()) {
                    idPermition = rs.getLong("permitionid");
                    roles.add(new Role(rs.getLong("id"),
                            idPermition,
                            rs.getString("namerole"),
                            rs.getString("descroption"),
                            FindByIdPermition(idPermition, permitions)));
                }
                stmn.close();
                request.setAttribute("roles", roles);


            stmn = conn.createStatement();
            rs = stmn.executeQuery(select_all_user);

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


            Long idRole;
            Long idUser;
            stmn = conn.createStatement();
            rs = stmn.executeQuery(select_all_assigment);
            assigments.clear();
            while (rs.next()) {
                idRole = rs.getLong("roleid");
                idUser = rs.getLong("userid");
                assigments.add(new Assigment(rs.getLong("id"),
                        idUser,
                        idRole,
                        rs.getDate("datecreate"),
                        FindByIdUser(idUser, users),
                        FindByIdRole(idRole, roles)
                ));
            }
            stmn.close();
            request.setAttribute("assigments", assigments);


        } catch (SQLException e) {
            e.printStackTrace();
        }

        if("/assigment".equals(request.getServletPath())){
            request.getRequestDispatcher("/jspf/assigment.jsp").forward(request, response);
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
        String insert_assigment = "INSERT INTO public.assigment(userid, roleid, datecreate)VALUES (?, ?, ?)";

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            Connection conn = DriverManager.getConnection
                    ("jdbc:postgresql://localhost:5432/user_accounting_applications", "postgres", "a53mx0z29_-");


            String user = request.getParameter("userid");;
            String role = request.getParameter("roleid");
            Date datecreate = Date.valueOf(request.getParameter("datecreate"));
            int index1 = user.indexOf('=');
            int index2 = user.indexOf(",");
            String u1 = user.substring(index1+1, index2);
            Long userid = Long.parseLong(u1.trim());

            int index3 = user.indexOf('=');
            int index4 = user.indexOf(",");
            String u2 = role.substring(index3+1, index4);
            long roleid = Long.parseLong(u2.trim());

            Assigment newrole = new Assigment(userid, roleid, datecreate);

            try (PreparedStatement preparedStatement = conn.prepareStatement(insert_assigment)){
                preparedStatement.setLong(1, userid);
                preparedStatement.setLong(2, roleid);
                preparedStatement.setDate(3, newrole.getDateCreate());
                int result = preparedStatement.executeUpdate();
            } catch (Exception e) {
                System.out.println(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            getServletContext().getRequestDispatcher("/jspf/assigment.jsp").forward(request, response);

        }
        doGet(request, response);
    }
}
