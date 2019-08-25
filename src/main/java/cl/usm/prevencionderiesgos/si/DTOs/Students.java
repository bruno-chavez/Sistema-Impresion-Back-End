package cl.usm.prevencionderiesgos.si.DTOs;

import java.util.List;

public class Students {

    private List<StudentInfo> students;

    public Students(List<StudentInfo> students) { this.students = students; }

    public List<StudentInfo> getStudents() { return students; }
}
