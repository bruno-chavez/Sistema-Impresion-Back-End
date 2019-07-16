package cl.usm.prevencionderiesgos.si.models;

import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
public class PDF {

    public PDF() { }

    @Id
    @GeneratedValue
    private Integer id;
    @Type(type="org.hibernate.type.BinaryType")
    private byte[] file;
    @ManyToOne
    @JoinColumn
    private Student student;


    public byte[] getFile() { return file; }

    public void setFile(byte[] file) { this.file = file; }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Student getStudent() { return student; }

    public void setStudent(Student student) { this.student = student; }
}