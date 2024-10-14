package SoftwareProyecto.Repositorios;

import SoftwareProyecto.Clases.Password;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordRepository extends JpaRepository<Password, Long> {
}
