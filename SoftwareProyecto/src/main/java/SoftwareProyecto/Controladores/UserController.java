package SoftwareProyecto.Controladores;

import SoftwareProyecto.Clases.User;
import SoftwareProyecto.Servicios.PasswordService;
import SoftwareProyecto.Servicios.UserService;
import SoftwareProyecto.Servicios.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;

import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserSession userSession;
    @Autowired
    private PasswordService passwordService;

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
    @PostMapping("/register")
    public String registerUser(@RequestParam("username") String username, @RequestParam("password") String password, Model model) {
        Optional<User> existingUser = userService.findByUsername(username);
        if (existingUser.isPresent()) {
            model.addAttribute("message", "El usuario ya existe.");
            return "safePassword"; // Redirige de nuevo a la misma página si el usuario existe
        }
        // De lo contrario, el registro es exitoso, redirige a otra página
        return "prueba"; // Redirige a "prueba" tras un registro exitoso
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        return "safePassword"; //
    }

}