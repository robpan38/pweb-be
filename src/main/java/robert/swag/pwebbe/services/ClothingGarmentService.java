package robert.swag.pwebbe.services;

import robert.swag.pwebbe.dto.clothingGarment.ClothingGarmentDto;
import robert.swag.pwebbe.entities.ClothingGarment;

import java.util.List;

public interface ClothingGarmentService {

    public List<ClothingGarment> get();
    public ClothingGarment getById(Long id);
    public boolean verifyClothingGarmentExists(Long id);
    public boolean verifyClothingGarmentExists(String name);
    public ClothingGarment add(ClothingGarmentDto clothingGarmentDto);
    public ClothingGarment update(ClothingGarmentDto clothingGarmentDto);
    public void delete(Long id);
}
