package robert.swag.pwebbe.services.impl;

import lombok.RequiredArgsConstructor;
import org.flywaydb.core.internal.util.CollectionsUtils;
import org.springframework.stereotype.Service;
import robert.swag.pwebbe.dto.clothingGarment.ClothingGarmentDto;
import robert.swag.pwebbe.entities.ClothingGarment;
import robert.swag.pwebbe.repositories.ClothingGarmentRepository;
import robert.swag.pwebbe.repositories.CollectionRepository;
import robert.swag.pwebbe.repositories.MaterialRepository;
import robert.swag.pwebbe.services.ClothingGarmentService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClothingGarmentServiceImpl implements ClothingGarmentService {

    private final ClothingGarmentRepository cgRepository;
    private final MaterialRepository materialRepository;
    private final CollectionRepository collectionRepository;


    @Override
    public List<ClothingGarment> get() {
        return cgRepository.findAll();
    }

    @Override
    public ClothingGarment getById(Long id) {
        return cgRepository.findById(id).get();
    }

    @Override
    public boolean verifyClothingGarmentExists(Long id) {
        return cgRepository.findById(id).isPresent();
    }

    @Override
    public boolean verifyClothingGarmentExists(String name) {
        return cgRepository.findByName(name).isPresent();
    }

    @Override
    public ClothingGarment add(ClothingGarmentDto clothingGarmentDto) {
        return cgRepository.save(convertFromDto(clothingGarmentDto));
    }

    @Override
    public ClothingGarment update(ClothingGarmentDto clothingGarmentDto) {
        ClothingGarment clothingGarment = cgRepository.findById(clothingGarmentDto.getId()).get();
        ClothingGarment converted = convertFromDto(clothingGarmentDto);
        clothingGarment.setName(converted.getName());
        clothingGarment.setMaterials(converted.getMaterials());
        clothingGarment.setPrice(converted.getPrice());
        clothingGarment.setStock(converted.getStock());
        clothingGarment.setYear(converted.getYear());
        clothingGarment.setCollection(converted.getCollection());
        return cgRepository.save(clothingGarment);
    }

    @Override
    public void delete(Long id) {
        cgRepository.deleteById(id);
    }

    private ClothingGarment convertFromDto(ClothingGarmentDto clothingGarmentDto) {
        return ClothingGarment.builder()
                .name(clothingGarmentDto.getName())
                .materials(
                        clothingGarmentDto.getMaterialsNames()
                                .stream()
                                .map(material -> materialRepository.findByName(material).get())
                                .collect(Collectors.toList())
                )
                .price(clothingGarmentDto.getPrice())
                .stock(clothingGarmentDto.getStock())
                .year(clothingGarmentDto.getYear())
                .collection(
                        collectionRepository.findByName(clothingGarmentDto.getCollectionName()).orElse(null)
                )
                .build();
    }
}
