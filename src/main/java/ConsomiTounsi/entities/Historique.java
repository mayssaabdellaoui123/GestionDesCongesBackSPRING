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
public class Historique implements Serializable {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private long idHistorique;
    private String Description;
    private LocalDateTime Date ;
    private String Owner ;
    private String Action ;
    @Enumerated(EnumType.STRING)
    private TypeHistorique Typehistorique;

    public void setType(TypeHistorique type) {
        this.Typehistorique = type;
    }

    public TypeHistorique getType() {
        return Typehistorique;
    }

    public void setAction(String action) {
        Action = action;
    }

    public String getAction() {
        return Action;
    }



    public void setIdHistorique(long idHistorique) {
        this.idHistorique = idHistorique;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setDate(LocalDateTime date) {
        Date = date;
    }

    public void setOwner(String owner) {
        Owner = owner;
    }

    public long getIdHistorique() {
        return idHistorique;
    }

    public String getDescription() {
        return Description;
    }

    public LocalDateTime getDate() {
        return Date;
    }

    public String getOwner() {
        return Owner;
    }
}
