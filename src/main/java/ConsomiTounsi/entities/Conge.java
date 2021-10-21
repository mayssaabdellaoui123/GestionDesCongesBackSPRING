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


    private String userNameVF ;

    public String getUserNameVF() {
        return userNameVF;
    }

    public void setUserNameVF(String userNameVF) {
        this.userNameVF = userNameVF;
    }

    public String soldeThisYear ;
    public String soldeYear_1 ;
    public String soldeYear_2 ;

    public String total ;
    public String nmbrDeJourConge ;
    public String RestDeJourConge ;


    public LocalDateTime datevalidationFinale ;
    public LocalDateTime getDatevalidationFinale() {
        return datevalidationFinale;
    }

    public void setDatevalidationFinale(LocalDateTime datevalidationFinale) {
        this.datevalidationFinale = datevalidationFinale;
    }
    public LocalDateTime datevalidationPrimaire ;
    public LocalDateTime getDatevalidationPrimaire() {
        return datevalidationPrimaire;
    }

    public void setDatevalidationPrimaire(LocalDateTime datevalidationPrimaire) {
        this.datevalidationPrimaire = datevalidationPrimaire;
    }



    private LocalDateTime dateSaisie;

    public String getSoldeThisYear() {
        return soldeThisYear;
    }

    public void setSoldeThisYear(String soldeThisYear) {
        this.soldeThisYear = soldeThisYear;
    }

    public String getSoldeYear_1() {
        return soldeYear_1;
    }

    public void setSoldeYear_1(String soldeYear_1) {
        this.soldeYear_1 = soldeYear_1;
    }

    public String getSoldeYear_2() {
        return soldeYear_2;
    }

    public void setSoldeYear_2(String soldeYear_2) {
        this.soldeYear_2 = soldeYear_2;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getNmbrDeJourConge() {
        return nmbrDeJourConge;
    }

    public void setNmbrDeJourConge(String nmbrDeJourConge) {
        this.nmbrDeJourConge = nmbrDeJourConge;
    }

    public String getRestDeJourConge() {
        return RestDeJourConge;
    }

    public void setRestDeJourConge(String restDeJourConge) {
        RestDeJourConge = restDeJourConge;
    }



    @Enumerated(EnumType.STRING)
    private TypeConge type;


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
