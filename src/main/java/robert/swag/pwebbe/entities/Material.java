package robert.swag.pwebbe.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="material")
public class Material implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(name="material_name")
    private String name;
    @Column(name="material_producer")
    private String producer;
    @JsonIgnore
    @ManyToMany(mappedBy="materials")
    private List<ClothingGarment> clothingGarments;
}
