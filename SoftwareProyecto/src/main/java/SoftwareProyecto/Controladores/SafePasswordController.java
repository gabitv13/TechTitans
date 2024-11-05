package SoftwareProyecto.Controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SafePasswordController {
    @GetMapping("/register2")
    public String safePassword () {
        return "safePassword";
    }
}