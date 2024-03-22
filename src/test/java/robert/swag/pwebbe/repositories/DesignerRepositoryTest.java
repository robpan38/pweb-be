package robert.swag.pwebbe.repositories;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import robert.swag.pwebbe.entities.Designer;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DesignerRepositoryTest {

    @Autowired
    private DesignerRepository designerRepository;

    @Test
    public void testAddDesigner() {
//        Designer designer = new Designer();
//        designer.setName("Helmut Lang");
//        designerRepository.save(designer);

        Optional<Designer> designerOptional = designerRepository.findByName("Helmut Lang");
        System.out.println(designerOptional.get());
    }
}