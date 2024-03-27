package robert.swag.pwebbe.services;

import robert.swag.pwebbe.dto.designer.DesignerDto;
import robert.swag.pwebbe.entities.Designer;

import java.util.List;

public interface DesignerService {

    public List<Designer> get();
    public Designer getById(Long id);
    public Designer add(DesignerDto designerDto);
    public Designer update(DesignerDto designerDto);
    public void delete(Long id);
    public boolean verifyDesignerExists(Long id);
    public boolean verifyDesignerExists(String name);

}
