package cl.usm.prevencionderiesgos.si.controllers.admin;

import cl.usm.prevencionderiesgos.si.DTOs.Message;
import cl.usm.prevencionderiesgos.si.models.Student;
import cl.usm.prevencionderiesgos.si.repositories.StudentRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/admin/reset")
public class ResetPages {

    private final StudentRepository studentRepository;

    ResetPages(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping
    @ResponseBody
    public Message reset(HttpServletRequest request) {
        // If no session exists it will return null
        if (request.getSession(false) != null) {

            // Changes all the students pages to 0, used with caution!
            Iterable<Student> students = studentRepository.findAll();
            for (Student student : students) {
                student.setPages(0);
                studentRepository.save(student);
            }

            return new Message("Pages for students got reset");
        } else {
            return new Message("You must be logged in to perform this action");
        }
    }
}
