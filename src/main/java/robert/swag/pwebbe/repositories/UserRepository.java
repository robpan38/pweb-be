package robert.swag.pwebbe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import robert.swag.pwebbe.entities.Role;
import robert.swag.pwebbe.entities.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);
    User findByRole(Role role);
}
