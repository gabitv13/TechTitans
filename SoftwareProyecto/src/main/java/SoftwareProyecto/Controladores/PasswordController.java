package SoftwareProyecto.Controladores;

import SoftwareProyecto.Clases.Password;
import SoftwareProyecto.Repositorios.PasswordRepository;
import SoftwareProyecto.Servicios.PasswordService;
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
    private PasswordService passwordService;

    @PostConstruct
    public void def(){
        passwordRepository.save(new Password("gmail.com", "123"));
        passwordRepository.save(new Password("uber", "234"));

    }

    @GetMapping("/passwords")
    public String listPasswords(Model model) {
        model.addAttribute("passwords", passwordService.findAll());
        return "passwords";
    }
    @GetMapping("/passwords/new")
    public String newPasswordForm(Model model) {
        return "addPassword";
    }
    @GetMapping("/passwords/update")
    public String updatePasswordForm(Model model) {
        return "updatePassword";
    }

    @PostMapping("/passwords/add")
    public String addPassword(@RequestParam String website, @RequestParam String password) {
        passwordRepository.save(new Password(website, password));
        return "añadida";
    }

    @PostMapping("/passwords/delete")
    public String deletePassword(@RequestParam Long id) {
        passwordRepository.deleteById(id);
        return "deletePassword";
    }

    @GetMapping("/passwords/update/{id}")
    public String editPasswordForm(@PathVariable Long id, Model model) {
        Password password = passwordService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid password Id:" + id));
        model.addAttribute("password", password);
        return "updatePassword";
    }

    @PostMapping("/passwords/update")
    public String updatePassword(@RequestParam Long id, @RequestParam String newPassword) {
        Password password = passwordService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid password Id:" + id));
        password.setPassword(newPassword);
        passwordService.save(password);
        return "redirect:/passwords";
    }
}