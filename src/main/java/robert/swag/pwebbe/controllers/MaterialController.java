package robert.swag.pwebbe.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import robert.swag.pwebbe.dto.material.MaterialDto;
import robert.swag.pwebbe.entities.Material;
import robert.swag.pwebbe.services.MaterialService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/material")
@RequiredArgsConstructor
public class MaterialController {

    private final MaterialService materialService;

    @GetMapping
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<List<Material>> get() {
        return ResponseEntity.ok().body(materialService.get());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<Material> getById(@PathVariable Long id) {
        if (!materialService.verifyMaterialExists(id)) {
            return ResponseEntity.status(404).body(null);
        }

        return ResponseEntity.ok().body(materialService.getById(id));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<Material> add(@RequestBody MaterialDto material) {
        if (materialService.verifyMaterialExists(material.getName())) {
            return ResponseEntity.status(400).body(null);
        }

        return ResponseEntity.ok().body(materialService.add(material));
    }

    @PostMapping("/update")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<Material> update(@RequestBody MaterialDto materialDto) {
        if (materialDto.getId() == null || !materialService.verifyMaterialExists(materialDto.getId())) {
            return ResponseEntity.status(404).body(null);
        }

        return ResponseEntity.ok().body(materialService.update(materialDto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!materialService.verifyMaterialExists(id)) {
            return ResponseEntity.status(404).body(null);
        }

        materialService.delete(id);
        return ResponseEntity.ok().body(null);
    }
}
