package com.example.coursework3;

public class Role {
    private Long id;
    private Long permitionId;
    private String nameRole;
    private String description;
    private Permition permition;

    public Role(){}

    public Role(Long id, Long permitionId, String nameRole, String description) {
        this.id = id;
        this.permitionId = permitionId;
        this.nameRole = nameRole;
        this.description = description;
    }

    public Role(Long id, Long permitionId, String nameRole, String description, Permition permition) {
        this.id = id;
        this.permitionId = permitionId;
        this.nameRole = nameRole;
        this.description = description;
        this.permition = permition;
    }
    public Role(Long permitionId, String nameRole, String description) {
        this.permitionId = permitionId;
        this.nameRole = nameRole;
        this.description = description;
    }

    public Role(Long permitionId, String nameRole, String description, Permition permition) {
        this.permitionId = permitionId;
        this.nameRole = nameRole;
        this.description = description;
        this.permition = permition;
    }

    public Long getId() {
        return id;
    }


    public String getNameRole() {
        return nameRole;
    }

    public String getDescription() {
        return description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNameRole(String nameRole) {
        this.nameRole = nameRole;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getPermitionId() {
        return permition.getId();
    }
    public void setPermitionId(Permition permition) {
        this.permition = permition;
    }

    public String toString() {
        return "User {" + "Id = " + id + ", permitionId = " + permitionId+", nameRole = " + nameRole +", discription = " + description
                + "}";
    }
}
