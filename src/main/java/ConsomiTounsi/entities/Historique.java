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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idHistorique;
    private String description;
    private LocalDateTime date;
    private String owner;
    private String action;
    @Enumerated(EnumType.STRING)
    private TypeHistorique typehistorique;


    public void setIdHistorique(long idHistorique) {
        this.idHistorique = idHistorique;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setTypehistorique(TypeHistorique typehistorique) {
        this.typehistorique = typehistorique;
    }

    public long getIdHistorique() {
        return idHistorique;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getOwner() {
        return owner;
    }

    public String getAction() {
        return action;
    }

    public TypeHistorique getTypehistorique() {
        return typehistorique;
    }
}