package robert.swag.pwebbe.services;


import robert.swag.pwebbe.dto.collection.CollectionDto;
import robert.swag.pwebbe.entities.Collection;

import java.util.List;

public interface CollectionService {

    public List<Collection> get();
    public Collection getById(Long id);
    public Collection add(CollectionDto collectionDto);
    public Collection update(CollectionDto collectionDto);
    public void delete(Long id);
    public boolean verifyCollectionExists(String name);
    public boolean verifyCollectionExists(Long id);
}
