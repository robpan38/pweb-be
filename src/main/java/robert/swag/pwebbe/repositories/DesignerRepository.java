package robert.swag.pwebbe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import robert.swag.pwebbe.entities.Designer;

import java.util.Optional;

@Repository
public interface DesignerRepository extends JpaRepository<Designer, Long> {

    Optional<Designer> findByName(String name);
}
