package robert.swag.pwebbe.dto.clothingGarment;

import lombok.Data;

import java.util.List;

@Data
public class ClothingGarmentDto {

    private Long id;
    private String name;
    private List<String> materialsNames;
    private Float price;
    private Integer stock;
    private Integer year;
    private String collectionName;
}
