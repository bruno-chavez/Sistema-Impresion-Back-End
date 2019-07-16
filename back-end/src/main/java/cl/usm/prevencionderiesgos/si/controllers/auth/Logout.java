package cl.usm.prevencionderiesgos.si.controllers.auth;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/auth/logout")
public class Logout {

    @DeleteMapping
    public String DeleteSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();

        return "Session deleted";
    }

}
