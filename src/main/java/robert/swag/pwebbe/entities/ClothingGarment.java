package robert.swag.pwebbe.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name="clothing_garment")
public class ClothingGarment implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(name="clothing_name")
    private String name;
    @ManyToMany
    @JoinTable(
            name="clothing_material",
            foreignKey=@ForeignKey(name="clothing_id"),
            inverseForeignKey=@ForeignKey(name="material_id")
    )
    private List<Material> materials;
    @Column(name="clothing_price")
    private Float price;
    @Column(name="clothing_stock")
    private Integer stock;
    @Column(name="clothing_year")
    private Integer year;
    @ManyToOne
    @JoinColumn(name="clothing_collection_id")
    private Collection collection;
}
