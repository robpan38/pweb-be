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

    @PostMapping
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<Material> add(@RequestBody MaterialDto material) {
        return ResponseEntity.ok().body(materialService.add(material));
    }

    @PostMapping("/update")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<Material> update(@RequestBody MaterialDto materialDto) {
        return ResponseEntity.ok().body(materialService.update(materialDto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        materialService.delete(id);
        return ResponseEntity.ok().body(null);
    }
}
