package robert.swag.pwebbe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import robert.swag.pwebbe.entities.Collection;
import robert.swag.pwebbe.entities.Role;
import robert.swag.pwebbe.entities.User;
import robert.swag.pwebbe.repositories.CollectionRepository;
import robert.swag.pwebbe.repositories.DesignerRepository;
import robert.swag.pwebbe.repositories.UserRepository;


@SpringBootApplication
public class PwebBeApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CollectionRepository collectionRepository;
	@Autowired
	private DesignerRepository designerRepository;

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

		if (!collectionRepository.findByName("AW 98").isPresent()) {
			Collection collection = new Collection();
			collection.setName("AW 98");
			collection.setDesigner(designerRepository.findByName("Helmut Lang").get());
			collection.setYear(1998);
			collection.setSeason("autumn winter");
			collectionRepository.save(collection);
		}
	}
}
