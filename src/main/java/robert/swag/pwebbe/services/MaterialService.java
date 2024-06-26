package robert.swag.pwebbe.services;

import robert.swag.pwebbe.dto.material.MaterialDto;
import robert.swag.pwebbe.entities.Material;

import java.util.List;

public interface MaterialService {
    public List<Material> get();
    public Material getById(Long id);
    public boolean verifyMaterialExists(Long id);
    public boolean verifyMaterialExists(String name);
    public boolean verifyMaterialsExist(List<String> names);
    public Material add(MaterialDto materialDto);

    public Material update(MaterialDto materialDto);
    public void delete(Long id);
}
