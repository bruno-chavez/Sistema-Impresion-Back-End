package cl.usm.prevencionderiesgos.si.controllers.auth;

import cl.usm.prevencionderiesgos.si.models.Admin;
import cl.usm.prevencionderiesgos.si.repositories.AdminRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminLogin {
    private final AdminRepository repository;

    AdminLogin(AdminRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    Admin create(@RequestBody Admin admin) {
        return repository.save(admin);
    }
}
