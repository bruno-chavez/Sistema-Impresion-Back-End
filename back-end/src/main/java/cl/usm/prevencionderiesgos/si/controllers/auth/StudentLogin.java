package cl.usm.prevencionderiesgos.si.controllers.auth;

import cl.usm.prevencionderiesgos.si.models.Student;
import cl.usm.prevencionderiesgos.si.repositories.StudentRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/auth/login")
public class StudentLogin {

    private final StudentRepository repository;
    private final PasswordEncoder passwordEncoder;

    StudentLogin(StudentRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }


    @PostMapping
    String PostStudentLogin(@RequestBody Student student, HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();
        session.setAttribute("student-email", student.getEmail());

        if (passwordEncoder.matches(student.getPassword(), repository.findByEmail(student.getEmail()).getPassword())) {
            return "authenticated";
        } else {
            return "error";
        }
    }

    // Temporal endpoint, used to check if sessions are created
    @GetMapping
    Object CheckSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return session.getAttribute("student-email");
    }
}
