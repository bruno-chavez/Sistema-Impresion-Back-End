package cl.usm.prevencionderiesgos.si.controllers.admin;

import cl.usm.prevencionderiesgos.si.DTOs.StudentInfo;
import cl.usm.prevencionderiesgos.si.DTOs.Students;
import cl.usm.prevencionderiesgos.si.models.Student;
import cl.usm.prevencionderiesgos.si.repositories.StudentRepository;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin/users")
public class ShowUsers {

    private final StudentRepository studentRepository;

    ShowUsers(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping
    @ResponseBody
    public Students CreateSession() {

        List<Student> students = studentRepository.findAll();

        // Iterates over the all the students to make a student list
        List<StudentInfo> studentsInfo = new ArrayList<>();

        for (Student std : students) {
            studentsInfo.add(new StudentInfo(std.getName(), std.getEmail()));
        }

        return new Students(studentsInfo);
    }
}
