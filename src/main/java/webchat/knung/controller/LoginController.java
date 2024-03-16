package webchat.knung.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
    @GetMapping("/")
    public String login() {
        return "login";
    }

    @PostMapping("/Athentication")
    public String athentication() {
        return "temp";
    }
    @GetMapping("/register")
    public String register() {
        return "register";
    }
}
