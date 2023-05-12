package com.example.coursework3;
import java.util.Date;

public class Assigment {
    private Long id;
    private Long userId;
    private Long roleId;
    private Date dateCreate;

    private User user;
    private Role role;

    public Assigment(Long id, Long userId, Long roleId, Date dateCreate) {
        this.id = id;
        this.userId = userId;
        this.roleId = roleId;
        this.dateCreate = dateCreate;
    }
    public Assigment(Long userId, Long roleId, Date dateCreate) {
        this.userId = userId;
        this.roleId = roleId;
        this.dateCreate = dateCreate;
    }

    public Assigment(Long id, Long userId, Long roleId, Date dateCreate, User user, Role role) {
        this.id = id;
        this.userId = userId;
        this.roleId = roleId;
        this.dateCreate = dateCreate;
        this.user = user;
        this.role = role;
    }

    public Assigment(Long userId, Long roleId, Date dateCreate, User user, Role role) {
        this.userId = userId;
        this.roleId = roleId;
        this.dateCreate = dateCreate;
        this.user = user;
        this.role = role;
    }

    public Assigment(){}

    public User getUser() {
        return user;
    }

    public Role getRole() {
        return role;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public java.sql.Date getDateCreate() {
        return (java.sql.Date) dateCreate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    @Override
    public String toString() {
        return "User {" + "Id = " + id + ", userId = " + userId+", roleId = " + roleId +", dateCreate = " + dateCreate
                + "}";
    }
}
