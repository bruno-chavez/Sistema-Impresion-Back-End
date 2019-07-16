package cl.usm.prevencionderiesgos.si.controllers.auth;

import cl.usm.prevencionderiesgos.si.models.Admin;
import cl.usm.prevencionderiesgos.si.repositories.AdminRepository;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/auth/admin")
public class AdminLogin {
    private final AdminRepository repository;

    AdminLogin(AdminRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public Admin CreateSession(@RequestBody Admin admin, HttpServletRequest request) {

        HttpSession session = request.getSession();
        session.setAttribute("admin-email", admin.getEmail());

        return repository.save(admin);
    }
}
