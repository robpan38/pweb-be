package robert.swag.pwebbe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import robert.swag.pwebbe.entities.ClothingGarment;

import java.util.Optional;

@Repository
public interface ClothingGarmentRepository extends JpaRepository<ClothingGarment, Long> {

    public Optional<ClothingGarment> findByName(String name);
}
