package robert.swag.pwebbe.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import robert.swag.pwebbe.dto.clothingGarment.ClothingGarmentDto;
import robert.swag.pwebbe.entities.ClothingGarment;
import robert.swag.pwebbe.services.ClothingGarmentService;
import robert.swag.pwebbe.services.CollectionService;
import robert.swag.pwebbe.services.MaterialService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clothingGarment")
@RequiredArgsConstructor
public class ClothingGarmentController {

    private final ClothingGarmentService cgService;
    private final MaterialService materialService;
    private final CollectionService collectionService;

    @GetMapping
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<List<ClothingGarment>> get() {
        return ResponseEntity.ok(cgService.get());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<ClothingGarment> getById(@PathVariable Long id) {
        if (!cgService.verifyClothingGarmentExists(id)) {
            return ResponseEntity.status(404).body(null);
        }

        return ResponseEntity.ok(cgService.getById(id));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<String> add(@RequestBody ClothingGarmentDto clothingGarmentDto) {
        if (clothingGarmentDto.getName() == null) {
            return ResponseEntity.status(400).body("Provide a name for the clothing garment");
        }

        if (cgService.verifyClothingGarmentExists(clothingGarmentDto.getName())) {
            return ResponseEntity.status(400).body("There already is a clothing garment with this name");
        }

        if (!materialService.verifyMaterialsExist(clothingGarmentDto.getMaterialsNames())) {
            return ResponseEntity.status(400).body("One or more of the materials provided for this clothing garment do not exist");
        }

        if (!collectionService.verifyCollectionExists(clothingGarmentDto.getCollectionName())) {
            return ResponseEntity.status(400).body("The collection specified for this clothing garment does not exist");
        }

        cgService.add(clothingGarmentDto);
        return ResponseEntity.ok("Clothing garment successfully added");
    }

    @PostMapping("/update")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<String> update(@RequestBody ClothingGarmentDto clothingGarmentDto) {
        if (clothingGarmentDto.getId() == null || !cgService.verifyClothingGarmentExists(clothingGarmentDto.getId())) {
            return ResponseEntity.status(400).body("Clothing garment does not have an associated id");
        }

        if (!materialService.verifyMaterialsExist(clothingGarmentDto.getMaterialsNames())) {
            return ResponseEntity.status(400).body("One or more of the materials provided for this clothing garment do not exist");
        }

        if (!collectionService.verifyCollectionExists(clothingGarmentDto.getCollectionName())
        && !clothingGarmentDto.getCollectionName().equals("")) {
            return ResponseEntity.status(400).body("The collection specified for this clothing garment does not exist");
        }

        cgService.update(clothingGarmentDto);
        return ResponseEntity.ok("Clothing garment was successfully updated");
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        if (!cgService.verifyClothingGarmentExists(id)) {
            return ResponseEntity.status(400).body("The requested clothing garment does not exist");
        }

        cgService.delete(id);
        return ResponseEntity.ok("Clothing garment successfully deleted");
    }
}
