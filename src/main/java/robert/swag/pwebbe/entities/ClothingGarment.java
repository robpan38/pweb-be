package robert.swag.pwebbe.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
            joinColumns=@JoinColumn(name="clothing_id"),
            inverseJoinColumns=@JoinColumn(name="material_id")
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
