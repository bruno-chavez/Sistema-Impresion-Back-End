package cl.usm.prevencionderiesgos.si.controllers.auth;

import cl.usm.prevencionderiesgos.si.DTOs.Message;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/auth/logout")
public class Logout {

    @DeleteMapping
    @ResponseBody
    public Message DeleteSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();

        return new Message("Session deleted");
    }

}
