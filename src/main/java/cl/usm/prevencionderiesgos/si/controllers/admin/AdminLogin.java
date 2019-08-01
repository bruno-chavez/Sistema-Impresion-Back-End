package cl.usm.prevencionderiesgos.si.controllers.admin;

import cl.usm.prevencionderiesgos.si.DTOs.CreateSessionRequest;
import cl.usm.prevencionderiesgos.si.DTOs.Message;
import cl.usm.prevencionderiesgos.si.repositories.AdminRepository;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/admin/login")
public class AdminLogin {

    private final AdminRepository repository;


    AdminLogin(AdminRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @ResponseBody
    public Message CreateSession(@RequestBody CreateSessionRequest requestBody, HttpServletRequest request) {

        if (requestBody.getPassword().equals(repository.findByEmail(requestBody.getEmail()).getPassword())){

            HttpSession session = request.getSession();
            session.setAttribute("type", "admin");

            return new Message("Authenticated");
        } else {
            return new Message("Authentication failed");
        }
    }
}
