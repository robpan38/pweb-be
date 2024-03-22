package robert.swag.pwebbe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import robert.swag.pwebbe.entities.ClothingGarment;

@Repository
public interface ClothingGarmentRepository extends JpaRepository<ClothingGarment, Long> {
}
