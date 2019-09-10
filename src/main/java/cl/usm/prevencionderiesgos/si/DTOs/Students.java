package cl.usm.prevencionderiesgos.si.DTOs;

import java.util.List;

public class Students {

    private List<StudentNameEmail> students;

    public Students(List<StudentNameEmail> students) { this.students = students; }

    public List<StudentNameEmail> getStudents() { return students; }
}
