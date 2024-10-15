package SoftwareProyecto.Repositorios;

import SoftwareProyecto.Clases.Password;
import SoftwareProyecto.Clases.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PasswordRepository extends JpaRepository<Password, Long> {
    List<Password> findByUser(User user);
}
