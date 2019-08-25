package cl.usm.prevencionderiesgos.si.DTOs;

public class StudentInfo {

    private String name;
    private String email;

    public StudentInfo(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() { return name; }

    public String getEmail() { return email; }
}
