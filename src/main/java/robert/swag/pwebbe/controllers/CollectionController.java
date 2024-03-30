package robert.swag.pwebbe.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import robert.swag.pwebbe.dto.collection.CollectionDto;
import robert.swag.pwebbe.entities.Collection;
import robert.swag.pwebbe.services.CollectionService;
import robert.swag.pwebbe.services.DesignerService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/collection")
@RequiredArgsConstructor
public class CollectionController {

    private final CollectionService collectionService;
    private final DesignerService designerService;

    @GetMapping
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<List<Collection>> get() {
        return ResponseEntity.ok(collectionService.get());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<Collection> getById(@PathVariable Long id) {
        if (!collectionService.verifyCollectionExists(id)) {
            return ResponseEntity.status(404).body(null);
        }

        return ResponseEntity.ok(collectionService.getById(id));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<String> add(@RequestBody CollectionDto collectionDto) {
        if (collectionDto.getName() == null || collectionService.verifyCollectionExists(collectionDto.getName())) {
            return ResponseEntity.status(400).body("There is already a collection with this name");
        }

        if (!designerService.verifyDesignerExists(collectionDto.getDesignerName())) {
            return ResponseEntity.status(400).body("The designer of this collection does not exist");
        }

        collectionService.add(collectionDto);
        return ResponseEntity.ok("Collection was successfully added");
    }

    @PostMapping("/update")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<String> update(@RequestBody CollectionDto collectionDto) {
        if (collectionDto.getId() == null || !collectionService.verifyCollectionExists(collectionDto.getId())) {
            return ResponseEntity.status(400).body("Provide the id for an existing collection");
        }

        if (!designerService.verifyDesignerExists(collectionDto.getDesignerName())) {
            return ResponseEntity.status(400).body("The designer of this collection does not exist");
        }

        collectionService.update(collectionDto);
        return ResponseEntity.ok("Collection was successfully updated");
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        if (!collectionService.verifyCollectionExists(id)) {
            return ResponseEntity.status(400).body("There is no collection with this id");
        }

        collectionService.delete(id);
        return ResponseEntity.ok("Collection was successfully deleted");
    }
}
