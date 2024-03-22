package robert.swag.pwebbe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import robert.swag.pwebbe.entities.Role;
import robert.swag.pwebbe.entities.User;
import robert.swag.pwebbe.repositories.UserRepository;

@SpringBootApplication
public class PwebBeApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(PwebBeApplication.class, args);
	}

	public void run(String... args) {
		if (userRepository.findByRole(Role.ADMIN) == null) {
			User admin = new User();
			admin.setEmail("admin@gmail.com");
			admin.setFirstName("admin");
			admin.setRole(Role.ADMIN);
			admin.setPassword(new BCryptPasswordEncoder().encode("admin"));
			userRepository.save(admin);
		}
	}
}
