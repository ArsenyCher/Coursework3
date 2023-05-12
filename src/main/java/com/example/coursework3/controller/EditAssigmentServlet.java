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

@WebServlet(name = "EditAssigmentServlet", value = "/editassigment")
public class EditAssigmentServlet extends HttpServlet {
    String select_all_role = "SELECT * FROM public.roles ORDER BY id ASC";
    String select_all_user = "SELECT * FROM public.users ORDER BY id ASC";
    String select_all_assigment = "SELECT * FROM public.assigment ORDER BY id";
    String select_all_permition = "SELECT * FROM public.permition ORDER BY id";
    String edit_assigment = "UPDATE public.assigment SET userid=?, roleid=?, datecreate=? WHERE id = ?;";
    String select_assigment_byid = "SELECT * FROM public.assigment WHERE id = ?;";
    ArrayList<Assigment> assigments = new ArrayList<Assigment>();
    ArrayList<Assigment> editassigments = new ArrayList<Assigment>();
    ArrayList<Role> roles = new ArrayList<Role>();
    ArrayList<User> users = new ArrayList<User>();
    ArrayList<Permition> permitions = new ArrayList<Permition>();

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
                id = Long.valueOf((strId));
            }

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


            try (PreparedStatement preparedStatement = conn.prepareStatement(select_assigment_byid)) {
                preparedStatement.setLong(1, id);
                rs = preparedStatement.executeQuery();
                if(rs != null) {
                    editassigments.clear();
                    while (rs.next()) {
                        idRole = rs.getLong("roleid");
                        idUser = rs.getLong("userid");
                        editassigments.add(new Assigment(rs.getLong("id"),
                                idUser,
                                idRole,
                                rs.getDate("datecreate"),
                                FindByIdUser(idUser, users),
                                FindByIdRole(idRole, roles)
                        ));
                    }
                    rs.close();
                    request.setAttribute("assigmentEdit", editassigments);
                }
                else
                {
                    System.out.println("Ошибка загрузки assigment");
                }
            } catch (Exception e) {
                System.out.println(e);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        if("/editassigment".equals(request.getServletPath())){
            request.getRequestDispatcher("/jspf/editassigment.jsp").forward(request, response);
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
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
                id = Long.valueOf((strId));
            }


            String user = request.getParameter("userid");;
            String role = request.getParameter("roleid");
            Date datecreate = Date.valueOf(request.getParameter("datecreate"));
            int index1 = user.indexOf('=');
            int index2 = user.indexOf(",");
            String u1 = user.substring(index1+1, index2);
            long userid = Long.parseLong(u1.trim());

            String u2 = role.substring(index1+1, index2);
            long roleid = Long.parseLong(u2.trim());


            try (PreparedStatement preparedStatement = conn.prepareStatement(edit_assigment)){
                preparedStatement.setLong(1, userid);
                preparedStatement.setLong(2, roleid);
                preparedStatement.setDate(3, datecreate);
                preparedStatement.setLong(4, id);
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
