package cl.usm.prevencionderiesgos.si.controllers.auth;

import cl.usm.prevencionderiesgos.si.models.Student;
import cl.usm.prevencionderiesgos.si.repositories.StudentRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping("/register")
public class Register {

    private final StudentRepository repository;
    private final PasswordEncoder passwordEncoder;

    Register(StudentRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }


    @PostMapping
    Student PostRegister(@RequestBody Student student) {

        student.setPassword(passwordEncoder.encode(student.getPassword()));

        return repository.save(student);
    }
}
