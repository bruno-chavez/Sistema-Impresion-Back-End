package cl.usm.prevencionderiesgos.si.controllers.auth;

import cl.usm.prevencionderiesgos.si.DTOs.Message;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("auth/session")
public class Session {

    @GetMapping
    @ResponseBody
    public Message CheckSession(HttpServletRequest request) {

        HttpSession session = request.getSession(false);

        if (session != null) {
            return new Message(session.getAttribute("type").toString());
        } else {
            return new Message("none");
        }
    }
}
