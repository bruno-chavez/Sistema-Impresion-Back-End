package cl.usm.prevencionderiesgos.si.controllers.auth;

import cl.usm.prevencionderiesgos.si.repositories.AdminRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminLogin {
    private final AdminRepository repository;

    AdminLogin(AdminRepository repository) {
        this.repository = repository;
    }
}
