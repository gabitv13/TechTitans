package SoftwareProyecto;


import SoftwareProyecto.Servicios.PasswordService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class SoftwareProyectoApplication {
	@Bean
	public PasswordService passwordService() {
		return new PasswordService();
	}

	public static void main(String[] args) {
		SpringApplication.run(SoftwareProyectoApplication.class, args);
	}

}
