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
public class Employee implements Serializable {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private long id;

    private String matricule;
    private String nomPrenom;
    private String adresse;
    private long telephone;
    private String tache;
    private int solde;


    @JsonIgnore
    @OneToMany(mappedBy="employee",
            cascade = CascadeType.ALL)
    private List<Conge> conge = new ArrayList<>();


    @ManyToMany(cascade = CascadeType.ALL)
    private List<JoursFeries> joursferies;


    @JsonIgnore
    @ManyToOne
    private Departement departement;

    @OneToOne
    private Carriere carriere;

    @OneToOne
    @JoinColumn(name="ACCOUNT_ID")
    private User account;


}
