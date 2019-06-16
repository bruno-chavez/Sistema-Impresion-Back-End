package cl.usm.prevencionderiesgos.si.controllers.auth;

import cl.usm.prevencionderiesgos.si.models.Admin;
import cl.usm.prevencionderiesgos.si.repositories.AdminRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping("/admin")
public class AdminLogin {
    private final AdminRepository repository;

    AdminLogin(AdminRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    Admin PostAdminLogin(@RequestBody Admin admin) {
        return repository.save(admin);
    }
}
