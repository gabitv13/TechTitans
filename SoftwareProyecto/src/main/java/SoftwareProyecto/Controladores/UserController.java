package SoftwareProyecto.Controladores;

import SoftwareProyecto.Clases.User;
import SoftwareProyecto.Servicios.UserService;
import SoftwareProyecto.Servicios.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserSession userSession;

    @PostMapping("/login")
    public String login(@RequestParam String username) {
        Optional<User> userOptional = userService.findByUsername(username);

        User user;
        if (userOptional.isPresent()) {
            user = userOptional.get();
        } else {
            user = userService.createUser(username);
        }

        userSession.login(user);

        return "redirect:/passwords";
    }
}