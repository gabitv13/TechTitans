package SoftwareProyecto.Controladores;

import SoftwareProyecto.Clases.Password;
import SoftwareProyecto.Clases.User;
import SoftwareProyecto.Repositorios.PasswordRepository;
import SoftwareProyecto.Repositorios.UserRepository;
import SoftwareProyecto.Servicios.PasswordService;
import SoftwareProyecto.Servicios.UserSession;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PasswordController {

    @Autowired
    private PasswordRepository passwordRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordService passwordService;
    @Autowired
    private UserSession userSession;

    @PostConstruct
    public void def(){
        User user = new User("user1");
        userRepository.save(user);
        passwordRepository.save(new Password("gmail.com", "123", user));
        passwordRepository.save(new Password("uber", "234", user));

    }

    @GetMapping("/")
    public String home() {
        return "index.html";
    }

    @GetMapping("/login")
    public String loginForm(){
        return "login";
    }


    @GetMapping("/logout")
    public String logout() {
        userSession.logout();
        return "redirect:/";
    }

    @GetMapping("/passwords")
    public String listPasswords(Model model) {
        if (!userSession.isLoggedIn()) {
            return "redirect:/login";
        }
        User user = userSession.getCurrentUser();
        model.addAttribute("username", user.getUsername());
        model.addAttribute("passwords", passwordService.findAllByUser(user));
        return "passwords";
    }
    @GetMapping("/passwords/new")
    public String newPasswordForm(Model model) {
        if (!userSession.isLoggedIn()) {
            return "redirect:/";
        }
    return "addPassword";
    }

    @GetMapping("/passwords/update")
    public String updatePasswordForm(Model model) {
        if (!userSession.isLoggedIn()) {
            return "redirect:/";
        }
        return "updatePassword";
    }

    @PostMapping("/passwords/add")
    public String addPassword(@RequestParam String website, @RequestParam String password) {
        if (!userSession.isLoggedIn()) {
            return "redirect:/";
        }
            User user = userSession.getCurrentUser();
        passwordRepository.save(new Password(website, password, user));
        return "añadida";
    }

    @PostMapping("/passwords/delete")
    public String deletePassword(@RequestParam Long id) {
        if (!userSession.isLoggedIn()) {
            return "redirect:/";
        }
        passwordRepository.deleteById(id);
        return "deletePassword";
    }

    @GetMapping("/passwords/update/{id}")
    public String editPasswordForm(@PathVariable Long id, Model model) {
        if (!userSession.isLoggedIn()) {
            return "redirect:/";
        }
        Password password = passwordService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid password Id:" + id));
        model.addAttribute("password", password);
        return "updatePassword";
    }

    @PostMapping("/passwords/update")
    public String updatePassword(@RequestParam Long id, @RequestParam String newPassword) {
        if (!userSession.isLoggedIn()) {
            return "redirect:/";
        }
        Password password = passwordService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid password Id:" + id));
        password.setPassword(newPassword);
        passwordService.save(password);
        return "redirect:/passwords";
    }
    @PostMapping("/contact")
    public String contact(Model model) {
        return "emailSended";
    }
}