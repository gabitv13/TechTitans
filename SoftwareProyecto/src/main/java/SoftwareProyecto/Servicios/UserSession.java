package SoftwareProyecto.Servicios;

import SoftwareProyecto.Clases.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
@Component
public class UserSession {

    private final HttpSession httpSession;

    public UserSession(HttpSession httpSession) {
        this.httpSession = httpSession;
    }

    public void login(User user) {
        httpSession.setAttribute("currentUser", user);  // Guardar objeto User completo
    }

    public User getCurrentUser() {
        return (User) httpSession.getAttribute("currentUser");
    }

    public String getUsername() {
        User user = getCurrentUser();
        return user != null ? user.getUsername() : null;
    }

    public boolean isLoggedIn() {
        return getCurrentUser() != null;
    }

    public void logout() {
        httpSession.invalidate();
    }
}