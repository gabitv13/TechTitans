package SoftwareProyecto.Controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NextSprintController {
    @GetMapping("/nextSprint")
    public String nextSpring () {
        return "nextSprint";
    }
}
