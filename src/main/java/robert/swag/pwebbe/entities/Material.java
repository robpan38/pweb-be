package robert.swag.pwebbe.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name="material")
public class Material implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(name="material_name")
    private String name;
    @Column(name="material_producer")
    private String producer;
    @ManyToMany(mappedBy="materials")
    private List<ClothingGarment> clothingGarments;
}
