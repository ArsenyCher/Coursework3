package com.example.coursework3;

public class User {
    private Long id;
    private String userName;
    private String password;
    private String email;
    private Boolean status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public User(long id, long permitionid, String namerole, String description){

    }

    public User(Long id, String userName, String password, String email, Boolean status) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.status = status;
    }
    public User(String userName, String password, String email, Boolean status) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.status = status;
    }

    @Override
    public String toString() {
        return "User {" + "Id = " + id + ", userName = " + userName+", email = " + email +", status = " + status
                + "}";
    }

}
