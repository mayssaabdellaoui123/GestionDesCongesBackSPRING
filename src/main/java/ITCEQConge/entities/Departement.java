package ITCEQConge.entities;

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

    public String nomBoss ;

    public String getNomBoss() {
        return nomBoss;
    }

    public void setNomBoss(String nomBoss) {
        this.nomBoss = nomBoss;
    }

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

    private  String MatriculeRemplaceur;

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setMatriculeRemplaceur(String matriculeRemplaceur) {
        MatriculeRemplaceur = matriculeRemplaceur;
    }

    public String getMatriculeRemplaceur() {
        return MatriculeRemplaceur;
    }

    @JsonIgnore
    @OneToMany(mappedBy="departement",
            cascade = CascadeType.ALL,
            fetch= FetchType.EAGER)
    private List<User> users = new ArrayList<>();
}
