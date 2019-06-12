package cl.usm.prevencionderiesgos.si.models;

import javax.persistence.*;

@Entity
public class Student {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String email;
    private String password;
    private Integer used;

    @Enumerated(EnumType.STRING)
    private StudentType type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public StudentType getType() {
        return type;
    }

    public void setType(StudentType type) {
        this.type = type;
    }

    public Integer getUsed() {
        return used;
    }

    public void setUsed(Integer used) {
        this.used = used;
    }
}
