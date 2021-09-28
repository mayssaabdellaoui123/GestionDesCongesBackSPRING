package ConsomiTounsi.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Conge implements Serializable {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private long idConge;
    @Temporal(TemporalType.DATE)
    private Date DateDebut;
    @Temporal(TemporalType.DATE)
    private Date DateFin;

    @Enumerated(EnumType.STRING)
    private TypeConge type;
    private LocalDateTime dateSaisie;

    private String AvisPrimaire;
    private String AvisFinale;

    public boolean isAttente() {
        return attente;
    }

    public void setAttente(boolean attente) {
        this.attente = attente;
    }

    private boolean attente  ;

    public boolean isAttenteFinale() {
        return attenteFinale;
    }

    public void setAttenteFinale(boolean attenteFinale) {
        this.attenteFinale = attenteFinale;
    }

    private boolean attenteFinale  ;

    public String getMatriculeOwnerVP() {
        return MatriculeOwnerVP;
    }

    public void setMatriculeOwnerVP(String matriculeOwnerVP) {
        MatriculeOwnerVP = matriculeOwnerVP;
    }

    private String MatriculeOwnerVP ;


    private boolean validationPrimaire ;

    public void setIdConge(long idConge) {
        this.idConge = idConge;
    }

    public void setDateDebut(Date dateDebut) {
        DateDebut = dateDebut;
    }

    public void setDateFin(Date dateFin) {
        DateFin = dateFin;
    }

    public void setType(TypeConge type) {
        this.type = type;
    }

    public void setDateSaisie(LocalDateTime dateSaisie) {
        this.dateSaisie = dateSaisie;
    }

    public void setAvisPrimaire(String avisPrimaire) {
        AvisPrimaire = avisPrimaire;
    }

    public void setAvisFinale(String avisFinale) {
        AvisFinale = avisFinale;
    }

    public void setValidationPrimaire(boolean validationPrimaire) {
        this.validationPrimaire = validationPrimaire;
    }

    public void setValidationFinale(boolean validationFinale) {
        this.validationFinale = validationFinale;
    }

    public void setUsers(User users) {
        this.users = users;
    }

    public void setTitreDeConge(TitreDeConge titreDeConge) {
        this.titreDeConge = titreDeConge;
    }

    public long getIdConge() {
        return idConge;
    }

    public Date getDateDebut() {
        return DateDebut;
    }

    public Date getDateFin() {
        return DateFin;
    }

    public TypeConge getType() {
        return type;
    }

    public LocalDateTime getDateSaisie() {
        return dateSaisie;
    }

    public String getAvisPrimaire() {
        return AvisPrimaire;
    }

    public String getAvisFinale() {
        return AvisFinale;
    }

    public boolean isValidationPrimaire() {
        return validationPrimaire;
    }

    public boolean isValidationFinale() {
        return validationFinale;
    }

    public User getUsers() {
        return users;
    }

    public TitreDeConge getTitreDeConge() {
        return titreDeConge;
    }

    private boolean validationFinale ;



    @JsonIgnore
    @ManyToOne
    private User users;

    @OneToOne
    private TitreDeConge titreDeConge;
}
