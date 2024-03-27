package robert.swag.pwebbe.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import robert.swag.pwebbe.dto.designer.DesignerDto;
import robert.swag.pwebbe.entities.Designer;
import robert.swag.pwebbe.services.DesignerService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/designer")
@RequiredArgsConstructor
public class DesignerController {

    private final DesignerService designerService;

    @GetMapping
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<List<Designer>> get() {
        return ResponseEntity.ok(designerService.get());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<Designer> getById(@PathVariable Long id) {
        if (!designerService.verifyDesignerExists(id)) {
            return ResponseEntity.status(404).body(null);
        }

        return ResponseEntity.ok(designerService.getById(id));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<Designer> add(@RequestBody DesignerDto designerDto) {
        if (designerService.verifyDesignerExists(designerDto.getName())) {
            return ResponseEntity.status(400).body(null);
        }

        return ResponseEntity.ok(designerService.add(designerDto));
    }

    @PostMapping("/update")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<Designer> update(@RequestBody DesignerDto designerDto) {
        if (designerDto.getId() == null || !designerService.verifyDesignerExists(designerDto.getId())) {
            return ResponseEntity.status(400).body(null);
        }

        return ResponseEntity.ok(designerService.update(designerDto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!designerService.verifyDesignerExists(id)) {
            return ResponseEntity.status(400).body(null);
        }

        designerService.delete(id);
        return ResponseEntity.ok(null);
    }
}
