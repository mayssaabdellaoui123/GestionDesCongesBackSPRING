package ConsomiTounsi.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TypeDeConge implements Serializable {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private long idTypeConge;

    private String type;

    @JsonIgnore
    @OneToMany(mappedBy="typeDeconge",
            cascade = CascadeType.ALL,
            fetch= FetchType.EAGER)
    private List<Conge> typeC = new ArrayList<>();
}
