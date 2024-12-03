package SoftwareProyecto.Servicios;

import SoftwareProyecto.Clases.Password;
import SoftwareProyecto.Clases.User;
import SoftwareProyecto.Repositorios.PasswordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;


@Service
public class PasswordService {
    private AtomicLong nextId = new AtomicLong();
    @Autowired
    private PasswordRepository passwordRepository;

    @Autowired
    public PasswordService(PasswordRepository passwordRepository) {
        this.passwordRepository = passwordRepository;
    }

    public List<Password> findAllByUser(User user) {
        return passwordRepository.findByUser(user);
    }
        public Collection<Password> findAll() {
        return passwordRepository.findAll();
    }
    public Page<Password> findAll(Pageable page) {
        return passwordRepository.findAll(page);
    }

    public Optional<Password> findById(long id) {
        return passwordRepository.findById(id);
    }

    public void save(Password password) {
        passwordRepository.save(password);
    }

    public void deleteById(long id) {
        passwordRepository.deleteById(id);
    }

}
