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
@Table(name="designer")
public class Designer implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Float id;
    @Column(name="designer_name")
    private String name;
    @OneToMany(mappedBy="designer", cascade=CascadeType.ALL)
    private List<Collection> collection;
}
