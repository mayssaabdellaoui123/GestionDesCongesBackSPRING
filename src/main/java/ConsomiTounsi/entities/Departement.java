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
public class Departement implements Serializable {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private long idDepartement;
    private String nomDepartement;
    private long matriculeBoss;

    @JsonIgnore
    @OneToMany(mappedBy="departement",
            cascade = CascadeType.ALL,
            fetch= FetchType.EAGER)
    private List<User> users = new ArrayList<>();
}
