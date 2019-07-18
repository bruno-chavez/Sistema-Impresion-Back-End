package cl.usm.prevencionderiesgos.si.controllers.auth;

import cl.usm.prevencionderiesgos.si.DTOs.Message;
import cl.usm.prevencionderiesgos.si.models.Admin;
import cl.usm.prevencionderiesgos.si.repositories.AdminRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/auth/admin")
public class AdminLogin {

    private final AdminRepository repository;
    private final PasswordEncoder passwordEncoder;


    AdminLogin(AdminRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping
    @ResponseBody
    public Message CreateSession(@RequestBody Admin admin, HttpServletRequest request) {

        if (passwordEncoder.matches(admin.getPassword(), repository.findByEmail(admin.getEmail()).getPassword())){

            HttpSession session = request.getSession();
            session.setAttribute("admin-email", admin.getEmail());

            return new Message("Authenticated");
        } else {
            return new Message("Authentication failed");
        }
    }
}
