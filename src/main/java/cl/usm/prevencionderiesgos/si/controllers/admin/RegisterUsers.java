package cl.usm.prevencionderiesgos.si.controllers.admin;

import cl.usm.prevencionderiesgos.si.DTOs.Message;
import cl.usm.prevencionderiesgos.si.models.Student;
import cl.usm.prevencionderiesgos.si.repositories.StudentRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/admin/register")
public class RegisterUsers {

    private final StudentRepository repository;
    private final PasswordEncoder passwordEncoder;

    public RegisterUsers(StudentRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }


    @PostMapping
    @ResponseBody
    public Message CreateUser(@RequestBody Student student, HttpServletResponse response) {

        // Hashes and salts student's password
        student.setPassword(passwordEncoder.encode(student.getPassword()));
        student.setPages(0);
        student.setDocs(0);

        // Saves student to the DB
        repository.save(student);

        // Sets "Created" HTTP Status
        response.setStatus(201);

        return new Message("Student created");
    }
}
