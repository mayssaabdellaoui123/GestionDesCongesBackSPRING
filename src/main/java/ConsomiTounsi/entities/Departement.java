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

    public void setIdDepartement(long idDepartement) {
        this.idDepartement = idDepartement;
    }

    public void setNomDepartement(String nomDepartement) {
        this.nomDepartement = nomDepartement;
    }

    public void setMatriculeBoss(String matriculeBoss) {
        this.matriculeBoss = matriculeBoss;
    }

    public long getIdDepartement() {
        return idDepartement;
    }

    public String getNomDepartement() {
        return nomDepartement;
    }

    public String getMatriculeBoss() {
        return matriculeBoss;
    }

    private String matriculeBoss;

    @JsonIgnore
    @OneToMany(mappedBy="departement",
            cascade = CascadeType.ALL,
            fetch= FetchType.EAGER)
    private List<User> users = new ArrayList<>();
}
