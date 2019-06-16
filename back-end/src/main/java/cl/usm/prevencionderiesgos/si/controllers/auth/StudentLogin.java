package cl.usm.prevencionderiesgos.si.controllers.auth;

import cl.usm.prevencionderiesgos.si.models.Student;
import cl.usm.prevencionderiesgos.si.repositories.StudentRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping("/login")
public class StudentLogin {

    private final StudentRepository repository;
    private final PasswordEncoder passwordEncoder;

    StudentLogin(StudentRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }


    @PostMapping
    Boolean PostStudentLogin(@RequestBody Student student) {
        return passwordEncoder.matches(student.getPassword(), repository.findByEmail(student.getEmail()).getPassword());
    }
}
