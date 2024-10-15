package SoftwareProyecto.Servicios;

import SoftwareProyecto.Clases.User;
import SoftwareProyecto.Repositorios.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User createUser(String username) {
        User newUser = new User(username);
        return userRepository.save(newUser);
    }
}
