package cl.usm.prevencionderiesgos.si.controllers.student;

import cl.usm.prevencionderiesgos.si.DTOs.StudentPagesDocs;
import cl.usm.prevencionderiesgos.si.models.Student;
import cl.usm.prevencionderiesgos.si.repositories.StudentRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/student/info")
public class StudentInfo {
    private final StudentRepository repository;

    StudentInfo(StudentRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    @ResponseBody
    public StudentPagesDocs GetInfo(HttpServletRequest request){

        HttpSession session = request.getSession();
        Object email = session.getAttribute("student-email");
        Object type = session.getAttribute("type");

        Student student = repository.findByEmail(email.toString());

        int pages = 0;
        if (type.toString().equals("Regular")) {
            pages = 250-student.getPages();
        } else if (type.toString().equals("Memorista")) {
            pages = 300-student.getPages();
        }


        return new StudentPagesDocs(pages, student.getDocs());
    }
}
