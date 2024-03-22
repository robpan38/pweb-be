package robert.swag.pwebbe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import robert.swag.pwebbe.entities.Collection;

@Repository
public interface CollectionRepository extends JpaRepository<Collection, Long> {
}
