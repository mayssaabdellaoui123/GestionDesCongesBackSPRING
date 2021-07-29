package ConsomiTounsi.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Carriere implements Serializable {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private long idCarriere;

    private int salaire;

    @Enumerated(EnumType.STRING)
    private ClassCarriere classCarriere;

    private int categorie;
    private int echelle;
    private int echelon;





    @OneToOne(mappedBy="carriere")
    private Employee employee;

}
