package robert.swag.pwebbe.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import robert.swag.pwebbe.dto.designer.DesignerDto;
import robert.swag.pwebbe.entities.Designer;
import robert.swag.pwebbe.repositories.DesignerRepository;
import robert.swag.pwebbe.services.DesignerService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DesignerServiceImpl implements DesignerService {

    private final DesignerRepository designerRepository;

    @Override
    public List<Designer> get() {
        return designerRepository.findAll();
    }

    @Override
    public Designer getById(Long id) {
        return designerRepository.findById(id).get();
    }

    @Override
    public Designer add(DesignerDto designerDto) {
        return designerRepository.save(convertFromDto(designerDto));
    }

    @Override
    public Designer update(DesignerDto designerDto) {
        Designer designer = designerRepository.findById(designerDto.getId()).get();
        designer.setName(designerDto.getName());
        return designerRepository.save(designer);
    }

    @Override
    public void delete(Long id) {
        designerRepository.deleteById(id);
    }

    public Designer convertFromDto(DesignerDto designerDto) {
        return Designer
                .builder()
                .name(designerDto.getName())
                .build();
    }

    @Override
    public boolean verifyDesignerExists(Long id) {
        return designerRepository.findById(id).isPresent();
    }

    @Override
    public boolean verifyDesignerExists(String name) {
        return designerRepository.findByName(name).isPresent();
    }
}
