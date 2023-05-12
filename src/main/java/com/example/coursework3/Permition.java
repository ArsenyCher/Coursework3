package com.example.coursework3;


import java.util.Date;

public class Permition {
    private Long id;
    private String permitionName;
    private String description;
    private Date dateCreate;

    public Permition(Long id, String permitionName, String description, Date dateCreate) {
        this.id = id;
        this.permitionName = permitionName;
        this.description = description;
        this.dateCreate = dateCreate;
    }
    public Permition(String permitionName, String description, Date dateCreate) {
        this.permitionName = permitionName;
        this.description = description;
        this.dateCreate = dateCreate;
    }
    public Permition(){}

    public Long getId() {
        return id;
    }

    public String getPermitionName() {
        return permitionName;
    }

    public String getDescription() {
        return description;
    }

    public java.sql.Date getDateCreate() {
        return (java.sql.Date) dateCreate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPermitionName(String permitionName) {
        this.permitionName = permitionName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public String toString() {
        return "User {" + "Id = " + id + ", permitionName = " + permitionName+", dateCreate = " + dateCreate +", discription = " + description
                + "}";
    }
}
