package robert.swag.pwebbe.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name="collection")
public class Collection implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(name="collection_name")
    private String name;
    @ManyToOne
    @JoinColumn(name="collection_designer_id")
    private Designer designer;
    @Column(name="collection_season")
    private String season;
    @Column(name="collection_year")
    private Integer year;
    @OneToMany(mappedBy="collection", cascade=CascadeType.ALL)
    private List<ClothingGarment> clothingGarments;
}
