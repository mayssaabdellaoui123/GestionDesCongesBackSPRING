package ConsomiTounsi.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TitreDeConge implements Serializable {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private long idTitreConge;

    // @Temporal (TemporalType.DATE)
    private LocalDateTime dateTitreConge;


    @OneToOne(mappedBy="titreDeConge")
    private Conge conge;

}
