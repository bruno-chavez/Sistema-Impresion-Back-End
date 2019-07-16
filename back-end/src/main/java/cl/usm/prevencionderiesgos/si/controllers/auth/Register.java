package cl.usm.prevencionderiesgos.si.controllers.auth;

import cl.usm.prevencionderiesgos.si.models.Student;
import cl.usm.prevencionderiesgos.si.repositories.StudentRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth/register")
public class Register {

    private final StudentRepository repository;
    private final PasswordEncoder passwordEncoder;

    public Register(StudentRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }


    @PostMapping
    public String CreateUser(@RequestBody Student student) {

        student.setPassword(passwordEncoder.encode(student.getPassword()));
        student.setPages(0);

        repository.save(student);

        return "created";
    }
}
