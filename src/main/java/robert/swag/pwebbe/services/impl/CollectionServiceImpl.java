package robert.swag.pwebbe.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import robert.swag.pwebbe.dto.collection.CollectionDto;
import robert.swag.pwebbe.entities.Collection;
import robert.swag.pwebbe.repositories.CollectionRepository;
import robert.swag.pwebbe.repositories.DesignerRepository;
import robert.swag.pwebbe.services.CollectionService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CollectionServiceImpl implements CollectionService {

    private final CollectionRepository collectionRepository;
    private final DesignerRepository designerRepository;

    @Override
    public List<Collection> get() {
        return collectionRepository.findAll();
    }

    @Override
    public Collection getById(Long id) {
        return collectionRepository.findById(id).get();
    }

    @Override
    public Collection add(CollectionDto collectionDto) {
        return collectionRepository.save(convertFromDto(collectionDto));
    }

    @Override
    public Collection update(CollectionDto collectionDto) {
        Collection collection = collectionRepository.findById(collectionDto.getId()).get();
        Collection converted = convertFromDto(collectionDto);
        collection.setName(converted.getName());
        collection.setDesigner(converted.getDesigner());
        collection.setSeason(converted.getSeason());
        collection.setYear(converted.getYear());
        return collectionRepository.save(collection);
    }

    @Override
    public void delete(Long id) {
        collectionRepository.deleteById(id);
    }

    private Collection convertFromDto(CollectionDto collectionDto) {
        return Collection.builder()
                .name(collectionDto.getName())
                .designer(designerRepository.findByName(collectionDto.getDesignerName()).orElse(null))
                .season(collectionDto.getSeason())
                .year(collectionDto.getYear())
                .build();
    }

    @Override
    public boolean verifyCollectionExists(String name) {
        return collectionRepository.findByName(name).isPresent();
    }

    @Override
    public boolean verifyCollectionExists(Long id) {
        return collectionRepository.findById(id).isPresent();
    }
}
