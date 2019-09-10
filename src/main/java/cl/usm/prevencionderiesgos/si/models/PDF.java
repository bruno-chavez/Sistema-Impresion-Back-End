package cl.usm.prevencionderiesgos.si.models;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
public class PDF {

    public PDF() { }

    @Id
    @GeneratedValue
    private Integer id;

    private String title;
    private Integer pages;

    @CreationTimestamp
    private Date created_at;

    @ManyToOne()
    @JoinColumn
    private Student student;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Student getStudent() { return student; }

    public void setStudent(Student student) { this.student = student; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public Integer getPages() { return pages; }

    public void setPages(Integer pages) { this.pages = pages; }

    public Date getCreated_at() { return created_at; }

    public void setCreated_at(Date created_at) { this.created_at = created_at; }
}