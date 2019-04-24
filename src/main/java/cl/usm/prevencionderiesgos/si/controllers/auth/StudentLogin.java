package cl.usm.prevencionderiesgos.si.controllers.auth;

import cl.usm.prevencionderiesgos.si.repositories.StudentRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class StudentLogin {

    private final StudentRepository repository;

    StudentLogin(StudentRepository repository) {
        this.repository = repository;
    }

}
