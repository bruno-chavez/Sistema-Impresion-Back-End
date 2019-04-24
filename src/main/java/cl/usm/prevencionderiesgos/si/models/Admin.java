package cl.usm.prevencionderiesgos.si.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Admin {
    @Id
    @GeneratedValue
    private Integer id;
    private String email;
    private String password;
}
