package robert.swag.pwebbe.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import robert.swag.pwebbe.dto.material.MaterialDto;
import robert.swag.pwebbe.entities.Material;
import robert.swag.pwebbe.repositories.MaterialRepository;
import robert.swag.pwebbe.services.MaterialService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MaterialServiceImpl implements MaterialService {

    private final MaterialRepository materialRepository;

    @Override
    public List<Material> get() {
        return materialRepository.findAll();
    }

    @Override
    public Material getById(Long id) {
        return materialRepository.findById(id).get();
    }

    @Override
    public boolean verifyMaterialExists(Long id) {
        return materialRepository.findById(id).isPresent();
    }

    @Override
    public boolean verifyMaterialExists(String name) {
        return materialRepository.findByName(name).isPresent();
    }

    @Override
    public boolean verifyMaterialsExist(List<String> names) {
        for (String name : names) {
            if (!verifyMaterialExists(name)) {
                return false;
            }
        }

        return true;
    }

    public Material add(MaterialDto materialDto) {
        return materialRepository.save(convertFromDto(materialDto));
    }

    @Override
    public Material update(MaterialDto materialDto) {
        Material material = materialRepository.findById(materialDto.getId()).get();
        material.setName(materialDto.getName());
        material.setProducer(materialDto.getProducer());
        return materialRepository.save(material);
    }

    @Override
    public void delete(Long id) {
        materialRepository.deleteById(id);
    }


    public Material convertFromDto(MaterialDto materialDto) {
        return Material
                .builder()
                .name(materialDto.getName())
                .producer(materialDto.getProducer()).build();
    }
}
