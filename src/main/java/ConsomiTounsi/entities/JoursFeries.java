package ConsomiTounsi.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JoursFeries implements Serializable {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private long idJourFeries;
    private String nom;

    @Temporal(TemporalType.DATE)
    private Date date;


    @JsonIgnore
    @ManyToMany(mappedBy="joursferies",cascade= CascadeType.PERSIST,fetch = FetchType.EAGER)
    private List<Employee> employees;
}
