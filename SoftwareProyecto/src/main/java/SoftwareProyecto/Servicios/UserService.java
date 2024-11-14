package SoftwareProyecto.Servicios;

import SoftwareProyecto.Clases.User;
import SoftwareProyecto.Repositorios.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User createUser(String username, String password) {;
        User user = new User(username, password);
        return userRepository.save(user);
    }

    public boolean checkPassword(User user, String password) {
        return Objects.equals(password, user.getPassword());
    }
}
