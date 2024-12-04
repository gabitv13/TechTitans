package SoftwareProyecto.Controladores;

import SoftwareProyecto.Clases.User;
import SoftwareProyecto.Servicios.PasswordService;
import SoftwareProyecto.Servicios.UserService;
import SoftwareProyecto.Servicios.UserSession;
import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
        // Use a more strict Safelist that strips all HTML tags
        String sanitizedUsername = Jsoup.clean(username, Safelist.none());

        Optional<User> userOptional = userService.findByUsername(sanitizedUsername);

        if (userOptional.isPresent() && userService.checkPassword(userOptional.get(), password)) {
            userSession.login(userOptional.get());
            return "redirect:/passwords";
        } else {
            model.addAttribute("ErrorMessage", "Nombre de usuario o contraseña incorrecta.");
            return "login";
        }
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam("username") String username, @RequestParam("password") String password, Model model) {
        // Use Safelist.none() to completely strip HTML tags
        String sanitizedUsername = Jsoup.clean(username, Safelist.none());
        Optional<User> existingUser = userService.findByUsername(sanitizedUsername);

        if (existingUser.isPresent()) {
            model.addAttribute("errorMessage", "El usuario ya existe.");
            return "safePassword";
        }

        // Crear y guardar un nuevo usuario con el nombre y la contraseña proporcionados
        User newUser = userService.createUser(sanitizedUsername, password);
        userSession.login(newUser);
        return "redirect:/passwords";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        return "safePassword";
    }

    @GetMapping("/encriptar")
    public String showEncryptForm(Model model){
        return "encriptado/encrypt";
    }
}