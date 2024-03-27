package robert.swag.pwebbe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import robert.swag.pwebbe.entities.Material;

import java.util.Optional;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Long> {

    public Optional<Material> findByName(String name);
}
